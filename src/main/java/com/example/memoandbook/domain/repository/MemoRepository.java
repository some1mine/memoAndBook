package com.example.memoandbook.domain.repository;

import com.example.memoandbook.domain.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {

}
