package forms;

/**
 * Form. Used by login page
 */
public class LoginForm {

    @play.data.validation.Constraints.Required
    public String username;

    @play.data.validation.Constraints.Required
    public String password;
}
