import org.junit.*;

import play.mvc.*;
import play.twirl.api.Content;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    public static String MSG = "HELLO, WORLD";

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
        Content html = views.html.index.render(null);
//        Content html = views.html.index.render(MSG);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains(MSG);
    }

    @Test
    public void testIndex() {
        Result result = controllers.Index.get();
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains(MSG);
    }
}
