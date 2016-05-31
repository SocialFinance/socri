package controllers;

import models.LoginForm;
import models.User;
import play.data.Form;
import play.mvc.Result;
import views.html.*;

import java.util.List;

import static play.mvc.Controller.flash;
import static play.mvc.Controller.session;
import static play.mvc.Results.*;

public class Auth {

    public static Result get() {
        return ok(login.render(play.data.Form.form(models.LoginForm.class)));
    }

    public static Result logout() {
        session().clear();
        return redirect(routes.Index.get());
    }

    public static Result login() {

        User user = null;
        Form<models.LoginForm> form = Form.form(LoginForm.class).bindFromRequest();
        if (!form.hasErrors()) {
            List<User> hits = models.User.findByUsername(form.get().username);
            if(hits.isEmpty()) {
                form.reject("username", "Nooooope");
            } else {
                user = hits.get(0);
                if(!user.password.equals(form.get().password)) {
                    form.reject("password", "Nope on a rope");
                }
            }
        }
        if(form.hasErrors()) {
            flash("error", "Login error. Get that weak shit outta here.");
            return badRequest(login.render(form));
        }

        if(user != null) {
            session("connected", user.id);
            return redirect(routes.Index.get());
        } else {
            flash("error", "OH SHIT! Something broke on our side. Try that again if you want.");
            return internalServerError(login.render(form));
        }
    }
}
