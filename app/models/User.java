package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import forms.UserForm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User!
 * This is the User. Users use the website.
 */
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String alias;

    @Column
    private String specialties;

    @Column
    private String weaknesses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSpecialties() {
        return specialties;
    }

    public void setSpecialties(String specialties) {
        this.specialties = specialties;
    }

    public String getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses = weaknesses;
    }

    public static User fromForm(UserForm newUser) {
        User u = new User();
        u.setUsername(newUser.username);
        u.setPassword(newUser.password);
        u.setSpecialties(newUser.specialties);
        u.setWeaknesses(newUser.weaknesses);
        u.setAlias(newUser.alias);
        return u;
    }
}
