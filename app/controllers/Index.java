package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.UserService;
import views.html.home;
import views.html.index;
import views.html.wip;

/**
 * Controls simple or catch-all routes: Index, Home, works in progress, etc
 */
@org.springframework.stereotype.Controller
public class Index extends Controller {

    @Autowired
    private UserService userService;

    public Result index() {
        return ok(index.render(userService.getConnected(session())));
    }

    @Security.Authenticated(SecuredRoutes.class)
    public Result home() {
        return ok(home.render(userService.getConnected(session())));
    }

    public Result wip() {
        return ok(wip.render(userService.getConnected(session())));
    }

    public static Result goAway() {
        return redirect("http://alturl.com/p749b");
    }
}
