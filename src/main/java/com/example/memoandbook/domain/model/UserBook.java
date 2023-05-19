package com.example.memoandbook.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserBook {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String description;
  @ManyToOne
  @JsonBackReference
  private User user;
  private Integer readPage;
  private Integer totalPage;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private LocalDateTime deletedDate;

  public static UserBook fromBook(Book book) {
    return UserBook.builder()
        .title(book.getTitle())
        .description(book.getDescription())
        .totalPage(book.getTotalPage())
        .createdDate(LocalDateTime.now())
        .build();
  }
}
