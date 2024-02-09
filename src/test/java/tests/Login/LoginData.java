package tests.Login;

import org.testng.annotations.DataProvider;
import utils.runner.ProjectProperties;

public class LoginData {
    @DataProvider
    public static Object[][] loginParameters() {
        return new Object[][]{
                {false, ProjectProperties.USERNAME, "invalid", "Wrong password."},
                {false, "", "", "Please fill out Username and Password."},
                {false, ProjectProperties.USERNAME, "", "Please fill out Username and Password."},
                {false, "", ProjectProperties.PASSWORD, "Please fill out Username and Password."},
                {true, ProjectProperties.USERNAME, ProjectProperties.PASSWORD, ""}
        };
    }
}
