package com.example.darnerdanuh.domain.memo;

import com.example.darnerdanuh.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByUserId(String userId);
}
