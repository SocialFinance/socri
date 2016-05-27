package controllers;

import play.mvc.*;

import views.html.*;
import models.Message.*;

import models.*;

public class Index extends Controller {
    public static Result index() {

        User user = User.getConnectedUser(session());
        return ok(index.render(user));
    }
}
