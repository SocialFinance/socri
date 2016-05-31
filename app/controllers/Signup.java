package controllers;

import forms.LoginForm;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.data.Form;
import play.mvc.Result;
import services.UserService;
import views.html.login;
import views.html.signup;

import static play.mvc.Controller.flash;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

@Controller
public class Signup {

    @Autowired
    private UserService userService;

    public Result get() {
        return ok(signup.render(Form.form(models.User.class)));
    }

    public Result post() {

        Form<models.User> form = Form.form(models.User.class).bindFromRequest();
        if (form.hasErrors()) {
            flash("error", "Try that again, dipshit.");
            return badRequest(signup.render(form));
        }

        User newUser = form.get();
        userService.saveUser(newUser);

        flash("success", "Welcome aboard, " + newUser.getAlias() + ". Login to continue.");
        return ok(login.render(Form.form(LoginForm.class)));
    }
}
