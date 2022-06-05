package ru.trx.spring.data.demo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.trx.spring.data.demo.entity.ExamSheet;

import java.util.List;

public interface ExamSheetRepository extends JpaRepository<ExamSheet, Integer> {

    List<ExamSheet> findAllByNameContainingIgnoreCase(String name);

    List<ExamSheet> findAllByNameContainingIgnoreCase(String name, Sort sort);

    List<ExamSheet> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("select e from ExamSheet e join e.students students where students.id = ?1")
    List<ExamSheet> findAllByStudentId(Integer id);

}