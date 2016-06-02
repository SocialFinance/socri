package forms;

/**
 * Form. Used to change password from user settings.
 */
public class UserPasswordForm {

    @play.data.validation.Constraints.Required
    public String oldPassword;

    @play.data.validation.Constraints.Required
    public String newPassword;

}
