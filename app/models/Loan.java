package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Loan extends Model {

    @Id
    public String id;

    @play.data.validation.Constraints.Required
    public String userId;

    @play.data.validation.Constraints.Required
    public String type;

    @play.data.validation.Constraints.Required
    public double amount;

    @play.data.validation.Constraints.Required
    public long start;

    @play.data.validation.Constraints.Required
    public long end;

    @play.data.validation.Constraints.Required
    public double rate;

    @play.data.validation.Constraints.Required
    public double originalAmount;

    @play.data.validation.Constraints.Required
    public String backerId;

    public static Finder<String, Loan> find = new Model.Finder<>(String.class, Loan.class);

    public static List<Loan> allForUser(String id) {
        return find.filter().eq("userId", id).filter(find.all());
    }
}
