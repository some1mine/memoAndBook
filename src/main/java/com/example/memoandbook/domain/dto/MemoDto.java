package com.example.memoandbook.domain.dto;

import com.example.memoandbook.domain.model.Category;
import com.example.memoandbook.domain.model.Memo;
import com.example.memoandbook.domain.model.User;
import com.example.memoandbook.domain.model.UserBook;
import java.time.LocalDateTime;
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
public class MemoDto {
  private Long id;
  private String title;
  private String content;
  private Integer likes;
  private Integer page;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private LocalDateTime deletedDate;
  private User user;
  private UserBook userBook;
  private Category category;

  public static MemoDto from(Memo memo) {
    return MemoDto.builder()
        .id(memo.getId())
        .title(memo.getTitle())
        .content(memo.getContent())
        .likes(memo.getLikes())
        .page(memo.getPage())
        .createdDate(memo.getCreatedDate())
        .updatedDate(memo.getUpdatedDate())
        .deletedDate(memo.getDeletedDate())
        .user(memo.getUser())
        .userBook(memo.getUserBook())
        .category(memo.getCategory())
        .build();
  }
}
