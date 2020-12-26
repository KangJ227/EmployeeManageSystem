package Dialog;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import DataBase.DBcon;

@SuppressWarnings("serial")
public class DeleteAttendanceDialog extends JDialog implements ActionListener{
    /**
     *弹出删除薪资的窗口
     */

    JButton delete,exit;
    JTextField jtf1; //输入要删除员工的工号
    JPanel p1,p2;

    public DeleteAttendanceDialog(JFrame owner){
        super(owner,"删除员工考勤记录");
        jtf1 = new JTextField(10);
        delete = new JButton("提交");
        exit = new JButton("退出");
        this.setSize(400,250);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);


        /*创建按钮面板*/
        p1 = new JPanel();
        p1.add(new JLabel("工号："));
        p1.add(jtf1);
        p2 = new JPanel();
        p2.add(delete);
        p2.add(exit);
        getContentPane().add(p1,BorderLayout.CENTER);
        getContentPane().add(p2,BorderLayout.SOUTH);

        delete.addActionListener(this);
        exit.addActionListener(this);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==delete){
            try{
                Class.forName(DBcon.DRIVER);
            }catch(ClassNotFoundException a){
                System.out.println(a.toString());
            }

            if(jtf1.getText().equals("")){
                JOptionPane.showMessageDialog(null,"工号不能为空");
            }else{
                try{
                    Connection con;
                    con = DriverManager.getConnection(DBcon.url, DBcon.user, DBcon.password);
                    String id = jtf1.getText().trim();
                    Statement sql = con.createStatement();
                    String s = "delete from attendance where eno='"+id+"'"; //执行的SQL语句
                    int i = sql.executeUpdate(s);
                    if(i==0){
                        JOptionPane.showMessageDialog(null,"不存在该数据！");
                    }else{
                        JOptionPane.showMessageDialog(null,"删除员工考勤记录成功！");
                        super.setVisible(false);
                    }
                    con.close();
                }catch(SQLException a){
                    System.out.println(a.toString());
                }
            }
        }else if(e.getSource()==exit){
            this.dispose();
        }
    }
}

