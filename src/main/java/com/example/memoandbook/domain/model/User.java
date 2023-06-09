package com.example.memoandbook.domain.model;

import com.example.memoandbook.domain.form.SignUpForm;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private String password;
  private String name;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private LocalDateTime deletedDate;
  @OneToMany
  @JsonManagedReference
  private List<UserBook> userBooks;
  @OneToMany
  @JsonManagedReference
  private List<Memo> memos;
  @OneToMany
  @JsonManagedReference
  private List<Category> categories;
  public static User from(SignUpForm form) {
    return User.builder()
        .email(form.getEmail())
        .password(form.getPassword())
        .name(form.getName())
        .createdDate(LocalDateTime.now())
        .build();
  }
  public User hashPassword(PasswordEncoder passwordEncoder) {
    this.password = passwordEncoder.encode(this.password);
    return this;
  }
  public boolean checkPassword(String plainPassword, PasswordEncoder passwordEncoder) {
    return passwordEncoder.matches(plainPassword, this.password);
  }
}
