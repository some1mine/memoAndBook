package com.example.memoandbook.domain.dto;

import com.example.memoandbook.domain.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
  private String isbn13;
  private String title;
  private String author;
  private String pubdate;
  private String description;

  public static BookDto from(Book book) {
    return BookDto.builder()
        .isbn13(book.getIsbn13())
        .title(book.getTitle())
        .author(book.getAuthor())
        .pubdate(book.getPubDate())
        .description(book.getDescription())
        .build();
  }
}
