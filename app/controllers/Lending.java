package controllers;

import models.Loan;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.test;

import java.util.List;

public class Lending extends Controller {

    public static Result get() {
        User user = User.getConnectedUser(session());

        if(user != null) {
            return ok(index.render(user));
        } else {
            return ok(test.render(user));
        }
    }

    public static Result getLoans() {
        User user = User.getConnectedUser(session());
        return ok(Json.toJson(models.Loan.allForUser(user.id)));
    }
}
