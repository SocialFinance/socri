package forms;

public class UserPasswordForm {

    @play.data.validation.Constraints.Required
    public String oldPassword;

    @play.data.validation.Constraints.Required
    public String newPassword;

}
