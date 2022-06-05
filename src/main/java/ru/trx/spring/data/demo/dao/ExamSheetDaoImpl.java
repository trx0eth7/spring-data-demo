package ru.trx.spring.data.demo.dao;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.trx.spring.data.demo.entity.ExamSheet;

import java.util.List;

/**
 * @author Alexander Vasiliev
 */
@Repository
public class ExamSheetDaoImpl implements ExamSheetDao {

    protected SessionFactory sessionFactory;

    public ExamSheetDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(ExamSheet examSheet) {
        var tx = (Transaction) null;

        try (var session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.save(examSheet);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public List<ExamSheet> findAllByName(String name) {
        var tx = (Transaction) null;

        try (var session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            var examSheets = session.createQuery("select e from ExamSheet e " +
                            "where e.name = :name", ExamSheet.class)
                    .setParameter("name", name)
                    .getResultList();

            tx.commit();

            return examSheets;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}