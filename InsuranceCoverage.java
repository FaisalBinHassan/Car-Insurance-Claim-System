public class InsuranceCoverage {

    private int insuranceCoverageCode;
    private String description;
    private double amount;
    
    
    public InsuranceCoverage(int insuranceCoverageCode, String description, double amount) 
    {
        this.amount = amount;
        this.description = description;
        this.insuranceCoverageCode = insuranceCoverageCode ;
    }

    public int getInsuranceCoverageCode() {
        return insuranceCoverageCode;
    }

    public void setInsuranceCoverageCode(int insuranceCoverageCode) {
        this.insuranceCoverageCode = insuranceCoverageCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String toString() {
        return "	InsuranceCoverage Code: "+insuranceCoverageCode
                +"	InsuranceCoverage Description: "+description.replaceAll("_", " ");
    }
}
