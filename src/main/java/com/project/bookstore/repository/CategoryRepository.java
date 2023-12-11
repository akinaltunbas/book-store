package com.project.bookstore.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.project.bookstore.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	boolean existsByName(String name);
}
