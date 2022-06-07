package ru.trx.spring.data.demo.dao;

import org.springframework.stereotype.Repository;
import ru.trx.spring.data.demo.entity.ExamSheet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * @author Alexander Vasiliev
 */
@Repository
public class ExamSheetDaoImpl implements ExamSheetDao {

    @PersistenceUnit
    protected EntityManagerFactory entityManagerFactory;

    public ExamSheetDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void insert(ExamSheet examSheet) {
        var tx = (EntityTransaction) null;
        var em = (EntityManager) null;

        try {
            em = entityManagerFactory.createEntityManager();

            tx = em.getTransaction();
            tx.begin();

            em.persist(examSheet);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public List<ExamSheet> findAllByName(String name) {
        var tx = (EntityTransaction) null;
        var em = (EntityManager) null;

        try {
            em = entityManagerFactory.createEntityManager();

            tx = em.getTransaction();
            tx.begin();

            var examSheets = em.createQuery("select e from ExamSheet e " +
                            "where e.name = :name", ExamSheet.class)
                    .setParameter("name", name)
                    .getResultList();

            tx.commit();

            return examSheets;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            if (em != null) em.close();
        }
    }
}