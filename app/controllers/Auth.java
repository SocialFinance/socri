package controllers;

import forms.LoginForm;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.data.Form;
import play.mvc.Result;
import services.UserService;
import views.html.login;

import static play.mvc.Controller.flash;
import static play.mvc.Controller.session;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

@Controller
public class Auth {

    @Autowired
    private UserService userService;

    public Result get() {
        return ok(login.render(play.data.Form.form(LoginForm.class)));
    }

    public Result logout() {
        session().clear();
        return redirect(routes.Index.get());
    }

    public Result login() {

        User user = null;
        Form<LoginForm> form = Form.form(LoginForm.class).bindFromRequest();
        if (!form.hasErrors()) {
            user = userService.getByUsername(form.get().username);
            if (user == null) {
                form.reject("username", "Nooooope");
            } else {
                if (!userService.checkPassword(user.getId(), form.get().password)) {
                    form.reject("password", "Nope on a rope");
                }
            }
        }
        if (form.hasErrors()) {
            flash("error", "Login error. Get that weak shit outta here.");
            return badRequest(login.render(form));
        }

        if (user != null) {
            session("connected", "" + user.getId());
            return redirect(routes.Index.get());
        } else {
            flash("error", "OH SHIT! Something broke on our side. Try that again if you want.");
            return internalServerError(login.render(form));
        }
    }
}
