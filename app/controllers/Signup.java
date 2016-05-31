package controllers;

import models.LoginForm;
import models.User;
import play.data.Form;
import play.mvc.Result;
import views.html.login;
import views.html.signup;

import static play.mvc.Controller.flash;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class Signup {

    public static Result get() {
        return ok(signup.render(Form.form(models.User.class)));
    }

    public static Result post() {

        Form<models.User> form = Form.form(models.User.class).bindFromRequest();
        if (form.hasErrors()) {
            flash("error", "Try that again, dipshit.");
            return badRequest(signup.render(form));
        }

        User newUser = form.get();
        newUser.save();

        flash("success", "Welcome aboard, "+newUser.alias+". Login to continue.");
        return ok(login.render(Form.form(LoginForm.class)));
    }
}
