package com.example.memoandbook.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
  private String isbn13;
  private String title;
  private String description;
  @ManyToOne
  @JsonBackReference
  private User user;
  @OneToMany
  @JsonManagedReference
  private List<Memo> memos;
  private Integer readPage;
  private Integer totalPage;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private LocalDateTime deletedDate;

  public static UserBook fromBook(Book book) {
    return UserBook.builder()
        .isbn13(book.getIsbn13())
        .title(book.getTitle())
        .description(book.getDescription())
        .totalPage(book.getTotalPage())
        .createdDate(LocalDateTime.now())
        .build();
  }
}
