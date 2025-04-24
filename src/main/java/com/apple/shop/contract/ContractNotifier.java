package com.apple.shop.contract;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ContractNotifier {
	
	 private final ContractRepository contractRepository;

	    @Scheduled(cron = "0 0 9 * * *") // 매일 오전 9시에 실행
	    public void notifyUpcomingContracts() {
	        List<Contract> contracts = contractRepository.findAll();

	        for (Contract contract : contracts) {
	            if (!contract.isNotified() && contract.getEndDate().isBefore(LocalDate.now().plusDays(3))) {
	                // TODO: 이메일 or 알림 전송
	                System.out.println("📢 만료 예정 계약: " + contract.getTitle());

	                contract.setNotified(true);
	                contractRepository.save(contract);
	            }
	        }
	    }
}
