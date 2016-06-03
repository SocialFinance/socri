package forms;

import models.User;

/**
 * Form. Used to change general user settings.
 */
public class UserSettingsForm {

    @play.data.validation.Constraints.Required
    public String alias;

    @play.data.validation.Constraints.Required
    public String specialties;

    public String weaknesses;

    /**
     * Default constructor, needed for alt constructor
     */
    public UserSettingsForm() {
    }

    /**
     * Populate form from existing User
     *
     * @param u User
     */
    public UserSettingsForm(User u) {
        this.alias = u.getAlias();
        this.specialties = u.getSpecialties();
        this.weaknesses = u.getWeaknesses();
    }

    /**
     * Copy user settings into a User
     *
     * @param u User
     */
    public void populate(User u) {
        u.setAlias(alias);
        u.setSpecialties(specialties);
        u.setWeaknesses(weaknesses);
    }
}
