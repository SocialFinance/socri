import org.junit.Test;
import play.mvc.Result;
import play.test.WithApplication;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.GET;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;

public class FooTest extends WithApplication {


    @Test
    public void testFooRoute() {
        Result result = route(fakeRequest(GET, "/foo"));
        assertThat(result).isNotNull();
    }
}
