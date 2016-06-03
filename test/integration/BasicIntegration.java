package integration;

import controllers.UserController;
import models.User;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import play.libs.Json;
import play.mvc.Result;
import play.libs.F;
import play.test.FakeRequest;
import play.test.Helpers;
import play.test.TestBrowser;
import services.UserServiceDao;

import static integration.Util.getLogin;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.callAction;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.route;
import static play.test.Helpers.running;
import static play.test.Helpers.status;
import static play.test.Helpers.testServer;

public class BasicIntegration extends play.test.WithApplication {

    @Test
    public void testPublicRoutes() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("A social approach to the underworld");
                browser.goTo("http://localhost:3333/login");
                assertThat(browser.pageSource()).contains("Who dis?");
                browser.goTo("http://localhost:3333/signup");
                assertThat(browser.pageSource()).contains("no welchers!");
            }
        });
    }

    @Test
    public void testNoAuthPrivateRoutes() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                String msg = "Y U NO LOGIN?!";
                browser.goTo("http://localhost:3333/home");
                assertThat(browser.pageSource()).contains(msg);
                browser.goTo("http://localhost:3333/jobs");
                assertThat(browser.pageSource()).contains(msg);
                browser.goTo("http://localhost:3333/lending");
                assertThat(browser.pageSource()).contains(msg);
            }
        });
    }

    @Test
    public void testInvalidRoute() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/somethingfake");
                assertThat(browser.pageSource()).contains("www.youtube.com");
            }
        });
    }
}
