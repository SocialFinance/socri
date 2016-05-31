package services;

import models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import play.mvc.Http;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Service
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
        em.persist(user);
    }

    public User getConnected(Http.Session session) {
        User ret = null;
        if (session.containsKey("userid")) {
            ret = getById(Integer.parseInt(session.get("userid")));
        }
        return ret;
    }

    public User getById(int id) {
        User u = em.find(User.class, id);
        if (u != null) {
            u.setPassword("You can fuck right off");
        }
        return u;
    }

    public User getByUsername(String name) {

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> q = cb.createQuery(User.class);
//        Root<User> c = q.from(User.class);
//        ParameterExpression<String> p = cb.parameter(String.class);
//        q.select(c).where(cb.equal(c.get("username"), p));
//
//        TypedQuery<User> tq = em.createQuery(q);
//        tq.setParameter(p, name);
//        try {
//            return tq.getSingleResult();
//        } catch (NoResultException e) {
//            e.printStackTrace();
//            return null;
//        }

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
