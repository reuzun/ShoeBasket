package ceng.estu.database;

import ceng.estu.model.*;
import ceng.estu.utilities.AlertSystem;
import ceng.estu.utilities.ErrorType;

import javax.swing.plaf.nimbus.State;
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

    public static List<Model> boughtModels() throws SQLException {
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

    public static Model getModelUsingModelID(int modelID) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `model` where modelId = " + modelID);
            rs.next();
            ModelType modelType;
            String type = rs.getNString(4);
            if (type.equals(ModelType.Sneaker.toString())) {
                modelType = ModelType.Sneaker;
            } else if (type.equals(ModelType.Boot.toString())) {
                modelType = ModelType.Boot;
            } else {
                modelType = ModelType.Heel;
            }
            return new Model(
                    rs.getInt(1),
                    rs.getNString(2),
                    rs.getNString(3),
                    modelType,
                    rs.getDouble(5),
                    rs.getDouble(6)
            );
        }catch (Exception e){
            AlertSystem.getAlert(ErrorType.ERROR, "Something is huge failed about database. Please contact US!");
        }
        return null;
    }

    public static List<Shoe> getLastBoughtShoes() throws SQLException {
        List<Shoe> shoeList = new ArrayList<>();

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM shoe_sold INNER JOIN shoe ON shoe_sold.shoeId = shoe.ShoeID where Username = \"" + User.user.getUsername() + "\" " + "order by rand() limit 50");



        while (rs.next()){
            Shoe shoe = new Shoe(
                    rs.getInt(2),
                    rs.getInt(6),
                    rs.getInt(8),
                    rs.getNString(9),
                    rs.getInt(10)
            );

            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery("SELECT * FROM model where ModelId = " + shoe.getModelID() );
            rs2.next();

            shoe.setModelProperties(
                    rs2.getNString(3),
                    rs2.getNString(4),
                    rs.getDouble(4),
                    rs.getDate(3)
            );

            shoeList.add(shoe);
        }

        return shoeList;
    }

    public static boolean saveStar(int starCount, int modelId){
        try {

            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("select count(Username) from rated_models where username = \"" + User.user.getUsername() + "\" AND modelId = " + modelId);
            r.next();
            int i = r.getInt(1);

            if(i != 0){
                System.out.println("i is : " + i);
                AlertSystem.getAlert(ErrorType.INFORMATION, "You have already starred this model!");
                return false;
            }


            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO rated_models values (\"" + User.user.getUsername() + "\", " + modelId + ", " + starCount + ")");

            Thread.sleep(200);

            Statement s2 = con.createStatement();
            ResultSet r2 = s2.executeQuery("select count(Username) from rated_models where "+ " modelId = " + modelId);
            r2.next();
            int rateCount = r2.getInt(1);

            Statement s3 = con.createStatement();
            ResultSet r3 = s3.executeQuery("select Star from rated_models where "+ " modelId = " + modelId);
            int sum = 0;
            while (r3.next())
                sum += r3.getInt(1);

            double res;
            if(rateCount == 0)
                res = (double)sum;
            else res = (double)sum / (rateCount) ;

            Statement s4 = con.createStatement();
            s4.executeUpdate("UPDATE model SET CustomerRating = " + res + " where modelId = " + modelId);


            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            AlertSystem.getAlert(ErrorType.ERROR, "Something is huge failed about database. Please contact US!");
        }
        return false;
    }
}
