package com.project.auction.service;

import com.project.auction.dto.CreateAuctionRequest;
import com.project.auction.entity.Auction;
import com.project.auction.entity.AuctionStatus;
import com.project.auction.entity.User;
import com.project.auction.repository.AuctionRepository;
import com.project.auction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;

    public Auction createAuction(CreateAuctionRequest request, String sellerEmail) {
        User seller = userRepository.findByEmail(sellerEmail)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Auction auction = Auction.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .basePrice(request.getBasePrice())
                .currentPrice(request.getBasePrice())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .status(AuctionStatus.UPCOMING)
                .seller(seller)
                .build();

        return auctionRepository.save(auction);
    }

    public List<Auction> getLiveAuctions() {
        return auctionRepository.findByStatus(AuctionStatus.LIVE);
    }

    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }
}
