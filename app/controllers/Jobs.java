package controllers;

import play.mvc.*;

import views.html.*;

import models.*;

public class Jobs extends Controller {

    public static Result get() {
        User user = User.getConnectedUser(session());
        if(user != null) {
            return ok(index.render(user));
        } else {
            return ok(test.render(user));
        }
    }

}
