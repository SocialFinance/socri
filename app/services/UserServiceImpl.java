package services;

import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import play.mvc.Http;

import javax.inject.Inject;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Inject private UserServiceDao userDao;

    @Override public User getConnected(Http.Session session) {
        try {
            if (session.containsKey("userid")) {
                return userDao.findOne(Integer.parseInt(session.get("userid")));
            }
        } catch (NumberFormatException | NullPointerException e) {
            logger.warn("Unable to get userid from user session: " + session);
        }
        return null;
    }
}
