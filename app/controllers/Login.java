package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

public class Login {

    public static Result get() {
        return ok();
    }

    public static Result post() {
        return redirect(controllers.Index.index());
    }
}
