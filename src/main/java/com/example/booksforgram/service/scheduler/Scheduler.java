package com.example.booksforgram.service.scheduler;

import com.example.booksforgram.service.PromoBookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class Scheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    private final PromoBookService promoBookService;

    @Autowired
    public Scheduler(PromoBookService promoBookService) {
        this.promoBookService = promoBookService;
    }

    @Scheduled(cron =  "0 0 18 * * ?")
    public void promotionStart() {
        LOGGER.info("Start promo");
//        promoBookService.init();
        promoBookService.initPromoBooks();
    }
    @Scheduled(cron = "0 0 20 * * ?")
    public void promotionStop() {
        LOGGER.info("End promo");
//        promoBookService.init();
        promoBookService.deletePromoBooks();
    }
}
