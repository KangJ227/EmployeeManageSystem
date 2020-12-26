package DataBase;

import java.sql.*;

public class DBcon {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String url = "jdbc:mysql://host:3306/employeesystem?characterEncoding=UTF-8";//数据库的地址
    public static final String user = "employeeUser";//数据库账号
    public static final String password = "password";//数据库密码
    java.sql.Connection con = null;
    Statement statement;

    public DBcon(){
        try{
            init();//数据库初始化
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void init() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);
        statement = con.createStatement();
    }

}
