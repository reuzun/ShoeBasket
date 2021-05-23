package ceng.estu.utilities;

import java.util.Date;

/**
 * @author reuzun
 */
public class MyDate extends Date {

    private static MyDate date;

    private MyDate(){
        super();
    }

    public static void initDate(){
        date = new MyDate();
    }

    public static String refresh(){
        date = new MyDate();
        return date.toString();
    }

}
