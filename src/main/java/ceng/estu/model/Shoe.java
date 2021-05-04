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

    public int getShoeID() {
        return shoeID;
    }

    public void setShoeID(int shoeID) {
        this.shoeID = shoeID;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



    public Shoe(int shoeID, int modelID, int size, String color, int count) {
        this.shoeID = shoeID;
        this.modelID = modelID;
        this.size = size;
        this.color = color;
        this.count = count;
    }

    @Override
    public String toString() {
        return
                "\nshoeID=" + shoeID +
                "\nmodelID=" + modelID +
                "\nsize=" + size +
                "\ncolor='" + color +
                "\ncount=" + count;
    }

}
