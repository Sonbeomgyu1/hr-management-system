package com.apple.shop.item;

import org.springframework.data.domain.Page;  // ✅ Spring Data의 Pageable을 사용
import org.springframework.data.domain.Pageable;  // ✅ 올바른 Pageable import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findPageBy(Pageable page);  // ✅ Pageable을 올바르게 사용

//검색 기능
@Query(value = "select * from shop.item where title like %?1%", nativeQuery = true)
    List<Item> rawQuery1(String text);
}
