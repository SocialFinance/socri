package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Foo extends Controller {

    public static Result get() {
        return ok(index.render("Your new application is ready."));
    }

}
