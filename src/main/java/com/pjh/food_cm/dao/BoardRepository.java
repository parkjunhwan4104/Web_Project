package com.pjh.food_cm.dao;

import com.pjh.food_cm.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByName(String name);
}
