package com.project.auction.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateAuctionRequest {
    private String title;
    private String description;
    private BigDecimal basePrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
