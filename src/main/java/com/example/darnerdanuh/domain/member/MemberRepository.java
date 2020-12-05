package com.example.darnerdanuh.domain.member;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByEmail(String email);

    @Query("select m.name from member m where m.userId = :userId")
    String findByUserIdToName(@Param("userId") String userId);

    @Query("select m.userId from member m where m.userId = :userId")
    String findByUserIdToId(@Param("userId") String userId);

    List<Member> findAllByOrderByWordCntDesc();
}
