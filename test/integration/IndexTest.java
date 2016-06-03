package integration;

import org.junit.Test;
import play.mvc.Result;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;
import static play.test.Helpers.status;

public class IndexTest extends play.test.WithApplication {

    @Test
    public void testIndex() {
        Result result = route(fakeRequest(GET, "/"));
        assertThat(result).isNotNull();
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentAsString(result)).contains("A social approach to the underworld");
    }
}
