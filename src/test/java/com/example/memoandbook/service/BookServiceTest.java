package com.example.memoandbook.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.memoandbook.domain.model.Book;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {
  @Autowired
  private BookService bookService;
  @Test
  void getBookTest() {
    String keyword = "마음";
    List<Book> bookList = bookService.searchBook(keyword);
    System.out.println(bookList);
    assertNotNull(bookList);
  }

  @Test
  void getOneBookTest() {
    String isbn13 = "9791168473690";
    Book book = bookService.findABook(isbn13);
    System.out.println(book);
    assertNotNull(book);
  }
}