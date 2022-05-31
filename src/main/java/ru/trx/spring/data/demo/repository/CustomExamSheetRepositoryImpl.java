package ru.trx.spring.data.demo.repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * @author Alexander Vasiliev
 */
public class CustomExamSheetRepositoryImpl implements CustomExamSheetRepository {

    @PersistenceUnit
    protected EntityManagerFactory entityManagerFactory;

    public CustomExamSheetRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void deleteStudent(Integer examSheetId, Integer studentId) {
        var em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        em.createNativeQuery("delete from exam_sheet_students es " +
                "where es.exam_sheet_id = :examSheetId and es.students_id = :studentId")
                .setParameter("studentId", studentId)
                .setParameter("examSheetId", examSheetId)
                .executeUpdate();

        em.getTransaction().commit();
        em.close();
    }
}
