package com.stocksafe.tasks;


import com.stocksafe.services.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerifyStokeTime {

    private final BusinessService businessService;

    @Scheduled(cron = "0 0 0 * * ?",zone = "America/Sao_Paulo")
    public void verifyBoxesExpiredDate() {

        this.businessService.verify();

    }
}
