package forms;

public class UserForm {

    @play.data.validation.Constraints.Required
    public String username;

    @play.data.validation.Constraints.Required
    public String password;

    @play.data.validation.Constraints.Required
    public String alias;

    @play.data.validation.Constraints.Required
    public String specialties;

    public String weaknesses;
}
