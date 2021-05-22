package ceng.estu.model;

import java.sql.Date;

/**
 * @author reuzun
 */
public class Shoe {

    int shoeID;
    int modelID;
    int size;
    String color;
    int count;

    String brand;
    String type;
    double boughtPrice;
    Date date;

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


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //Model properties for last bought section


    public void setModelProperties(String brand, String type, double boughtPrice, Date date){
        this.brand = brand;
        this.type = type;
        this.boughtPrice = boughtPrice;
        this.date = date;
    }



    public Shoe(int modelID, int shoeID, int size, String color, int count) {
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
