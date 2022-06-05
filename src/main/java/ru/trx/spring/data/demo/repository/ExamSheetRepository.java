package ru.trx.spring.data.demo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.trx.spring.data.demo.entity.ExamSheet;

import java.util.List;

public interface ExamSheetRepository extends JpaRepository<ExamSheet, Integer> {

    List<ExamSheet> findAllByNameContainingIgnoreCase(String name);

    List<ExamSheet> findAllByNameContainingIgnoreCase(String name, Sort sort);

    List<ExamSheet> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}