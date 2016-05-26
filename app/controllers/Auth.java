package controllers;

import models.LoginForm;
import models.Message.BlankMessage;
import models.Message.ErrorMessage;
import models.User;
import play.data.Form;
import play.mvc.Result;
import views.html.*;

import java.util.List;

import static play.mvc.Controller.session;
import static play.mvc.Results.*;

public class Auth {

    public static Result get() {
        return ok(login.render(new BlankMessage(), play.data.Form.form(models.LoginForm.class)));
    }

    public static Result logout() {
        session().remove("connected");
        return redirect(routes.Index.index());
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
            return badRequest(login.render(new ErrorMessage("<strong>Auth error.</strong> Get that weak shit out of here"), form));
        }

        if(user != null) {
            session("connected", user.id);
            return redirect(routes.Index.index());
        } else {
            return internalServerError(login.render(new ErrorMessage("<strong>OH SHIT!</strong> Something broke down here somewhere. Try that again if you want."), form));
        }
    }
}
