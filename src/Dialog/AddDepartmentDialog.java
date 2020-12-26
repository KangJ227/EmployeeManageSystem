package Dialog;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import DataBase.DBcon;

@SuppressWarnings("serial")
public class AddDepartmentDialog extends JDialog implements ActionListener{
    /**
     *弹出部门的添加窗口
     */
    private JTextField id;
    private JTextField name;
    private JTextField manager;
    JButton save;
    JButton reset;
    JButton cancel;

    public AddDepartmentDialog(JFrame owner){
        super(owner,"部门信息添加");
        this.setSize(400,250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setModal(true);//当该对话框存在时，其他窗口都被屏蔽了
        
        //构造对象
        JPanel jpanel1 = new JPanel();
        int sizes = 15; //输入框的长度
        id = new JTextField(sizes);
        name = new JTextField(sizes);
        manager = new JTextField(sizes);
        jpanel1.add(new JLabel("部门号："));
        jpanel1.add(id);
        jpanel1.add(new JLabel("姓名："));
        jpanel1.add(name);
        jpanel1.add(new JLabel("部门负责人："));
        jpanel1.add(manager);
        jpanel1.setLayout(new GridLayout(3,2,5,50));
		getContentPane().add(jpanel1,BorderLayout.CENTER);

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
            if(id.getText().equals("")||name.getText().equals("")){
                JOptionPane.showMessageDialog(null,"部门号与部门名称不能为空");
            }else{
                try{
                    Class.forName(DBcon.DRIVER);
                }catch(ClassNotFoundException a){
                    System.out.println(a.getMessage());
                }try{
                    Connection con;
                    //获取输入数据
                    String ID = id.getText().trim();
                    String NAME = name.getText().trim();
                    String MANAGER = manager.getText().trim();
    
                    con = DriverManager.getConnection(DBcon.url,DBcon.user,DBcon.password);
                    Statement sql=con.createStatement();
                    String s = "INSERT INTO department VALUES('"+ID+"','"+NAME+"','"+MANAGER+"')";
    
                    sql.executeUpdate(s);
                    sql.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "部门添加成功");
                    super.setVisible(false);
                }catch(SQLException a){
                    System.out.println(a.getMessage());
                }
            }

        }else if(e.getSource()==reset){
            id.setText("");
            name.setText("");
            manager.setText("");
        }

        else if(e.getSource()==cancel){
            this.dispose();
        }
    }
}
