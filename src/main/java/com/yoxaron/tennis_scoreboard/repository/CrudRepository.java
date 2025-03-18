package com.yoxaron.tennis_scoreboard.repository;

import com.yoxaron.tennis_scoreboard.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class CrudRepository<K extends Serializable, E> implements Repository<K, E> {

    protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final Class<E> entityClass;

    @Override
    public List<E> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaQuery<E> criteria = session.getCriteriaBuilder().createQuery(entityClass);
            criteria.from(entityClass);
            List<E> resultList = session.createQuery(criteria)
                    .getResultList();
            session.getTransaction().commit();
            return resultList;
        } catch (Exception e) {
            throw new RuntimeException(e); //todo
        }
    }

    @Override
    public Optional<E> findById(K id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(entityClass, id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public E save(E entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public E update(E entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(E entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
