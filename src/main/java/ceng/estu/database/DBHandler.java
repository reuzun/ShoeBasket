package ceng.estu.database;

import ceng.estu.model.User;
import ceng.estu.model.UserType;

import java.sql.*;
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
            e.printStackTrace();
        }
        throw new Exception("No such that user.");
    }

    public static boolean signUp(){}

    private static UserType getType(String type){
        return type.equals("User") ? UserType.User : UserType.Admin;
    }

}
