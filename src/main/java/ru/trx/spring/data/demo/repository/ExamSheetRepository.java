package ru.trx.spring.data.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.trx.spring.data.demo.entity.ExamSheet;

import java.time.LocalDate;
import java.util.List;

public interface ExamSheetRepository extends JpaRepository<ExamSheet, Integer> {

    List<ExamSheet> findAllByNameContainingIgnoreCase(String name);

    @Query("select e from ExamSheet e join e.students students where students.id = ?1")
    List<ExamSheet> findAllByStudentId(Integer id);

    @Query(value = "select e.* from exam_sheet_students es " +
            "join exam_sheet e on e.id = es.exam_sheet_id " +
            "join student s on s.id = es.students_id " +
            "where s.birthday between ?1 and ?2", nativeQuery = true)
    List<ExamSheet> findAllByStudentsBirthdayBetween(LocalDate birthdayStart,
                                                     LocalDate birthdayEnd);


}