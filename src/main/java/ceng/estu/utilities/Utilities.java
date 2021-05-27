package ceng.estu.utilities;

import ceng.estu.model.ModelType;

import java.io.File;
import java.io.InputStream;

/**
 * @author reuzun
 */
public class Utilities {
    public static InputStream getImagePath(ModelType type){
        String str = type.toString().toLowerCase();
        String res = new File("").getPath().contains("target") ?
                new File("").getPath()+"\\classes\\images\\"+ str +".jpg"
                : new File("").getPath()+"\\target\\classes\\images\\"+ str +".jpg";
        //return res.replace("\\", File.separator);
        return  Utilities.class.getResourceAsStream("/images/" + str + ".jpg");
    }
}
