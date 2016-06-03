package integration;

import models.User;
import org.openqa.selenium.Cookie;
import play.test.TestBrowser;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;

public class Util {

    public static User createUser() {
        User u = new User();
        u.setId(1);
        u.setUsername("foo");
        u.setPassword("bar");
        u.setSpecialties("golf");
        u.setWeaknesses("old movies");
        u.setAlias("mac the knife");
        return u;
    }

    public static void getLogin(TestBrowser browser) {
        browser.goTo("http://localhost:3333/login");
        browser.fill("#username").with("foo");
        browser.fill("#password").with("bar");
        browser.submit("#loginSubmit");
        Cookie c = browser.getCookie("PLAY_SESSION");
        assertNotNull("Login null cookie check", c);
        assertTrue("Login userid check", c.getValue().contains("userid"));
        assertTrue("Login browser redirect check", browser.pageSource().contains("Welcome, Debug user"));
    }

}
