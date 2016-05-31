package forms;

public class LoginForm {

    @play.data.validation.Constraints.Required
    public String username;

    @play.data.validation.Constraints.Required
    public String password;
}
