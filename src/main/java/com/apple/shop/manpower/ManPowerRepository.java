package com.apple.shop.manpower;

import com.apple.shop.jobkorea.JobKorea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManPowerRepository extends JpaRepository<ManPower, Long> {

    // 가장 큰 ID를 구하고 그 값에 1을 더한 값 반환
    @Query("SELECT COALESCE(MAX(m.id), 0) + 1 FROM ManPower m")
    Long getNextId();

    @Query("SELECT mp FROM ManPower mp WHERE " +
            "(:searchDisplayName IS NULL OR LOWER(mp.displayName) LIKE LOWER(CONCAT('%', :searchDisplayName, '%'))) AND " +
            "(:searchArea IS NULL OR LOWER(mp.workArea) LIKE LOWER(CONCAT('%', :searchArea, '%'))) AND " +
            "(:searchName IS NULL OR LOWER(mp.name) LIKE LOWER(CONCAT('%', :searchName, '%'))) AND " +
            "(:searchSkills IS NULL OR LOWER(mp.skills) LIKE LOWER(CONCAT('%', :searchSkills, '%'))) AND " +
            "(:searchremark IS NULL OR LOWER(mp.remark) LIKE LOWER(CONCAT('%', :searchremark, '%'))) AND " +
            "(:searchcareerYears IS NULL OR LOWER(mp.careerYears) LIKE LOWER(CONCAT('%', :searchcareerYears, '%')))")
    List<ManPower> findBySearchFields(
            @Param("searchDisplayName") String searchDisplayName,
            @Param("searchArea") String searchArea,
            @Param("searchName") String searchName,
            @Param("searchSkills") String searchSkills,
            @Param("searchremark") String searchremark,
            @Param("searchcareerYears") String searchcareerYears);


}