package ceng.estu.model;

/**
 * @author reuzun
 */
public class Model {

    public Model() {
    }

    int modelID;
    String modelName;
    String brandName;
    ModelType type;
    double price;
    double customerRating;

    public Model(int modelID, String modelName, String brandName, ModelType type, double price, double customerRating) {
        this.modelID = modelID;
        this.modelName = modelName;
        this.brandName = brandName;
        this.type = type;
        this.price = price;
        this.customerRating = customerRating;
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

