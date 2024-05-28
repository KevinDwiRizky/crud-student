package org.kevin.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.kevin.entity.Student;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {
}
