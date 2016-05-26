package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model {

    @Id
    public String id;

    @play.data.validation.Constraints.Required
    public String username;

    @play.data.validation.Constraints.Required
    public String password;

    @play.data.validation.Constraints.Required
    public String alias;

    @play.data.validation.Constraints.Required
    public String specialties;

    public String weaknesses;

    public static Finder<Integer, User> find = new Model.Finder<>(Integer.class, User.class);

    public static java.util.List<models.User> findByUsername(String username) {
        return find.where().like("username", "%"+username+"%").findList();
    }

    public static boolean isUsernameAvailable(String username) {
        return findByUsername(username).isEmpty();
    }

    public static User getConnectedUser(play.mvc.Http.Session session) {
        User ret = null;
        if(session.containsKey("connected")) {
            ret = User.find.byId(Integer.parseInt(session.get("connected")));
            if(ret != null) {
                ret.password = "You can fuck right off";
            }
        }
        return ret;
    }
}
