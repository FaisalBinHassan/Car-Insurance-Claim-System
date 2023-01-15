import java.util.*;

public class Claim {
    
    private long ClaimNo;
    private String Location;
    private Date ClaimDate;
    private Boolean hasPremium;
    private Boolean hasSpecialOffer;
    
    public Claim(long ClaimNo , String Location, Date ClaimDate, Boolean hasPremium, Boolean hasSpecialOffer)
    {
        this.ClaimDate = ClaimDate ;
        this.ClaimNo = ClaimNo;
        this.Location = Location;
        this.hasPremium = hasPremium;
        this.hasSpecialOffer = hasSpecialOffer;
    }

    public long getClaimNo() {
        return ClaimNo;
    }

    public void setClaimNo(int ClaimNo) {
        this.ClaimNo = ClaimNo;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public Date getClaimDate() {
        return ClaimDate;
    }

    public void setClaimDate(Date ClaimDate) {
        this.ClaimDate = ClaimDate;
    }

    public Boolean getHasPremium() {
        return hasPremium;
    }

    public void setHasPremium(Boolean hasPremium) {
        this.hasPremium = hasPremium;
    }

    public Boolean getHasSpecialOffer() {
        return hasSpecialOffer;
    }

    public void setHasSpecialOffer(Boolean hasSpecialOffer) {
        this.hasSpecialOffer = hasSpecialOffer;
    }
    
    public double CalculateFinalClaimAmmount() {
        return 2.333333;
    }

    
}
