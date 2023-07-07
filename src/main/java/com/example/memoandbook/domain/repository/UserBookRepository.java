package com.example.memoandbook.domain.repository;

import com.example.memoandbook.domain.model.User;
import com.example.memoandbook.domain.model.UserBook;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {
  Optional<UserBook> findByIsbn13(String isbn13);

  List<UserBook> findAllByUser(User user);
}
