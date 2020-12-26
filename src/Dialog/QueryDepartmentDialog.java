package Dialog;

import javax.swing.*;
import java.awt.event.*;   
import java.sql.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.*;
import DataBase.DBcon;
import Model.Department;

@SuppressWarnings("serial")
public class QueryDepartmentDialog extends JDialog implements ActionListener{
    /**
     *弹出部门的查询窗口
     */
    JTable jTable1;
    JButton query,exit;
    JTextField jtf1; //用以输入部门号的查询框
    JPanel p1,p2;
    String[] columnNames = new String[]{"部门号","部门名称","部门负责人"};

    public QueryDepartmentDialog(JFrame owner){
        super(owner,"部门信息查询");
        jtf1 = new JTextField(10);
        query = new JButton("查询");
        exit = new JButton("退出");
        this.setModal(true); //聚焦当前窗口
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        /*创建按钮面板*/
        p1 = new JPanel();
        p1.add(new JLabel("部门号："));
        p1.add(jtf1);
        p2 = new JPanel();
        p2.add(query);
        p2.add(exit);
        getContentPane().add(p1,BorderLayout.NORTH);
        getContentPane().add(p2,BorderLayout.SOUTH);

        /*创建查询结果面板*/
        TableModel jTableModel = new DefaultTableModel(new String[0][0], columnNames);
        jTable1 = new JTable();
        jTable1.setModel(jTableModel);
        JScrollPane jScrollPane1 = new JScrollPane(jTable1);
        getContentPane().add(jScrollPane1,BorderLayout.CENTER);

        query.addActionListener(this);
        exit.addActionListener(this);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==query){
            try{
                Class.forName(DBcon.DRIVER);
            }catch(ClassNotFoundException a){
                System.out.println(a.toString());
            }

            if(jtf1.getText().equals("")){ //如果不输入部门号，则查询整张关系表
                try{
                    Connection con;
                    con = DriverManager.getConnection(DBcon.url, DBcon.user, DBcon.password);
                    Statement sql = con.createStatement();
                    String s = "SELECT * from department"; //执行的SQL语句
                    ResultSet rs = sql.executeQuery(s);

                    /*遍历数据库信息并显示在表格上*/
                    ArrayList<Department> queryData = new ArrayList<Department>();
                    while(rs.next()){
                        Department e1 = new Department();
                        e1.setID(rs.getString(1));
                        e1.setName(rs.getString(2));
                        e1.setManager(rs.getString(3));
                        
                        queryData.add(e1);
                    }

                    DefaultTableModel tm = new DefaultTableModel(new String[0][0],columnNames);
					jTable1.setModel(tm);
                    //添加数据到表格中
                    for(Department  data : queryData){
                        tm.addRow(new String[]{data.getID(),data.getName(),data.getManager()});
                    }

                    rs.close();
                    con.close();
                }catch(SQLException a){
                    System.out.println(a.getMessage());
                }

            }else{
                try{
                    Connection con;
                    con = DriverManager.getConnection(DBcon.url, DBcon.user, DBcon.password);
                    Statement sql = con.createStatement();
                    String dno = jtf1.getText().trim();
                    String s = "SELECT * from department where dno='"+dno+"'"; //执行的SQL语句
                    ResultSet rs = sql.executeQuery(s);
                    rs.next();
                    
                    DefaultTableModel tm = new DefaultTableModel(new String[0][0],columnNames);
                    jTable1.setModel(tm);
                    //添加数据到表格中
                    String id = rs.getString("dno").trim();
                    String name = rs.getString("dname").trim();
                    String manager = rs.getString("dmanager").trim();
                    tm.addRow(new String[]{id,name,manager});

                    rs.close();
                    con.close(); 
                }catch(SQLException a){
                    JOptionPane.showMessageDialog(null, "部门不存在！");
                    System.out.println(a.getMessage());
                }
            }
        }else if(e.getSource()==exit){
            this.dispose();;
        }
    }
}
