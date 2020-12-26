package Dialog;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import DataBase.DBcon;

@SuppressWarnings("serial")
public class AddAttendanceDialog extends JDialog implements ActionListener{
    private JTextField id;
    private JTextField attendNum;
    private JTextField askLeaveNum;
    private JTextField absentNum;
    JButton save;
    JButton reset;
    JButton cancel;

    public AddAttendanceDialog(JFrame owner){
        super(owner,"考勤记录添加");
        this.setSize(400,250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setModal(true);//当该对话框存在时，其他窗口都被屏蔽了
        
        //构造对象
        JPanel jpanel1 = new JPanel();
        int sizes = 15; //输入框的长度
        id = new JTextField(sizes);
        attendNum = new JTextField(sizes);
        askLeaveNum = new JTextField(sizes);
        absentNum = new JTextField(sizes);
        jpanel1.add(new JLabel("工号："));
        jpanel1.add(id);
        jpanel1.add(new JLabel("出勤天数："));
        jpanel1.add(attendNum);
        jpanel1.add(new JLabel("请假天数："));
        jpanel1.add(askLeaveNum);
        jpanel1.add(new JLabel("非正常考勤天数："));
        jpanel1.add(absentNum);
        jpanel1.setLayout(new GridLayout(4, 2,50,25));
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
            if(id.getText().equals("")){
                JOptionPane.showMessageDialog(null,"工号不能为空");
            }else{
                try{
                    Class.forName(DBcon.DRIVER);
                }catch(ClassNotFoundException a){
                    System.out.println(a.getMessage());
                }try{
                    Connection con;
                    //获取输入数据
                    String ID = id.getText().trim();
                    int ATTENDNUM = Integer.parseInt(attendNum.getText());
                    int ASKLEAVENUM = Integer.parseInt(askLeaveNum.getText());
                    int ABSENTNUM = Integer.parseInt(absentNum.getText());

    
                    con = DriverManager.getConnection(DBcon.url,DBcon.user,DBcon.password);
                    Statement sql=con.createStatement();
                    String s = "INSERT INTO attendance VALUES('"+ID+"','"+ATTENDNUM+"','"+ASKLEAVENUM+"','"+ABSENTNUM+"')";
    
                    sql.executeUpdate(s);
                    sql.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "员工考勤记录添加成功");
                    super.setVisible(false);
                }catch(SQLException a){
                    System.out.println(a.getMessage());
                }
            }
            
        }else if(e.getSource()==reset){
            id.setText("");
            attendNum.setText("");
            askLeaveNum.setText("");
            absentNum.setText("");
        }

        else if(e.getSource()==cancel){
            this.dispose();
        }
    }
    
}
