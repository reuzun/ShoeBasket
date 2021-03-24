package ceng.estu.controller;

import javafx.scene.control.PasswordField;

/**
 * @author reuzun
 */
public interface Controllable {
    default void fillAreas(String username, String password){} //This is for preventing unnecessary effort on controller manipulation.
    default PasswordField getInputPassword() throws Exception {
        throw new Exception("No Override has done.");
    }
}
