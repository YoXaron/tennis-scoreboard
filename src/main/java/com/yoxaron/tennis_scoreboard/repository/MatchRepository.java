package com.yoxaron.tennis_scoreboard.repository;

import com.yoxaron.tennis_scoreboard.model.entity.Match;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class MatchRepository extends CrudRepository<Long, Match> {

    public static final MatchRepository INSTANCE = new MatchRepository();

    private MatchRepository() {
        super(Match.class);
    }

    public static MatchRepository getInstance() {
        return INSTANCE;
    }

    public List<Match> findByNameWithPagination(String name, int page, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            var criteriaBuilder = session.getCriteriaBuilder();
            var criteriaQuery = criteriaBuilder.createQuery(Match.class);
            var root = criteriaQuery.from(Match.class);

            applyNameFilter(name, criteriaBuilder, criteriaQuery, root);

            List<Match> resultList = session.createQuery(criteriaQuery)
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();

            session.getTransaction().commit();

            return resultList;
        } catch (Exception e) {
            throw new RuntimeException(e); //todo
        }
    }

    public int countMatches(String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            var criteriaBuilder = session.getCriteriaBuilder();
            var countQuery = criteriaBuilder.createQuery(Long.class);
            var root = countQuery.from(Match.class);

            countQuery.select(criteriaBuilder.count(root));

            applyNameFilter(name, criteriaBuilder, countQuery, root);

            Long count = session.createQuery(countQuery).getSingleResult();
            session.getTransaction().commit();

            return count.intValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> void applyNameFilter(String playerName, CriteriaBuilder criteriaBuilder,
                                     CriteriaQuery<T> query, Root<Match> root) {
        if (playerName != null && !playerName.trim().isEmpty()) {
            Predicate predicate = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("playerOne").get("name")),
                            "%" + playerName.toLowerCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("playerTwo").get("name")),
                            "%" + playerName.toLowerCase() + "%")
            );
            query.where(predicate);
        }
    }
}
