package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

@org.springframework.stereotype.Controller
public class SecuredApi extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("userid");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return unauthorized("ლ(ಠ_ಠლ) Y U NO LOGIN?!");
    }
}
