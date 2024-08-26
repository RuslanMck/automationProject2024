package ui.dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider
    public Object[][] userCredentialsDataProvider() {
        return new Object[][]{{"qwe", "SuperSecretPassword", "Your username is invalid!"},
                {"tomsmith", "qweasd", "Your password is invalid!"},
                {"qwe", "qweasd", "Your username is invalid!"}
        };
    }
}
