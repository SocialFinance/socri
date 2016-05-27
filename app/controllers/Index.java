package controllers;

import play.mvc.*;

import views.html.*;
import models.Message.*;

import models.*;

public class Index extends Controller {
    public static Result index() {

        User user = User.getConnectedUser(session());
        if(user != null) {
            return ok(index.render(user));
        } else {
            return ok(test.render());
        }
    }

    public static Result test() {
        return ok(test.render());
    }
}
