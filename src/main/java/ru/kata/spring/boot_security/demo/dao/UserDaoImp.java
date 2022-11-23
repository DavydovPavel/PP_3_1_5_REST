package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Users> listUsers() {
        return entityManager.createQuery("FROM Users", Users.class).getResultList();
    }

    @Override
    public Users getUser(long id) {
        return entityManager.find(Users.class, id);
    }

    @Override
    public void add(Users user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUserByID(long id) {
        entityManager.remove(entityManager.find(Users.class, id));
    }

    @Override
    public void updateUserByID(Users user, long id) {
        entityManager.merge(user);
    }

    @Override
    public Users getName(String nickName) {
        return entityManager
                .createQuery("SELECT user FROM Users user WHERE user.nickName=:nickname", Users.class)
                .setParameter("nickname", nickName)
                .getSingleResult();
    }
}
