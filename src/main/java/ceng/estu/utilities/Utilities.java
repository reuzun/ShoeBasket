package ceng.estu.utilities;

import ceng.estu.model.ModelType;

import java.io.File;

/**
 * @author reuzun
 */
public class Utilities {
    public static String getImagePath(ModelType type){
        String str = type.toString().toLowerCase();
        String res = new File("").getAbsolutePath().contains("target") ?
                new File("").getAbsolutePath()+"\\classes\\images\\"+ str +".jpg"
                : new File("").getAbsolutePath()+"\\target\\classes\\images\\"+ str +".jpg";
        return res.replace("\\", File.separator);
    }
}
