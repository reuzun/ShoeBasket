package ceng.estu.model;

/**
 * @author reuzun
 */
public class Model {

    public Model() {
    }



    public int modelID;
    public String modelName;
    public String brandName;

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public ModelType getType() {
        return type;
    }

    public void setType(ModelType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(double customerRating) {
        this.customerRating = customerRating;
    }

    public ModelType type;
    public double price;
    public double customerRating;

    public Model(int modelID, String modelName, String brandName, ModelType type, double price, double customerRating) {
        this.modelID = modelID;
        this.modelName = modelName;
        this.brandName = brandName;
        this.type = type;
        this.price = price;
        this.customerRating = customerRating;
    }

    @Override
    public String toString() {
        return
                "Model : " + modelName +
                "\nBrand :" + brandName +
                "\nType : " + type +
                "\nPrice : " + price +
                "\nRating : " + customerRating
                ;
    }

    /*

    public static int id = 0;
    public int shoeId = 0;

    public Model(){
        id++;
        shoeId = id;
    }

    @Override
    public String toString() {
        return "Gucci's Boot\nBlack\nCount:48 Left\nStar:3.5*";*/
}

