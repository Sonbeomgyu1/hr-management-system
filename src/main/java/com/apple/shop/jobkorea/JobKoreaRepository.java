package com.apple.shop.jobkorea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobKoreaRepository extends JpaRepository<JobKorea, Long> {

    @Query("SELECT j FROM JobKorea j WHERE " +
            "(:searchJobKoreaTypeCd IS NULL OR LOWER(j.jobKoreaTypeCd) LIKE LOWER(CONCAT('%', :searchJobKoreaTypeCd, '%'))) AND " +
            "(:searchDisplayName IS NULL OR LOWER(j.displayName) LIKE LOWER(CONCAT('%', :searchDisplayName, '%'))) AND " +
            "(:searchArea IS NULL OR LOWER(j.area) LIKE LOWER(CONCAT('%', :searchArea, '%'))) AND " +
            "(:searchName IS NULL OR LOWER(j.name) LIKE LOWER(CONCAT('%', :searchName, '%'))) AND " +
            "(:searchSkill IS NULL OR LOWER(j.skill) LIKE LOWER(CONCAT('%', :searchSkill, '%')))")
    List<JobKorea> findBySearchFields(
            @Param("searchJobKoreaTypeCd") String searchJobKoreaTypeCd,
            @Param("searchDisplayName") String searchDisplayName,
            @Param("searchArea") String searchArea,
            @Param("searchName") String searchName,
            @Param("searchSkill") String searchSkill);
}
