package ru.trx.spring.data.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.trx.spring.data.demo.entity.ExamSheet;

import java.util.List;

public interface ExamSheetRepository extends JpaRepository<ExamSheet, Integer> {

    List<ExamSheet> findByNameContainingIgnoreCase(String name);




}