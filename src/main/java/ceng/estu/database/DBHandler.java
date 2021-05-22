package ceng.estu.database;

import ceng.estu.model.Model;
import ceng.estu.model.ModelType;
import ceng.estu.model.User;
import ceng.estu.model.UserType;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author reuzun
 */
public class DBHandler {
    private static Connection con;

    static {
        DBConnection.initDB();
        con = DBConnection.getCon();
    }

    public static boolean logIn(String id, String pass) throws Exception {
        try {
            Statement st = con.createStatement();
            ResultSet answer = st.executeQuery("SELECT * FROM `users` WHERE Username=\"" + id + "\" and Pass_word = \"" + pass + "\"");

            answer.next();

            String username = answer.getNString(1);
            String password = answer.getNString(2);
            UserType type = getType(answer.getNString(6));
            String email = answer.getNString(3);
            String name = answer.getNString(4);
            String surname = answer.getNString(5);

            List<String> adressList = new ArrayList<>();
            Statement stadress = con.createStatement();
            ResultSet answeradress = stadress.executeQuery("SELECT * FROM `user_adresses` WHERE Username=\"" + id + "\"");
            while (answeradress.next())
                adressList.add(answeradress.getNString(2));

            List<String> phoneList = new ArrayList<>();
            Statement stphone = con.createStatement();
            ResultSet answerphone = stphone.executeQuery("SELECT * FROM `user_phoneno` WHERE Username=\"" + id + "\"");
            while (answeradress.next())
                phoneList.add(answerphone.getNString(2));

            User.setUser(username, password, type, email, name, surname, adressList, phoneList);

            if(type == UserType.User)
                return true;
            else
                return false;

        }catch (Exception e){
            //e.printStackTrace();
            System.out.println("LOG " + Date.from(Instant.now()) + " : No such that user!");
        }
        throw new Exception("No such that user.");
    }

    public static boolean signUp(String username, String password, String email, String name, String surname, String address, String phoneNo) throws Exception {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("Insert into users values(" +
                    "\"" + username +  "\"," +
                    "\"" + password +  "\"," +
                    "\"" + email +  "\"," +
                    "\"" + name +  "\"," +
                    "\"" + surname +  "\"," +
                    " \"User\")");

            Statement stAddress = con.createStatement();
            stAddress.executeUpdate("Insert into user_adresses values(" +
                    "\"" + username +  "\"," +
                    "\"" + address +  "\")"
                    );

            Statement stPhone = con.createStatement();
            stPhone.executeUpdate("Insert into user_phoneno values(" +
                    "\"" + username +  "\"," +
                    "\"" + phoneNo +  "\")"
            );

            return true;
        }catch (Exception e){
            //e.printStackTrace();
        }
        throw new Exception("Username Already Exists!");
    }

    public static List<Model> randomModels() throws SQLException {
        List<Model> modelList = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM `model` order by rand() limit 50");

        while(rs.next()){
            ModelType modelType;
            String type = rs.getNString(4);
            if(type.equals(ModelType.Sneaker.toString())){
                modelType = ModelType.Sneaker;
            }else if(type.equals(ModelType.Boot.toString())){
                modelType = ModelType.Boot;
            }else{
                modelType = ModelType.Heel;
            }
            modelList.add(
                    new Model(
                            rs.getInt(1),
                            rs.getNString(2),
                            rs.getNString(3),
                            modelType,
                            rs.getDouble(5),
                            rs.getDouble(6)
                    )
            );
        }


        return modelList;
    }

    private static UserType getType(String type){
        return type.equals("User") ? UserType.User : UserType.Admin;
    }

}
