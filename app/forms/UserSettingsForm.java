package forms;

import models.User;

public class UserSettingsForm {

    @play.data.validation.Constraints.Required
    public String alias;

    @play.data.validation.Constraints.Required
    public String specialties;

    public String weaknesses;

    public UserSettingsForm() {

    }

    public UserSettingsForm(User u) {
        this.alias = u.getAlias();
        this.specialties = u.getSpecialties();
        this.weaknesses = u.getWeaknesses();
    }

    public void populate(User u) {
        u.setAlias(alias);
        u.setSpecialties(specialties);
        u.setWeaknesses(weaknesses);
    }
}
