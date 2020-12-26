package Dialog;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import DataBase.DBcon;

@SuppressWarnings("serial")
public class UpdateEmployeeDialog extends JDialog implements ActionListener{
    /**
     *弹出员工的添加窗口
     */
    private JTextField id;
    private JTextField name;
    private JTextField sex;
    private JTextField birthday;
    private JTextField address;
    private JTextField phoneNumber;
    private JTextField deptNumber;
    JButton save;
    JButton reset;
    JButton cancel;

    public UpdateEmployeeDialog(JFrame owner){
        super(owner,"员工信息修改");
        this.setSize(400,250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setModal(true);//当该对话框存在时，其他窗口都被屏蔽了
        
        //构造对象
        JPanel jpanel1 = new JPanel();
        int sizes = 15; //输入框的长度
        id = new JTextField(sizes);
        name = new JTextField(sizes);
        sex = new JTextField(sizes);
        birthday = new JTextField(sizes);
        address = new JTextField(sizes);
        phoneNumber = new JTextField(sizes);
        deptNumber = new JTextField(sizes);
        jpanel1.add(new JLabel("工号："));
        jpanel1.add(id);
        jpanel1.add(new JLabel("姓名："));
        jpanel1.add(name);
        jpanel1.add(new JLabel("性别："));
        jpanel1.add(sex);
        jpanel1.add(new JLabel("生日："));
        jpanel1.add(birthday);
        jpanel1.add(new JLabel("住址："));
        jpanel1.add(address);
        jpanel1.add(new JLabel("手机："));
        jpanel1.add(phoneNumber);
        jpanel1.add(new JLabel("部门："));
        jpanel1.add(deptNumber);
        jpanel1.setLayout(new GridLayout(7, 2));
		getContentPane().add(jpanel1);

        //构造按钮面板
        JPanel jpanel2 = new JPanel();
        save = new JButton("提交");
        reset = new JButton("重置");
        cancel = new JButton("取消");
        jpanel2.add(save);
        jpanel2.add(reset);
        jpanel2.add(cancel);
        getContentPane().add(jpanel2, BorderLayout.SOUTH);
        
        save.addActionListener(this);
        reset.addActionListener(this);
        cancel.addActionListener(this);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==save){
            try{
                Class.forName(DBcon.DRIVER);
            }catch(ClassNotFoundException a){
                System.out.println(a.getMessage());
            }
            if(id.getText().equals("")){
                JOptionPane.showMessageDialog(null,"工号与姓名不能为空");
            }else{
                try{
                    Connection con;
                    //获取输入数据
                    String ID = id.getText().trim();
                    String NAME = name.getText().trim();
                    String SEX = sex.getText().trim();
                    String BIRTHDAY = birthday.getText().trim();
                    String ADDRESS = address.getText().trim();
                    String PHONE = phoneNumber.getText().trim();
                    String DEPT = deptNumber.getText().trim();
    
                    con = DriverManager.getConnection(DBcon.url,DBcon.user,DBcon.password);
                    Statement sql=con.createStatement();
                    String s = "UPDATE employee SET eno='"+ID+"',ename='"+NAME+"',esex='"+SEX+"',ebirth='"+BIRTHDAY+"',eaddress='"+ADDRESS+"',ephoneno='"+PHONE+"',sno='"+DEPT+"' where eno='"+ID+"'";
    
                    sql.executeUpdate(s);
                    sql.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "员工修改成功");
                    super.setVisible(false);
                }catch(SQLException a){
                    JOptionPane.showMessageDialog(null, "没有输入全部数据或输入了非法数据");
                    System.out.println(a.getMessage());
                }
            }
            
        }else if(e.getSource()==reset){
            id.setText("");
            name.setText("");
            sex.setText("");
            birthday.setText("");
            address.setText("");
            phoneNumber.setText("");
            deptNumber.setText("");
        }

        else if(e.getSource()==cancel){
            this.dispose();
        }
    }
}
