package com.example.memoandbook.service;

import com.example.memoandbook.domain.common.CustomException;
import com.example.memoandbook.domain.common.ErrorCode;
import com.example.memoandbook.domain.form.MemoForm;
import com.example.memoandbook.domain.model.Category;
import com.example.memoandbook.domain.model.Memo;
import com.example.memoandbook.domain.model.UserBook;
import com.example.memoandbook.domain.repository.CategoryRepository;
import com.example.memoandbook.domain.repository.MemoRepository;
import com.example.memoandbook.domain.repository.UserBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoService {
  private final CategoryRepository categoryRepository;
  private final UserBookRepository userBookRepository;
  private final MemoRepository memoRepository;

  public Memo saveMemoInMyBook(MemoForm form) {
    UserBook userBook = userBookRepository.findByIsbn13(form.getIsbn13())
        .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
    Memo memo = Memo.from(form);
    Category category = categoryRepository.findById(form.getCategoryId())
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    memo.setCategory(category);
    memo.setUserBook(userBook);
    userBook.getMemos().add(memo);
    memoRepository.save(memo);
    userBookRepository.save(userBook);

    return memo;
  }
}
