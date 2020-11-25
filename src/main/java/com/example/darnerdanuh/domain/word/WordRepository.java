package com.example.darnerdanuh.domain.word;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {

    @Query("select m from Word m where m.word_id = :word_id order by function('RAND') ")
    List<Word> findByWord_id(@Param("word_id") int word_id);

    @Query(value = "SELECT * FROM word_test WHERE word_used=1 ORDER BY RAND()", nativeQuery = true)
    List<Word> findAllByWord_used();
}
