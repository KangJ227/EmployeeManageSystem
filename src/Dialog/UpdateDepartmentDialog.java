package Dialog;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import DataBase.DBcon;

@SuppressWarnings("serial")
public class UpdateDepartmentDialog extends JDialog implements ActionListener{
    /**
     *弹出员工的添加窗口
     */
    private JTextField id;
    private JTextField name;
    private JTextField manager;
    JButton save;
    JButton reset;
    JButton cancel;

    public UpdateDepartmentDialog(JFrame owner){
        super(owner,"部门信息修改");
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
        jpanel1.add(new JLabel("部门名称："));
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
                String s = "UPDATE department SET dno='"+ID+"',dname='"+NAME+"',dmanager='"+MANAGER+"' where dno='"+ID+"'";

                sql.executeUpdate(s);
                sql.close();
                con.close();
                JOptionPane.showMessageDialog(null, "部门修改成功");
                super.setVisible(false);
            }catch(SQLException a){
                JOptionPane.showMessageDialog(null, "没有输入全部数据或输入了非法数据");
                System.out.println(a.getMessage());
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
