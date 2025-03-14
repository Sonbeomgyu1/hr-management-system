package com.apple.shop.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // board 검색 기능
    @Query(value = "select * from shop.board where boardtitle like %?1%", nativeQuery = true)
    List<Board> rowQuery2(String text);
}
