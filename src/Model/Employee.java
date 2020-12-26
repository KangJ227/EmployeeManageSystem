package Model;


public class Employee {
    private String ID;
    private String name;
    private String sex;
    private String birthday;
    private String address;
    private String phoneNumber;
    private String deptNumber;

    public Employee(){
        
    }
    
    public Employee(String ID,String name,String sex,String birthday,String address,String phoneNumber,String deptNumber){
        this.ID = ID;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.deptNumber = deptNumber;
    }

    public String getID(){
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex(){
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    
    public String getBirthday(){
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getPhonenumber(){
        return phoneNumber;
    }

    public void setPhonenumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getDeptnumber(){
        return deptNumber;
    }

    public void setDeptnumber(String deptNumber){
        this.deptNumber = deptNumber;
    }
}
