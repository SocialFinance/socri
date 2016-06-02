package services;

import models.User;
import play.mvc.Http;

/**
 * User services not directly related to CRUD
 */
public interface UserService {
    /**
     * Get the User from the existing HTTP session cookie
     *
     * @param session
     * @return User, null if not found
     */
    User getConnected(Http.Session session);
}
