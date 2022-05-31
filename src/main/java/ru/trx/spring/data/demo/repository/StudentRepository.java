package ru.trx.spring.data.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.trx.spring.data.demo.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select s.* from student s " +
            "join exam_sheet_students es on s.id = es.students_id " +
            "where es.exam_sheet_id = ?1", nativeQuery = true)
    List<Student> findAllByExamSheetId(Integer examSheetId);

}