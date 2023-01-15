import java.util.*;

public class Owner {

    private String nationalID;
    private String First_name;
    private String Last_name;
    private Date dob;
    private int numberOFclaims;
    
    public Owner() {
    }
    
    public Owner(String nationalID, String First_name, String Last_name , Date dob )
    {
        this.First_name = First_name;
        this.Last_name = Last_name;
        this.nationalID = nationalID;
        this.dob = dob;    
        
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String First_name) {
        this.First_name = First_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String Last_name) {
        this.Last_name = Last_name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    public void INCREMENTnumberOFclaims(){
        this.setNumberOFclaims(this.getNumberOFclaims() + 1);
    }
    public String toString() {
        return "	Owner ID: "+nationalID
                +"	Name: "+First_name+" "+Last_name;
                
    }

    public int getNumberOFclaims() {
        return numberOFclaims;
    }

    public void setNumberOFclaims(int numberOFclaims) {
        this.numberOFclaims = numberOFclaims;
    }
    
}
