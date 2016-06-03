package integration;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import org.junit.Test;
import play.api.test.Helpers;
import play.libs.F;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import play.test.FakeRequest;
import play.test.TestBrowser;

import static integration.Util.createUser;
import static integration.Util.getLogin;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.route;
import static play.test.Helpers.running;
import static play.test.Helpers.status;
import static play.test.Helpers.testServer;

public class UserIntegration extends play.test.WithApplication {

    @Test
    public void testLoginLogout() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                getLogin(browser);
                browser.goTo("http://localhost:3333/logout");
                assertNull("Logout null cookie check", browser.getCookie("PLAY_SESSION"));
                assertTrue("Logout browser redirect check", browser.pageSource().contains("A social approach to the underworld"));
            }
        });
    }

    @Test
    public void testAuthPrivateRoute() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                String msg = "Someday this page may get rebuilt, but I wouldn't hold your breath.";
                getLogin(browser);
                browser.goTo("http://localhost:3333/jobs");
                assertTrue("Jobs check", browser.pageSource().contains(msg));
                browser.goTo("http://localhost:3333/lending");
                assertTrue("Lending check", browser.pageSource().contains(msg));
                browser.goTo("http://localhost:3333/jobs");
                assertTrue("Social check", browser.pageSource().contains(msg));
            }
        });
    }

    @Test
    public void testAuthAPIRoute() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                getLogin(browser);
                FakeRequest fakeRequest = new FakeRequest("GET", "/api/user/1").withSession("userid", "1");
                Result r = route(fakeRequest);
                assertTrue("User API GET response", status(r) == OK);
                User u = Json.fromJson(Json.parse(contentAsString(r)), User.class);
                assertTrue("User API GET content", u.getWeaknesses().contains("Fish tacos"));

                u.setAlias("mac the knife");
                fakeRequest = new FakeRequest("POST", "/api/user/1").withSession("userid", "1").withJsonBody(Json.toJson(u));
                r = route(fakeRequest);
                assertTrue("User API POST response", status(r) == OK);

                fakeRequest = new FakeRequest("GET", "/api/user/1").withSession("userid", "1");
                r = route(fakeRequest);
                assertTrue("User API GET2 response", status(r) == OK);
                assertTrue("User API GET2 content", contentAsString(r).contains("mac the knife"));

                fakeRequest = new FakeRequest("DELETE", "/api/user/1").withSession("userid", "1");
                r = route(fakeRequest);
                assertTrue("User API DELETE response", status(r) == OK);

                fakeRequest = new FakeRequest("GET", "/api/user/1").withSession("userid", "1");
                r = route(fakeRequest);
                assertTrue("User API GET3 response", status(r) == NOT_FOUND);
            }
        });
    }
}
