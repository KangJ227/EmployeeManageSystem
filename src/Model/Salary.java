package Model;

public class Salary {
    String ID;
    double baseSalary;
    double bonus;
    double fine;
    double insurance;
    double tax;
    double afterTaxSalary;

    public Salary(){
        
    }

    public Salary(String ID, double baseSalary, double bonus, double fine, double insurance, double tax) {
        this.ID = ID;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.fine = fine;
        this.insurance = insurance;
        this.tax = tax;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public double getInsurance() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getAfterTaxSalary(){
        afterTaxSalary = baseSalary + bonus - fine - insurance - tax;
        return afterTaxSalary;
    }

    public void setAfterTaxSalary(double afterTaxSalary) {
        this.afterTaxSalary = afterTaxSalary;
    }
}
