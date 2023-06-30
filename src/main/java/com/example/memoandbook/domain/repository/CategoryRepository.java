package com.example.memoandbook.domain.repository;

import com.example.memoandbook.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
