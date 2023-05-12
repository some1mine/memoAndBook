package com.example.memoandbook.domain.dto;

import com.example.memoandbook.domain.model.User;
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
public class UserDto {
  private Long id;
  private String email;
  private String name;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private LocalDateTime deletedDate;

  public static UserDto from(User user) {
    return UserDto.builder()
        .id(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .createdDate(user.getCreatedDate())
        .updatedDate(user.getUpdatedDate())
        .deletedDate(user.getDeletedDate())
        .build();
  }
}
