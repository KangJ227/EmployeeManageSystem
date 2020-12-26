package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Dialog.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener{
    //声明菜单
    JMenuBar menuBar = new JMenuBar();
    JMenu employeeMenu = new JMenu("员工管理");
    JMenuItem queryEmployeeMenuItem = new JMenuItem("查询员工");
    JMenuItem addEmployeeMenuItem = new JMenuItem("添加员工");
    JMenuItem updateEmployeeMenuItem = new JMenuItem("修改员工");
    JMenuItem deleteEmployeeMenuItem = new JMenuItem("删除员工");

    JMenu departmentMenu = new JMenu("部门管理");
    JMenuItem queryDepartMenuItem = new JMenuItem("查询部门");
    JMenuItem addDepartmentMenuItem = new JMenuItem("添加部门");
    JMenuItem updateDepartmentMenuItem = new JMenuItem("修改部门");
    JMenuItem deleteDepartmentMenuItem = new JMenuItem("删除部门");

    JMenu attendanceMenu = new JMenu("考勤管理");
    JMenuItem queryAttendanceMenuItem = new JMenuItem("查询考勤记录");
    JMenuItem addAttendanceMenuItem = new JMenuItem("添加考勤记录");
    JMenuItem updateAttendanceMenuItem = new JMenuItem("修改考勤记录");
    JMenuItem deleteAttendanceMenuItem = new JMenuItem("删除考勤记录");

    JMenu salaryMenu = new JMenu("薪资管理");
    JMenuItem querySalaryMenuItem = new JMenuItem("查询薪资");
    JMenuItem addSalaryMenuItem = new JMenuItem("添加薪资");
    JMenuItem updateSalaryMenuItem = new JMenuItem("修改薪资");
    JMenuItem deleteSalaryMenuItem = new JMenuItem("删除薪资");

    //欢迎图片
	ImageIcon labIma=new ImageIcon(MainFrame.class.getResource("/Image/1.jpg"));

    //声明对话框
    QueryEmployeeDialog QEdialog;
    AddEmployeeDialog AEdialog;
    UpdateEmployeeDialog UEdialog;
    DeleteEmployeeDialog DEdialog;

    QueryDepartmentDialog QDdialog;
    AddDepartmentDialog ADdialog;
    UpdateDepartmentDialog UDdialog;
    DeleteDepartmentDialog DDdialog;

    QuerySalaryDialog QSdialog;
    AddSalaryDialog ASdialog;
    UpdateSalaryDialog USdialog;
    DeleteSalaryDialog DSdialog;

    QueryAttendanceDialog QADdialog;
    AddAttendanceDialog AADdialog;
    UpdateAttendanceDialog UADdialog;
    DeleteAttendanceDialog DADdialog;
    

    public MainFrame(){
        this.setTitle("员工管理系统");

        //构造员工管理的菜单
        this.setJMenuBar(menuBar);
        menuBar.add(employeeMenu);
        employeeMenu.add(queryEmployeeMenuItem);
        employeeMenu.add(addEmployeeMenuItem);
        employeeMenu.add(updateEmployeeMenuItem);
        employeeMenu.add(deleteEmployeeMenuItem);

        queryEmployeeMenuItem.addActionListener(this);
        addEmployeeMenuItem.addActionListener(this);
        updateEmployeeMenuItem.addActionListener(this);
        deleteEmployeeMenuItem.addActionListener(this);

        //构造部门管理的菜单
        menuBar.add(departmentMenu);
        departmentMenu.add(queryDepartMenuItem);
        departmentMenu.add(addDepartmentMenuItem);
        departmentMenu.add(updateDepartmentMenuItem);
        departmentMenu.add(deleteDepartmentMenuItem);

        queryDepartMenuItem.addActionListener(this);
        addDepartmentMenuItem.addActionListener(this);
        updateDepartmentMenuItem.addActionListener(this);
        deleteDepartmentMenuItem.addActionListener(this);

        //构造考勤管理的菜单
        menuBar.add(attendanceMenu);
        attendanceMenu.add(queryAttendanceMenuItem);
        attendanceMenu.add(addAttendanceMenuItem);
        attendanceMenu.add(updateAttendanceMenuItem);
        attendanceMenu.add(deleteAttendanceMenuItem);

        queryAttendanceMenuItem.addActionListener(this);
        addAttendanceMenuItem.addActionListener(this);
        updateAttendanceMenuItem.addActionListener(this);
        deleteAttendanceMenuItem.addActionListener(this);

        //构造薪资管理的菜单
        menuBar.add(salaryMenu);
        salaryMenu.add(querySalaryMenuItem);
        salaryMenu.add(addSalaryMenuItem);
        salaryMenu.add(updateSalaryMenuItem);
        salaryMenu.add(deleteSalaryMenuItem);

        //构造欢迎界面
        this.add(new JLabel(labIma));

        //注册监听器
        querySalaryMenuItem.addActionListener(this);
        addSalaryMenuItem.addActionListener(this);
        updateSalaryMenuItem.addActionListener(this);
        deleteSalaryMenuItem.addActionListener(this);

        this.setBounds(300,300,labIma.getIconWidth(),labIma.getIconHeight());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        //主菜单控制
        if(e.getSource()==queryEmployeeMenuItem){
            QEdialog = new QueryEmployeeDialog(this);
        }
        else if(e.getSource()==addEmployeeMenuItem){
            AEdialog = new AddEmployeeDialog(this);
        }
        else if(e.getSource()==updateEmployeeMenuItem){
            UEdialog = new UpdateEmployeeDialog(this);
        }
        else if(e.getSource()==deleteEmployeeMenuItem){
            DEdialog = new DeleteEmployeeDialog(this);
        }


        else if(e.getSource()==queryDepartMenuItem){
            QDdialog = new QueryDepartmentDialog(this);
        }
        else if(e.getSource()==addDepartmentMenuItem){
            ADdialog = new AddDepartmentDialog(this);
        }
        else if(e.getSource()==updateDepartmentMenuItem){
            UDdialog = new UpdateDepartmentDialog(this);
        }
        else if(e.getSource()==deleteDepartmentMenuItem){
            DDdialog = new DeleteDepartmentDialog(this);
        }


        else if(e.getSource()==querySalaryMenuItem){
            QSdialog = new QuerySalaryDialog(this);
        }
        else if(e.getSource()==addSalaryMenuItem){
            ASdialog = new AddSalaryDialog(this);
        }
        else if(e.getSource()==updateSalaryMenuItem){
            USdialog = new UpdateSalaryDialog(this);
        }
        else if(e.getSource()==deleteSalaryMenuItem){
            DSdialog = new DeleteSalaryDialog(this);
        }


        else if(e.getSource()==queryAttendanceMenuItem){
            QADdialog = new QueryAttendanceDialog(this);
        }
        else if(e.getSource()==addAttendanceMenuItem){
            AADdialog = new AddAttendanceDialog(this);
        }
        else if(e.getSource()==updateAttendanceMenuItem){
            UADdialog = new UpdateAttendanceDialog(this);
        }
        else if(e.getSource()==deleteAttendanceMenuItem){
            DADdialog = new DeleteAttendanceDialog(this);
        }
    }

}
