package com.example.memoandbook.domain.model;

import com.example.memoandbook.domain.form.MemoForm;
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
public class Memo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private Integer page;
  @ManyToOne
  @JsonBackReference
  private User user;
  @ManyToOne
  @JsonBackReference
  private UserBook userBook;
  private Integer likes;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private LocalDateTime deletedDate;
  @ManyToOne
  @JsonBackReference
  private Category category;

  public static Memo from(MemoForm form) {
    return Memo.builder()
        .title(form.getTitle())
        .content(form.getContent())
        .page(form.getPage())
        .likes(0)
        .createdDate(LocalDateTime.now())
        .build();
  }
}
