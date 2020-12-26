package Dialog;

import javax.swing.*;
import java.awt.event.*;   
import java.sql.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.*;
import DataBase.DBcon;
import Model.Employee;

@SuppressWarnings("serial")
public class QueryEmployeeDialog extends JDialog implements ActionListener{
    /**
     *弹出员工的查询窗口
     */
    JTable jTable1;
    JButton query,exit;
    JTextField jtf1; //用以输入工号的查询框
    JPanel p1,p2;
    String[] columnNames = new String[]{"工号","姓名","性别","生日","住址","手机","部门"};

    public QueryEmployeeDialog(JFrame owner){
        super(owner,"员工信息查询");
        jtf1 = new JTextField(10);
        query = new JButton("查询");
        exit = new JButton("退出");
        this.setModal(true); //聚焦当前窗口
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        /*创建按钮面板*/
        p1 = new JPanel();
        p1.add(new JLabel("工号："));
        p1.add(jtf1);
        p2 = new JPanel();
        p2.add(query);
        p2.add(exit);

        /*创建查询结果面板*/
        TableModel jTableModel = new DefaultTableModel(new String[0][0], columnNames);
        jTable1 = new JTable();
        jTable1.setModel(jTableModel);
        JScrollPane jScrollPane1 = new JScrollPane(jTable1);

        getContentPane().add(p1,BorderLayout.NORTH);
        getContentPane().add(p2,BorderLayout.SOUTH);
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

            if(jtf1.getText().equals("")){ //如果不输入工号，则查询整张关系表
                try{
                    Connection con;
                    con = DriverManager.getConnection(DBcon.url, DBcon.user, DBcon.password);
                    Statement sql = con.createStatement();
                    String s = "select * from employee"; //执行的SQL语句
                    ResultSet rs = sql.executeQuery(s);

                    /*遍历数据库信息并显示在表格上*/
                    ArrayList<Employee> queryData = new ArrayList<Employee>();
                    while(rs.next()){
                        Employee e1 = new Employee();
                        e1.setID(rs.getString("eno").trim());
                        e1.setName(rs.getString("ename").trim());
                        e1.setSex(rs.getString("esex").trim());
                        e1.setBirthday(rs.getString("ebirth").trim());
                        e1.setAddress(rs.getString("eaddress").trim());
                        e1.setPhonenumber(rs.getString("ephoneno").trim());
                        e1.setDeptnumber(rs.getString("sno").trim());
                        
                        queryData.add(e1);
                    }

                    DefaultTableModel tm = new DefaultTableModel(new String[0][0],columnNames);
					jTable1.setModel(tm);
                    //添加数据到表格中
                    for(Employee data : queryData){
                        tm.addRow(new String[]{data.getID(),data.getName(),data.getSex(),data.getBirthday(),data.getAddress(),data.getPhonenumber(),data.getDeptnumber()});
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
                    String id = jtf1.getText().trim();;
                    String s = "SELECT * from employee where eno='"+id+"'"; //执行的SQL语句
                    ResultSet rs = sql.executeQuery(s);
                    
                    rs.next();
                    id = rs.getString(1);
                    String name = rs.getString(2);
                    String sex = rs.getString(3);
                    String birth = rs.getString(4);
                    String address = rs.getString(5);
                    String phoneNo = rs.getString(6);
                    String deptNo = rs.getString(7);
                    String[] rowData = new String[]{id,name,sex,birth,address,phoneNo,deptNo};

                    DefaultTableModel tm = new DefaultTableModel(new String[0][0],columnNames);
                    jTable1.setModel(tm);
                    tm.addRow(rowData);
                    rs.close();
                    con.close(); 
                }catch(SQLException a){
                    JOptionPane.showMessageDialog(null, "用户不存在！");
                    System.out.println(a.getMessage());
                }
            }
        }else if(e.getSource()==exit){
            this.dispose();;
        }
    }
}
