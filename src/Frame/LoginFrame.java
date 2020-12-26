package Frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import DataBase.DBcon;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame implements ActionListener{
    JLabel userLabel,passwordLabel;
    JTextField userTextField;
    JPasswordField userPasswordField;
    JButton yes,no,exit;
    Container c;

    LoginFrame(){
        super("用户登录");
		this.setBounds(700, 300, 550, 400);
		this.setLayout(null);
		this.setResizable(false);

        userLabel = new JLabel("用户名:");
        userLabel.setBounds(90, 30, 100, 100);
        passwordLabel = new JLabel("密  码:");
        passwordLabel.setBounds(90, 90, 100, 200);

        userTextField = new JTextField(15);
        userTextField.setBounds(140, 70, 300, 25);
        userPasswordField = new JPasswordField(15);
		userPasswordField.setBounds(140, 180, 300, 25);

        yes = new JButton("确定");
        yes.setBounds(100, 250, 90, 20);
        no = new JButton("取消");
        no.setBounds(230, 250, 90, 20);
        exit = new JButton("退出");
        exit.setBounds(360, 250, 90, 20);

        yes.addActionListener(this);
        no.addActionListener(this);
        exit.addActionListener(this);

        this.add(userLabel);
        this.add(passwordLabel);
        this.add(userTextField);
        this.add(userPasswordField);
        this.add(yes);
        this.add(no);
        this.add(exit);

        this.setVisible(true);
    }


    @SuppressWarnings("deprecation")
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==no){
            userTextField.setText("");
            userPasswordField.setText("");
            return;
        }
        if(e.getSource()==exit){
            System.exit(0);
        }
        else{
            if(userTextField.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null, "用户名不能为空");
                return;
            }
            if(userPasswordField.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null, "密码不能为空");
                return;
            }

            String user = userTextField.getText().trim();
            String password = userPasswordField.getText().trim();
            if(compare(user,password)){
                JOptionPane.showMessageDialog(null, "登录成功！");
                super.setVisible(false);
                new MainFrame();
            }else{
                JOptionPane.showMessageDialog(null, "用户名或密码错误！");
            }
        }

    }

    public boolean compare(String username, String password) {
        boolean m = false;
        Statement statement;
        Connection con;
        try{
            Class.forName(DBcon.DRIVER);
        }catch(ClassNotFoundException a){
            System.out.println(a.getMessage());
        }
        try {
            ResultSet rs;
            con = DriverManager.getConnection(DBcon.url,DBcon.user,DBcon.password);
            String sql = "select Password from login where Username='"+username+"'";
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            if (rs.next()){
                String pa = rs.getString(1);
                if (pa.equals(password)){
                    m = true;
                }
            }

            rs.close();
            con.close();
            statement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }
    public static void main(String[] args) {
        new LoginFrame();
    }
}
