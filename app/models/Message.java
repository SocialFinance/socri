package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message extends Model {

    @Id
    public String id;

    public Boolean show;

    public String text;

    public String classes;

    public static class BlankMessage extends Message {
        public BlankMessage() {
            id = java.util.UUID.randomUUID().toString();
            show = false;
            text = "";
            classes = "";
        }
    }

    public static class ErrorMessage extends Message {
        public ErrorMessage() {
            id = java.util.UUID.randomUUID().toString();
            show = true;
            text = "";
            classes = "alert alert-danger";
        }

        public ErrorMessage(String msg) {
            this();
            text = msg;
        }
    }

    public static class InfoMessage extends Message {
        public InfoMessage() {
            id = java.util.UUID.randomUUID().toString();
            show = true;
            text = "";
            classes = "alert alert-primary";
        }

        public InfoMessage(String msg) {
            this();
            text = msg;
        }
    }
}
