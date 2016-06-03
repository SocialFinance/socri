package services;

import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import play.Play;

/**
 * Debug helper to populate the database with a default user.
 * Disable via application.conf (socri.debug)
 */
@Component
public class DebugService implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(DebugService.class);

    @Autowired private UserServiceDao userDao;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        try {
            if (Play.application().configuration().getBoolean("socri.debug.user.create")) {
                String user = Play.application().configuration().getString("socri.debug.user.name");
                String pass = Play.application().configuration().getString("socri.debug.user.password");
                if (userDao.findFirstByUsername(user) == null) {
                    logger.info("DEBUG User " + user + ":" + pass + " created!");

                    User u = new User();
                    u.setUsername(user);
                    u.setPassword(pass);
                    u.setAlias("Debug user");
                    u.setSpecialties("Debugging, wasting time");
                    u.setWeaknesses("Fish tacos");
                    userDao.save(u);
                }
            }
        } catch (RuntimeException e) {
            //This can happen in unit tests that want to use spring but not the full play framework
            logger.error("Debug user is probably unavailable. Play application configuration failed: " + e.getMessage());
        }
    }
}
