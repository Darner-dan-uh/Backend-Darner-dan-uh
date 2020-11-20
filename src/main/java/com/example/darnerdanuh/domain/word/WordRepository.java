package com.example.darnerdanuh.domain.word;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {

    @Query(value="SELECT * FROM word ORDER BY RAND()", nativeQuery = true)
    List<Word> findByWord_id(int word_id);
}
