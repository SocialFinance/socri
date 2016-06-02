package controllers;

import forms.LoginForm;
import forms.UserPasswordForm;
import forms.UserSettingsForm;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Security;
import services.UserService;
import views.html.login;
import views.html.settings;
import views.html.signup;

@Controller
public class UserController extends play.mvc.Controller {

    @Autowired
    private UserService userService;

    /****************************************
     * API routes
     */

    @Security.Authenticated(SecuredApi.class)
    public Result deleteUserAPI(Integer userId) {
        try {
            userService.deleteUser(userId);
            flash("info", "User deleted successfully");
            session().clear();
            return ok("User deleted");
        } catch (NullPointerException | NumberFormatException e) {
            flash("error", "Unable to delete user");
            return badRequest("Error: unable to delete user");
        }
    }

    @Security.Authenticated(SecuredApi.class)
    public Result getUserAPI(Integer userId) {
        try {
            return ok(Json.toJson(userService.getById(userId)));
        } catch (NullPointerException | NumberFormatException e) {
            return badRequest("{}");
        }
    }

    @Security.Authenticated(SecuredApi.class)
    public Result updateUserAPI(Integer userId) {
        return ok();
    }

    /******************************************
     * User content routes
     */

    public Result getLogin() {
        return ok(login.render(play.data.Form.form(LoginForm.class)));
    }

    public Result doLogout() {
        session().clear();
        return redirect(routes.Index.index());
    }

    public Result doLogin() {

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
            session("userid", "" + user.getId());
            return redirect(routes.Index.home());
        } else {
            flash("error", "OH SHIT! Something broke on our side. Try that again if you want.");
            return internalServerError(login.render(form));
        }
    }

    public Result getSignup() {
        return ok(signup.render(Form.form(models.User.class)));
    }

    public Result doSignup() {

        Form<models.User> form = Form.form(models.User.class).bindFromRequest();
        if (form.hasErrors()) {
            flash("error", "Try that again, dipshit.");
            return badRequest(signup.render(form));
        }
        User newUser = form.get();
        if (userService.getByUsername(newUser.getUsername()) != null) {
            flash("error", "Pick a different username.");
            form.reject("username", "That username is taken.");
            return badRequest(signup.render(form));
        }

        userService.saveUser(newUser);
        flash("success", "Welcome aboard, " + newUser.getAlias() + ". Login to continue.");
        return ok(login.render(Form.form(LoginForm.class)));
    }

    @Security.Authenticated(SecuredRoutes.class)
    public Result getSettings() {
        User u = userService.getConnected(session());
        return ok(settings.render(u, Form.form(UserSettingsForm.class).fill(new UserSettingsForm(u)), Form.form(UserPasswordForm.class)));
    }

    @Security.Authenticated(SecuredRoutes.class)
    public Result saveSettings() {
        Form<UserSettingsForm> form = Form.form(UserSettingsForm.class).bindFromRequest();
        UserSettingsForm fu = form.get();
        User u = userService.getConnected(session());
        fu.populate(u);
        userService.saveUser(u);
        flash("success", "Settings updated successfully");
        return getSettings();
    }

    @Security.Authenticated(SecuredRoutes.class)
    public Result changePassword() {
        User u = userService.getConnected(session());

        Form<UserPasswordForm> form = Form.form(UserPasswordForm.class).bindFromRequest();
        UserPasswordForm fu = form.get();
        if(!form.hasErrors()) {
            if(!u.getPassword().equals(fu.oldPassword)) {
                form.reject("oldPassword", "That's not your old password. R-tard.");
            } else if(fu.oldPassword == fu.newPassword) {
                form.reject("newPassword", "Old password = new password. Idiot.");
            }
        }
        if(form.hasErrors()) {
            flash("error", "Changing password failed. You did it wrong.");
            return ok(settings.render(u, Form.form(UserSettingsForm.class).fill(new UserSettingsForm(u)), form));
        }

        u.setPassword(fu.newPassword);
        userService.saveUser(u);

        flash("success", "Password changed successfully");
        return getSettings();
    }
}
