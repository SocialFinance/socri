package services;

import models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import play.mvc.Http;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class UserService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public User createUser() {
        User ret = new User();
        em.persist(ret);
        return ret;
    }

    @Transactional
    public void saveUser(User user) {
        em.merge(user);
    }

    @Transactional
    public void deleteUser(int id) {
        em.remove(getById(id));
    }

    public User getConnected(Http.Session session) {
        User ret = null;
        if (session.containsKey("userid")) {
            ret = getById(Integer.parseInt(session.get("userid")));
        }
        return ret;
    }

    public User getById(int id) {
        return em.find(User.class, id);
    }

    public User getByUsername(String name) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT c FROM users c WHERE c.username = :username", User.class);
            return query.setParameter("username", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean checkPassword(int userID, String password) {
        User u = em.find(User.class, userID);
        if (u == null) {
            return false;
        } else {
            return u.getPassword().equals(password);
        }
    }
}
