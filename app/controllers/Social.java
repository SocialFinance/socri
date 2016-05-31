package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.UserService;
import views.html.home;

@org.springframework.stereotype.Controller
public class Social extends Controller {

    @Autowired
    private UserService userService;

    @Security.Authenticated(Secured.class)
    public Result get() {
        //TODO
        return ok(home.render(userService.getConnected(session())));
    }
}
