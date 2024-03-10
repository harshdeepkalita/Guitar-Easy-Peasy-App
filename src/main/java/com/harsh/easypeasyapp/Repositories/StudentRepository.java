package com.harsh.easypeasyapp.Repositories;

import com.harsh.easypeasyapp.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
