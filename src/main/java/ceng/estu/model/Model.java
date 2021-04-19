package ceng.estu.model;

/**
 * @author reuzun
 */
public class Model {

    public static int id = 0;
    public int shoeId = 0;

    public Model(){
        id++;
        shoeId = id;
    }

    @Override
    public String toString() {
        return "Gucci's Boot\nBlack\nCount:48 Left\nStar:3.5*";
    }
}
