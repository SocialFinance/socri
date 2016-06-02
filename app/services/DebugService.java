package services;

import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import play.Play;

import javax.inject.Inject;

/**
 * Helper to populate the database with a default user.
 * Disable via application.conf (socri.debug)
 */
@Component
public class DebugService implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(DebugService.class);

    @Inject private UserServiceDao userDao;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String user = Play.application().configuration().getString("socri.debug.user.name");
        if (userDao.findFirstByUsername(user) == null) {
            String password = Play.application().configuration().getString("socri.debug.user.password");
            logger.info("##############################################################################");
            logger.info("# DEBUG User " + user + ":" + password + " created!");
            logger.info("##############################################################################");

            if (Play.application().configuration().getBoolean("socri.debug.user.create")) {
                User u = new User();
                u.setUsername(user);
                u.setPassword(password);
                u.setAlias("Debug user");
                u.setSpecialties("Debugging, wasting time");
                u.setWeaknesses("Fish tacos");
                userDao.save(u);
            }
        }
    }
}
