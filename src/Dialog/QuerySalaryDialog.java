package Dialog;

import javax.swing.*;
import java.awt.event.*;   
import java.sql.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.*;
import DataBase.DBcon;
import Model.Salary;

@SuppressWarnings("serial")
public class QuerySalaryDialog extends JDialog implements ActionListener{
    JTable jTable1;
    JButton query,exit;
    JTextField jtf1; //用以输入工号的查询框
    JPanel p1,p2;
    String[] columnNames = new String[]{"工号","底薪","奖金","罚款","五险一金","缴税额","实发工资"};

    public QuerySalaryDialog(JFrame owner){
        super(owner,"员工薪资信息查询");
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
                    String s = "select * from salary"; //执行的SQL语句
                    ResultSet rs = sql.executeQuery(s);

                    /*遍历数据库信息并显示在表格上*/
                    ArrayList<Salary> queryData = new ArrayList<Salary>();
                    while(rs.next()){
                        Salary e1 = new Salary();
                        e1.setID(rs.getString(1).trim());
                        e1.setBaseSalary(rs.getDouble(2));
                        e1.setBonus(rs.getDouble(3));
                        e1.setFine(rs.getDouble(4));
                        e1.setInsurance(rs.getDouble(5));
                        e1.setTax(rs.getDouble(6));
                        e1.setAfterTaxSalary(rs.getDouble(7));
                        
                        queryData.add(e1);
                    }

                    DefaultTableModel tm = new DefaultTableModel(new String[0][0],columnNames);
					jTable1.setModel(tm);
                    //添加数据到表格中
                    for(Salary data : queryData){
                        tm.addRow(new String[]{data.getID(),Double.toString(data.getBaseSalary()),Double.toString(data.getBonus()),Double.toString(data.getFine()),Double.toString(data.getInsurance()),Double.toString(data.getTax()),Double.toString(data.getAfterTaxSalary())});
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
                    String s = "select * from salary where eno='"+id+"'"; //执行的SQL语句
                    ResultSet rs = sql.executeQuery(s);
                    
                    rs.next();
                    id = rs.getString(1);
                    String baseSalary = rs.getString(2);
                    String bonus = rs.getString(3);
                    String fine = rs.getString(4);
                    String insurance = rs.getString(5);
                    String tax = rs.getString(6);
                    String afterTaxSalary = rs.getString(7);
                    String[] rowData = new String[]{id,baseSalary,bonus,fine,insurance,tax,afterTaxSalary};

                    DefaultTableModel tm = new DefaultTableModel(new String[0][0],columnNames);
                    jTable1.setModel(tm);
                    tm.addRow(rowData);
                    rs.close();
                    con.close(); 
                }catch(SQLException a){
                    JOptionPane.showMessageDialog(null, "员工不存在！");
                    System.out.println(a.getMessage());
                }
            }
        }else if(e.getSource()==exit){
            this.dispose();;
        }
    }
}
