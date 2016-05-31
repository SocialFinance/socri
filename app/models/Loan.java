package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "loans")
public class Loan {

    @Id
    @Column
    @GeneratedValue
    private String id;

    @Column(name = "user_id")
    @play.data.validation.Constraints.Required
    private String userId;

    @Column(name = "backer_id")
    @play.data.validation.Constraints.Required
    private String backerId;

    @Column
    @play.data.validation.Constraints.Required
    private String type;

    @Column
    @play.data.validation.Constraints.Required
    private double amount;

    @Column
    @play.data.validation.Constraints.Required
    private long start;

    @Column
    @play.data.validation.Constraints.Required
    private long end;

    @Column
    @play.data.validation.Constraints.Required
    private double rate;

    @Column(name = "original_amount")
    @play.data.validation.Constraints.Required
    private double originalAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBackerId() {
        return backerId;
    }

    public void setBackerId(String backerId) {
        this.backerId = backerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(double originalAmount) {
        this.originalAmount = originalAmount;
    }
}
