package ru.trx.spring.data.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author Alexander Vasiliev
 */
public class CustomExamSheetRepositoryImpl implements CustomExamSheetRepository {

    @PersistenceContext
    protected EntityManager em;

    public CustomExamSheetRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public void deleteStudent(Integer examSheetId, Integer studentId) {
        em.createNativeQuery("delete from exam_sheet_students es " +
                "where es.exam_sheet_id = :examSheetId and es.students_id = :studentId")
                .setParameter("studentId", studentId)
                .setParameter("examSheetId", examSheetId)
                .executeUpdate();
    }
}
