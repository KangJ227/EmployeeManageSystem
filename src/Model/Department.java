package Model;

public class Department{
    String ID;
    String name;
    String manager;

    public Department(){

    }

    public Department(String ID,String name,String manager){
        this.ID = ID;
        this.name = name;
        this.manager = manager;
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

    public String getManager(){
        return manager;
    }

    public void setManager(String manager){
        this.manager = manager;
    }
}