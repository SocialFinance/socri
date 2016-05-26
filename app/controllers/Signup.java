package controllers;

import models.LoginForm;
import models.Message.ErrorMessage;
import models.Message.InfoMessage;
import models.User;
import play.data.Form;
import play.mvc.Result;
import views.html.login;
import views.html.signup;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class Signup {

    public static Result get() {
        return ok(signup.render(new models.Message.BlankMessage(), Form.form(models.User.class)));
    }

    public static Result post() {

        Form<models.User> form = Form.form(models.User.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(signup.render(new ErrorMessage("<strong>Yea, no</strong> Try again."), form));
        }

        User newUser = form.get();
        newUser.save();

        return ok(login.render(new InfoMessage("Welcome aboard, " + newUser.alias + ". Auth to continue."),
                               Form.form(LoginForm.class)));
    }
}
