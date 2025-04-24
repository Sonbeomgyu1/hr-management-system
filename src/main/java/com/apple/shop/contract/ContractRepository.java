package com.apple.shop.contract;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

	List<Contract> findByEndDateBefore(LocalDate date); // 만료 계약 찾기 용
	
	List<Contract> findByMemberId(Long memberId);

	void deleteByIdAndMemberId(Long id, Long memberId);
	
	Optional<Contract> findByMemberIdAndFilePath(Long memberId, String filePath);

	
	

}
