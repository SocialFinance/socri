package services;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import play.Play;

/**
 * Helper to populate the database with a default user.
 * Disable via application.conf (socri.debug)
 */
@Component
public class DebugService implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String user = Play.application().configuration().getString("socri.debug.user.name");
        if (userService.getByUsername(user) == null) {
            String password = Play.application().configuration().getString("socri.debug.user.password");
            System.out.println("##############################################################################");
            System.out.println("# DEBUG User " + user + ":" + password + " created!");
            System.out.println("##############################################################################");

            if (Play.application().configuration().getBoolean("socri.debug.user.create")) {
                User u = new User();
                u.setUsername(user);
                u.setPassword(password);
                u.setAlias("Debug user");
                u.setSpecialties("Debugging, wasting time");
                u.setWeaknesses("Fish tacos");
                userService.saveUser(u);
            }
        }
    }
}
