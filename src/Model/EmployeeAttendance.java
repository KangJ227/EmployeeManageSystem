package Model;

public class EmployeeAttendance {
    String ID;
    int attendNum;
    int askLeaveNum;
    int absentNum;
    
    public EmployeeAttendance(){
        
    }

    public EmployeeAttendance(String ID, int attendNum, int askLeaveNum, int absentNum) {
        this.ID = ID;
        this.attendNum = attendNum;
        this.askLeaveNum = askLeaveNum;
        this.absentNum = absentNum;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public int getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(int attendNum) {
        this.attendNum = attendNum;
    }

    public int getAskLeaveNum() {
        return askLeaveNum;
    }

    public void setAskLeaveNum(int askLeaveNum) {
        this.askLeaveNum = askLeaveNum;
    }

    public int getAbsentNum() {
        return absentNum;
    }

    public void setAbsentNum(int absentNum) {
        this.absentNum = absentNum;
    }

}
