package com.example.memoandbook.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemoForm {
  private String title;
  private String isbn13;
  private String content;
  private Integer page;
}
