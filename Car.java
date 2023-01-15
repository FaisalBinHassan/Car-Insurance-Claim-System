import java.util.Date;


public class Car {

    private String CarPlate;
    private String CarType;
    private String Brand;
    private String CarModel;
    private String CarColor;
    private int BuiltYear;
    
    public Car() {
    }
    
    public Car(String CarPlate,String CarType,String Brand,String CarModel,String CarColor,int BuiltYear)
    {
        this.Brand = Brand;
        this.BuiltYear = BuiltYear;
        this.CarColor = CarColor;
        this.CarModel = CarModel;
        this.CarPlate = CarPlate;
        this.CarType = CarType;
    }

    public String getCarPlate() {
        return CarPlate;
    }

    public void setCarPlate(String CarPlate) {
        this.CarPlate = CarPlate;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String CarType) {
        this.CarType = CarType;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getCarModel() {
        return CarModel;
    }

    public void setCarModel(String CarModel) {
        this.CarModel = CarModel;
    }

    public String getCarColor() {
        return CarColor;
    }

    public void setCarColor(String CarColor) {
        this.CarColor = CarColor;
    }

    public int getBuiltYear() {
        return BuiltYear;
    }

    public void setBuiltYear(int BuiltYear) {
        this.BuiltYear = BuiltYear;
    }

    public String toString() {
        return "Car No.: "+CarPlate+"	Type: "
                            +CarType+"	Brand: "
                            +Brand+"	Model: "
                            +CarModel+"	Color: "
                            +CarColor+"	Mfg. Year: "
                            +BuiltYear;
    }
}
