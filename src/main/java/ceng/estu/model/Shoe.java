package ceng.estu.model;

/**
 * @author reuzun
 */
public class Shoe {

    int shoeID;
    int modelID;
    int size;
    String color;
    int count;

    public Shoe(int shoeID, int modelID, int size, String color, int count) {
        this.shoeID = shoeID;
        this.modelID = modelID;
        this.size = size;
        this.color = color;
        this.count = count;
    }

}
