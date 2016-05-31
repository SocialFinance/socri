package controllers;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;
import views.html.index;
import views.html.test;

@org.springframework.stereotype.Controller
public class Lending extends Controller {

    @Autowired
    private UserService userService;

    public Result get() {
        User user = userService.getConnected(session());

        if (user != null) {
            return ok(index.render(user));
        } else {
            return ok(test.render(user));
        }
    }

    public Result getLoans() {
        User user = userService.getConnected(session());
//        return ok(Json.toJson(models.Loan.allForUser(user.id)));
        return ok(test.render(null));
    }
}
