package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import static play.mvc.Controller.flash;

@org.springframework.stereotype.Controller
public class SecuredRoutes extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("userid");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        flash("error", "ლ(ಠ_ಠლ) Y U NO LOGIN?!");
        return redirect(routes.UserController.getLogin());
    }
}
