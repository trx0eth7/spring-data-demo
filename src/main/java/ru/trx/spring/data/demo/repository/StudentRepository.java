package ru.trx.spring.data.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.trx.spring.data.demo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}