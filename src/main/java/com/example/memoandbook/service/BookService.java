package com.example.memoandbook.service;

import com.example.memoandbook.alraddin.AladdinOpenAPI;
import com.example.memoandbook.domain.common.CustomException;
import com.example.memoandbook.domain.common.ErrorCode;
import com.example.memoandbook.domain.model.Book;
import com.example.memoandbook.domain.model.User;
import com.example.memoandbook.domain.model.UserBook;
import com.example.memoandbook.domain.repository.UserBookRepository;
import com.example.memoandbook.domain.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
  private final UserBookRepository userBookRepository;
  private final UserRepository userRepository;
  public List<Book> searchBook(String keyword) {
    try {
      return AladdinOpenAPI.getBooksFromKeyword(keyword);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  public Book findABook(String isbn13) {
    try {
      return AladdinOpenAPI.getOneBook(isbn13);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  public UserBook saveInMyBook(String isbn13, User user) {
    try {
      Book book = AladdinOpenAPI.getOneBook(isbn13);
      if (book == null) throw new CustomException(ErrorCode.BOOK_NOT_FOUND);

      UserBook userBook = UserBook.fromBook(book);
      userBook.setUser(user);
      userBookRepository.save(userBook);
      user.getUserBooks().add(userBook);
      userRepository.save(user);

      return userBook;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public List<UserBook> getMyBooks(User user) {
    return userBookRepository.findAllByUser(user);
  }
}
