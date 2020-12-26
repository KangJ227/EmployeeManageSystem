package Dialog;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import DataBase.DBcon;

@SuppressWarnings("serial")
public class AddSalaryDialog extends JDialog implements ActionListener{
    /**
     *弹出薪资的添加窗口
     */
    private JTextField id;
    private JTextField baseSalary;
    private JTextField bonus;
    private JTextField fine;
    private JTextField insurance;
    private JTextField tax;
    JButton save;
    JButton reset;
    JButton cancel;

    public AddSalaryDialog(JFrame owner){
        super(owner,"薪资信息添加");
        this.setSize(400,250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setModal(true);//当该对话框存在时，其他窗口都被屏蔽了
        
        //构造对象
        JPanel jpanel1 = new JPanel();
        int sizes = 15; //输入框的长度
        id = new JTextField(sizes);
        baseSalary = new JTextField(sizes);
        bonus = new JTextField(sizes);
        fine = new JTextField(sizes);
        insurance = new JTextField(sizes);
        tax = new JTextField(sizes);
        jpanel1.add(new JLabel("工号："));
        jpanel1.add(id);
        jpanel1.add(new JLabel("底薪："));
        jpanel1.add(baseSalary);
        jpanel1.add(new JLabel("奖金："));
        jpanel1.add(bonus);
        jpanel1.add(new JLabel("罚款："));
        jpanel1.add(fine);
        jpanel1.add(new JLabel("五险一金："));
        jpanel1.add(insurance);
        jpanel1.add(new JLabel("缴税额："));
        jpanel1.add(tax);
        jpanel1.setLayout(new GridLayout(6, 2));
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
            if(id.getText().equals("")||baseSalary.getText().equals("")){
                JOptionPane.showMessageDialog(null,"工号与底薪不能为空");
            }else{
                try{
                    Class.forName(DBcon.DRIVER);
                }catch(ClassNotFoundException a){
                    System.out.println(a.getMessage());
                }try{
                    Connection con;
                    //获取输入数据
                    String ID = id.getText().trim();
                    double BASESALARY = Double.parseDouble(baseSalary.getText());
                    double BONUS = Double.parseDouble(bonus.getText());
                    double FINE = Double.parseDouble(fine.getText());
                    double INSURANCE = Double.parseDouble(insurance.getText());
                    double TAX = Double.parseDouble(tax.getText());
                    double afterTaxSalary = BASESALARY+BONUS-FINE-INSURANCE-TAX;
    
                    con = DriverManager.getConnection(DBcon.url,DBcon.user,DBcon.password);
                    Statement sql=con.createStatement();
                    String s = "INSERT INTO salary VALUES('"+ID+"','"+BASESALARY+"','"+BONUS+"','"+FINE+"','"+INSURANCE+"','"+TAX+"','"+afterTaxSalary+"')";
    
                    sql.executeUpdate(s);
                    sql.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "员工薪资添加成功");
                    super.setVisible(false);
                }catch(SQLException a){
                    System.out.println(a.getMessage());
                }
            }
            
        }else if(e.getSource()==reset){
            id.setText("");
            baseSalary.setText("");
            bonus.setText("");
            fine.setText("");
            insurance.setText("");
            tax.setText("");
        }

        else if(e.getSource()==cancel){
            this.dispose();
        }
    }
    
}
