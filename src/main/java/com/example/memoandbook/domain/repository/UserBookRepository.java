package com.example.memoandbook.domain.repository;

import com.example.memoandbook.domain.model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {

}
