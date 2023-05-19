package com.example.memoandbook.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  private String isbn13;
  private String title;
  private String author;
  private String pubDate;
  private String description;
  private Integer totalPage;
}
