package ceng.estu.database;

import ceng.estu.model.*;
import ceng.estu.utilities.AlertSystem;
import ceng.estu.utilities.ErrorType;
import ceng.estu.utilities.MyDate;
import ceng.estu.utilities.SortType;
import com.mysql.cj.protocol.Resultset;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author reuzun
 */
public class DBHandler {
    private static Connection con;
    //static MyDate date;

    static {
        DBConnection.initDB();
        con = DBConnection.getCon();
        MyDate.initDate();
    }

    public static boolean logIn(String id, String pass) throws Exception {
        try {
            Statement st = con.createStatement();

            String query = "SELECT * FROM `users` WHERE Username=\"" + id + "\" and Pass_word = \"" + pass + "\"";

            ResultSet answer = st.executeQuery(query);

            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));

            answer.next();

            String username = answer.getNString(1);
            String password = answer.getNString(2);
            UserType type = getType(answer.getNString(6));
            String email = answer.getNString(3);
            String name = answer.getNString(4);
            String surname = answer.getNString(5);

            List<String> adressList = new ArrayList<>();
            Statement stadress = con.createStatement();


            query = "SELECT * FROM `user_adresses` WHERE Username=\"" + id + "\"";
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));

            ResultSet answeradress = stadress.executeQuery(query);

            while (answeradress.next())
                adressList.add(answeradress.getNString(2));

            List<String> phoneList = new ArrayList<>();
            Statement stphone = con.createStatement();
            query = "SELECT * FROM `user_phoneno` WHERE Username=\"" + id + "\"";

            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));

            ResultSet answerphone = stphone.executeQuery(query);

            while (answerphone.next())
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
            String query = "Insert into users values(" +
                    "\"" + username +  "\"," +
                    "\"" + password +  "\"," +
                    "\"" + email +  "\"," +
                    "\"" + name +  "\"," +
                    "\"" + surname +  "\"," +
                    " \"User\")";
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
            st.executeUpdate(query);

            if (address!=null && address.length() != 0 && address.replace(" ","").length() != 0 ) {
                Statement stAddress = con.createStatement();
                query = "Insert into user_adresses values(" +
                        "\"" + username + "\"," +
                        "\"" + address + "\")";

                System.out.println(MyDate.refresh() + " : " + query.replace("\n", ""));

                stAddress.executeUpdate(query
                );
            }
            if (phoneNo!=null && phoneNo.length() != 0 ) {
                Statement stPhone = con.createStatement();
                query = "Insert into user_phoneno values(" +
                        "\"" + username + "\"," +
                        "\"" + phoneNo + "\")";
                System.out.println(MyDate.refresh() + " : " + query.replace("\n", ""));
                stPhone.executeUpdate(query
                );
            }

            return true;
        }catch (Exception e){
            //e.printStackTrace();
        }
        throw new Exception("Username Already Exists!");
    }

    public static List<Model> randomModels() throws SQLException {
        List<Model> modelList = new ArrayList<>();
        Statement st = con.createStatement();
        String query = "SELECT * FROM `model` order by rand() limit 50";
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        ResultSet rs = st.executeQuery(query);

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

    @Deprecated(since = "We dont know thy we write it :D")
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

    public static Model getModelByModelId(int modelID) {
        try {
            Statement st = con.createStatement();
            String query = "SELECT * FROM `model` where modelId = " + modelID;
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
            ResultSet rs = st.executeQuery(query);
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
        String query = "SELECT * FROM shoe_sold INNER JOIN shoe ON shoe_sold.shoeId = shoe.ShoeID where Username = \"" + User.user.getUsername() + "\" " + "ORDER BY shoe_sold.SaleDate DESC limit 50";
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        ResultSet rs = st.executeQuery(query);



        while (rs.next()){
            Shoe shoe = new Shoe(
                    rs.getInt(6),
                    rs.getInt(2),
                    rs.getInt(8),
                    rs.getNString(9),
                    rs.getInt(10)
            );

            Statement st2 = con.createStatement();
            query = "SELECT * FROM model where ModelId = " + shoe.getModelID();
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
            ResultSet rs2 = st2.executeQuery( query );
            rs2.next();

            shoe.setModelPropertiesForSale(
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
            String query = "select count(Username) from rated_models where username = \"" + User.user.getUsername() + "\" AND modelId = " + modelId;
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
            ResultSet r = s.executeQuery(query);
            r.next();
            int i = r.getInt(1);

            if(i != 0){
                //System.out.println("i is : " + i);
                AlertSystem.getAlert(ErrorType.INFORMATION, "You have already starred this model!");
                return false;
            }


            Statement st = con.createStatement();
            query = "INSERT INTO rated_models values (\"" + User.user.getUsername() + "\", " + modelId + ", " + starCount + ")";
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
            st.executeUpdate(query);

            Thread.sleep(200);

            Statement s2 = con.createStatement();
            query = "select count(Username) from rated_models where "+ " modelId = " + modelId;
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
            ResultSet r2 = s2.executeQuery(query);
            r2.next();
            int rateCount = r2.getInt(1);

            Statement s3 = con.createStatement();
            query = "select Star from rated_models where "+ " modelId = " + modelId;
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));

            ResultSet r3 = s3.executeQuery(query);
            int sum = 0;
            while (r3.next())
                sum += r3.getInt(1);

            double res;
            if(rateCount == 0)
                res = (double)sum;
            else res = (double)sum / (rateCount) ;

            Statement s4 = con.createStatement();
            query = "UPDATE model SET CustomerRating = " + res + " where modelId = " + modelId;
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
            s4.executeUpdate(query);


            AlertSystem.getAlert(ErrorType.INFORMATION, "Done!");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            AlertSystem.getAlert(ErrorType.ERROR, "Something is huge failed about database. Please contact US!");
        }
        return false;
    }

    public static Shoe getShoeByShoeId(String shoeId) throws SQLException {
        Statement st = con.createStatement();
        String query = "SELECT * FROM shoe WHERE shoeId = " + shoeId;
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        ResultSet rs = st.executeQuery(query);
        rs.next();

        return new Shoe(
                rs.getInt(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getNString(4),
                rs.getInt(5)
                );

    }

    public static int getModelIdByShoeId(int shoeId) throws SQLException {
        Statement st = con.createStatement();
        String query ="SELECT * FROM shoe WHERE shoeId = " + shoeId;

        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));

        ResultSet rs = st.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }

    // TYPE - KEY - VALUE accepts as input.
    // Types is str and int
    public static List<Shoe> getShoesByKeyValues(String... strings) throws SQLException {
        Statement st = con.createStatement();
        StringBuilder sb = new StringBuilder("SELECT * FROM shoe WHERE ");

        String type = null;
        for(int i = 0 ; i < strings.length ; i+=3){
            if (strings[i + 2] == null || strings[i + 2].length() == 0 || strings[i + 2].equals("null"))
                continue;
            type = strings[i];
            if(type.equals("str")){
                sb.append(" AND ").append(strings[i + 1]).append(" = \"");
                sb.append(strings[i+2] + "\" ");
            }else{
                sb.append(" AND ").append(strings[i + 1]).append(" = ");
                sb.append(strings[i+2]);
            }
        }

        String str = sb.toString();
        str = str.replaceFirst("AND", "");

        System.out.println( MyDate.refresh() + " : " + str.replace("\n",""));

        ResultSet rs = st.executeQuery(str);
        List<Shoe> listShoe = new ArrayList<>();
        while (rs.next()) {

            listShoe.add(new Shoe(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getNString(4),
                    rs.getInt(5)
            ));
        }

        return listShoe;

    }

    // TYPE - KEY - VALUE accepts as input.
    // Types is str and int
    public static List<Model> getModelsByKeyValues(String... strings) throws SQLException {
        Statement st = con.createStatement();
        StringBuilder sb = new StringBuilder("SELECT * FROM model WHERE ");

        String type = null;
        for(int i = 0 ; i < strings.length ; i+=3){
            if (strings[i + 2] == null || strings[i + 2].length() == 0 || strings[i + 2].equals("null"))
                continue;
            type = strings[i];
            if(type.equals("str")){
                sb.append(" AND ").append(strings[i + 1]).append(" = \"");
                sb.append(strings[i+2] + "\" ");
            }else if(type.equals("strlike")){
                sb.append(" AND ").append(strings[i + 1]).append(" LIKE \"%");
                sb.append(strings[i+2] + "%\" ");
            }
            else{
                sb.append(" AND ").append(strings[i + 1]).append(" = ");
                sb.append(strings[i+2]);
            }
        }

        String str = sb.toString();
        str = str.replaceFirst("AND", "");

        System.out.println( MyDate.refresh() + " : " + str.replace("\n",""));

        //System.out.println("Query is : " + str);

        ResultSet rs = st.executeQuery(str);
        List<Model> listShoe = new ArrayList<>();
        while (rs.next()) {
            ModelType modelType;
            String t = rs.getNString(4);
            if (t.equals(ModelType.Sneaker.toString())) {
                modelType = ModelType.Sneaker;
            } else if (t.equals(ModelType.Boot.toString())) {
                modelType = ModelType.Boot;
            } else {
                modelType = ModelType.Heel;
            }

            listShoe.add(new Model(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    modelType,
                    rs.getDouble(5),
                    rs.getDouble(6)
            ));
        }

        return listShoe;

    }

    //---- Does not log
    public static boolean insertModel(String modelName, String brandName, String type, double price) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO MODEL VALUES (DEFAULT ,?,?,?,?,NULL)");
        ps.setString(1, modelName);
        ps.setString(2, brandName);
        ps.setString(3, type);
        ps.setDouble(4, price);
        return ps.execute();
    }

    //---- Does not log
    public static boolean insertShoe(int modelId, int size, String color, int count) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO SHOE VALUES (? ,DEFAULT ,?,?,?)");
        ps.setInt(1, modelId);
        ps.setInt(2, size);
        ps.setString(3, color);
        ps.setInt(4, count);
        return ps.execute();
    }


    public static boolean deleteShoeByShoeId(String shoeId) throws SQLException {
        Statement st = con.createStatement();
        String query = "DELETE FROM shoe WHERE shoeId = " + shoeId;
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        return st.execute(query);
    }

    public static boolean deleteModelByModelId(int modelId) throws SQLException {
        Statement st = con.createStatement();
        String query = "DELETE FROM model WHERE modelId = " + modelId;
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        return st.execute( query );
    }

    public static boolean updateModelByModelId(String price, String modelId) throws SQLException {
        Statement st = con.createStatement();
        String query = "UPDATE model SET price = " + price + " WHERE modelid = " + modelId;
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        return st.execute(query);
    }

    public static boolean updateShoeByShoeId(String count, String shoeId) throws SQLException {
        Statement st = con.createStatement();
        String query = "UPDATE shoe SET count = " + count + " WHERE shoeid = " + shoeId;
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        return st.execute(query);
    }

    public static List<Model> searchModelsAccordingToParams(String name, double lowerBound, double upperBound, SortType sortType) throws SQLException {
        boolean flag = false;
        List<Model> modelList = new ArrayList<>();
        StringBuilder sb = new StringBuilder("SELECT * FROM model WHERE ");
        if(name.length() > 0){
            sb.append(" AND (brandname LIKE \"%" + name + "%\" OR modelname LIKE \"%" + name + "%\") ");
            flag = true;
        }
        /*if( size != 0 ){
            sb.append(" AND size = " + size + " ");
            flag = true;
        }*/
        if( lowerBound != 0 ){
            sb.append(" AND price > " + lowerBound + " ");
            flag = true;
        }
        if( upperBound != 0 ){
            sb.append(" AND price < " + upperBound + " ");
            flag = true;
        }
        if( sortType != null ){
            if (flag == false)
            {
                String s = sb.toString().replaceFirst("WHERE" , "");
                sb = new StringBuilder(s);
            }
            switch (sortType){
                case starAscending:
                    sb.append( "ORDER BY customerrating ASC" );
                    break;
                case priceAscending:
                    sb.append( "ORDER BY price ASC" );
                    break;
                case priceDescending:
                    sb.append( "ORDER BY price DESC" );
                    break;
                case starDescending:
                    sb.append( "ORDER BY customerrating DESC" );
                    break;
            }
        }

        sb.append(" LIMIT 50");

        String query = sb.toString().replaceFirst("AND" , "");

        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()){

            ModelType modelType;
            String t = rs.getNString(4);
            if (t.equals(ModelType.Sneaker.toString())) {
                modelType = ModelType.Sneaker;
            } else if (t.equals(ModelType.Boot.toString())) {
                modelType = ModelType.Boot;
            } else {
                modelType = ModelType.Heel;
            }

            modelList.add(new Model(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    modelType,
                    rs.getDouble(5),
                    rs.getDouble(6)
            ));
        }

        return modelList;
    }

    public static List<Integer> getSizeByModelId(int modelId){
        try {
            List<Integer> sizeList = new ArrayList<>();

            Statement st = con.createStatement();
            String query = "SELECT size FROM model INNER JOIN shoe ON model.ModelID = shoe.ModelID WHERE model.ModelID = " + modelId;
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                sizeList.add(rs.getInt(1));
            }

            return sizeList;
        }catch (Exception e){
            System.out.println("Error ocurred!");
        }
            return new ArrayList<>();
    }

    public static List<String> getColorByModelId(int modelId, Integer selectedItem) {
        try {
            List<String> sizeList = new ArrayList<>();

            Statement st = con.createStatement();
            String query = "SELECT color FROM model INNER JOIN shoe ON model.ModelID = shoe.ModelID WHERE model.ModelID = " + modelId + " AND shoe.size = " + selectedItem;
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                sizeList.add(rs.getNString(1));
            }

            return sizeList;
        }catch (Exception e){
            System.out.println("Error ocurred!");
        }
        return new ArrayList<>();
    }

    public static int getShoeIdByModelIdColorSize(int modelId, int size, String color) {
        try {
            Statement st = con.createStatement();
            String query = "SELECT shoeid FROM shoe WHERE modelid = " + modelId + " AND size = " + size + " AND color = \"" + color + "\"";
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
            ResultSet rs = st.executeQuery( query );
            rs.next();
            return rs.getInt(1);
        }catch (Exception e){}
        return -1;
    }

    //Does not log
    public static boolean addToUserBasketByShoeId(int shoeID) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO basket_contains VALUES (?, ?) ");
            ps.setString(1, User.user.getUsername());
            ps.setInt(2, shoeID);
            return ps.execute();
        }catch (Exception e){
            System.out.println("Error ocurred!");
        }
        return false;
    }


    //-------------------------------------------------------------------------------------//
    public static boolean addAdress(String text, int adressIndex) throws SQLException {
        Statement st = con.createStatement();
        String query = "INSERT INTO user_adresses values (\"" + User.user.getUsername() + "\", \"" + text + "\")";
        st.execute(query);
        User.user.getAddresses().add(text);
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        return true;
    }

    public static boolean addPhoneNo(String text, int phoneIndex) throws SQLException {
        Statement st = con.createStatement();

        String query = "INSERT INTO user_phoneno values (\"" + User.user.getUsername() + "\", \"" + text + "\")";
        st.execute(query);
        User.user.getPhoneNos().add(text);
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        return true;
    }

    public static boolean updatePhoneNo(String text, int phoneIndex) throws SQLException {
        Statement st = con.createStatement();
        String query = "UPDATE user_phoneno SET phoneno = " + text + " WHERE username = \"" + User.user.getUsername() + "\"" +
                " AND phoneno = \"" + User.user.getPhoneNos().get(phoneIndex) + "\"" ;
        st.execute(query);
        User.user.getPhoneNos().set(phoneIndex, text);
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        return true;
    }

    public static boolean updateAdress(String text, int adressIndex) throws SQLException {
        Statement st = con.createStatement();
        String query = "UPDATE user_adresses SET adress = \"" + text + "\" WHERE username = \"" + User.user.getUsername() + "\"" +
                " AND adress = \"" + User.user.getAddresses().get(adressIndex) + "\"";
        User.user.getAddresses().set(adressIndex, text);
        st.execute(query);
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        return true;
    }

    public static boolean deletePhone(String text, int phoneIndex) throws SQLException {
        Statement st = con.createStatement();
        String query = "DELETE FROM user_phoneno WHERE phoneno = " + text;
        st.execute(query);
        User.user.getPhoneNos().remove(phoneIndex);
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        return true;
    }

    public static boolean deleteAdress(String text, int adressIndex) throws SQLException {
        Statement st = con.createStatement();
        String query = "DELETE FROM user_adresses WHERE adress = \"" + text + "\"";
        st.execute(query);
        User.user.getAddresses().remove(adressIndex);
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        return true;
    }

    public static List<Shoe> getUserBasket() throws SQLException {
        Statement st = con.createStatement();
        String query = "SELECT shoe.ShoeID, type, BrandName, ModelName, color, size, price" +
                " FROM (basket_contains INNER JOIN shoe ON basket_contains.ShoeID = shoe.ShoeID) " +
                "INNER JOIN model ON shoe.ModelID = model.ModelID WHERE username = \"" + User.user.getUsername() + "\"";

        ResultSet rs = st.executeQuery(query);

        List<Shoe> shoeBasket = new ArrayList<>();

        while(rs.next()){
            int shoeId = rs.getInt(1);
            String type = rs.getNString(2);
            String brandName = rs.getNString(3);
            String modelName = rs.getNString(4);
            String color = rs.getNString(5);
            int size = rs.getInt(6);
            double price = rs.getInt(7);

            Shoe shoe = new Shoe(-1, shoeId, size, color, -1);
            shoe.setModelPropertiesForBasket(modelName, brandName, type, price);

            shoeBasket.add(shoe);
        }

        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        return shoeBasket;
    }

    public static boolean removeFromUserBasket(int shoeId) throws SQLException{
        Statement st = con.createStatement();
        String query = "DELETE FROM basket_contains WHERE shoeId = " + shoeId + " AND username = \"" + User.user.getUsername() + "\" LIMIT 1";
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        return st.execute(query);
    }

    public static boolean buyUserBasket(List<Shoe> list, String address) throws SQLException {

        for(Shoe s : list){

            Statement st = con.createStatement();
            String query = "INSERT INTO shoe_sold VALUES (\"" + User.user.getUsername() + "\", " + s.getShoeID() + ", NOW(), " + s.getBoughtPrice() + ", \"" + address + "\")";
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
            st.execute(query);

            Statement st2 = con.createStatement();
            query = "UPDATE shoe SET count = count - 1 WHERE shoeid = " + s.getShoeID();
            System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
            st2.execute(query);

            removeFromUserBasket(s.getShoeID());
        }

        return true;
    }

    public static String[] findBest (String table, String column) throws SQLException {
        String query = "SELECT       "+ column +" ,\n" +
                "             COUNT(" + column + ") AS `value_occurrence` \n" +
                "    FROM     "+ table +"\n" +
                "    GROUP BY " + column + "\n" +
                "    ORDER BY `value_occurrence` DESC\n" +
                "    LIMIT    1;";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));

        String a;

        try{
            a = rs.getNString(1);
        }catch (Exception e){
            a = String.valueOf( rs.getInt(1) );
        }

        return new String[]{a, String.valueOf(rs.getInt(2))};

    }

    public static int userCount() throws SQLException {
        String query = "SELECT COUNT(\"username\") from users";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));

        return rs.getInt(1);
    }

    public static String getMostSuccessfulBrand() throws SQLException {
        String query = "SELECT BrandName," +
                " COUNT(ModelId) AS Model_COUNT," +
                " SUM(customerRating) AS totalRating," +
                " SUM(customerRating)/COUNT(ModelId) AS indicator" +
                " FROM model GROUP BY brandName ORDER BY indicator DESC";

        Statement st = con.createStatement();
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        ResultSet rs = st.executeQuery(query);

        rs.next();

        return rs.getNString(1) + "( " + rs.getDouble(4) + " )";

    }

    public static String[] findBestSellerModelId() throws SQLException {
        String query = "SELECT model.ModelID," +
                " COUNT(model.ModelId) AS sell_Count" +
                " FROM (shoe_sold INNER JOIN shoe ON shoe_sold.ShoeID = shoe.ShoeID)" +
                " INNER JOIN model ON shoe.ModelID = model.ModelID GROUP BY model.ModelID" +
                " ORDER BY sell_Count DESC";

        Statement st = con.createStatement();
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        ResultSet rs = st.executeQuery(query);

        rs.next();

        return new String[]{String.valueOf(rs.getInt(1)), String.valueOf(rs.getInt(2))};

    }

    public static String[] bestMoneySpender() throws SQLException {
        String query = "SELECT username," +
                " SUM(shoe_sold.PRICE) AS sumprice" +
                " FROM (shoe_sold INNER JOIN shoe ON shoe_sold.ShoeID = shoe.ShoeID)" +
                " INNER JOIN model ON shoe.ModelID = model.ModelID" +
                " GROUP BY shoe_sold.Username" +
                " ORDER BY sumprice DESC";

        Statement st = con.createStatement();
        System.out.println( MyDate.refresh() + " : " + query.replace("\n",""));
        ResultSet rs = st.executeQuery(query);

        rs.next();

        return new String[]{String.valueOf(rs.getNString(1)), String.valueOf(rs.getInt(2))};

    }

}
