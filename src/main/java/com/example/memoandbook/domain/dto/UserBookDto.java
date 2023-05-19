package com.example.memoandbook.domain.dto;

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
public class UserBookDto {
  private Long id;
  private String title;
  private String description;
  private User user;
  private Integer readPage;
  private Integer totalPage;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private LocalDateTime deletedDate;

  public static UserBookDto from(UserBook userBook) {
    return UserBookDto.builder()
        .id(userBook.getId())
        .title(userBook.getTitle())
        .description(userBook.getDescription())
        .user(userBook.getUser())
        .readPage(userBook.getReadPage())
        .totalPage(userBook.getTotalPage())
        .createdDate(userBook.getCreatedDate())
        .updatedDate(userBook.getUpdatedDate())
        .deletedDate(userBook.getDeletedDate())
        .build();
  }
}
