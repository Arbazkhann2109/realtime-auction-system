package com.project.auction.service;

import com.project.auction.dto.BidRequest;
import com.project.auction.entity.Auction;
import com.project.auction.entity.AuctionStatus;
import com.project.auction.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BidService {

    private final AuctionRepository auctionRepository;

    @Transactional
    public Auction placeBid(BidRequest request) {
        Auction auction = auctionRepository.findById(request.getAuctionId())
                .orElseThrow(() -> new RuntimeException("Auction not found"));

        if (auction.getStatus() != AuctionStatus.LIVE) {
            throw new RuntimeException("Auction is not live");
        }

        if (request.getAmount().compareTo(auction.getCurrentPrice()) <= 0) {
            throw new RuntimeException("Bid must be higher than current price");
        }

        auction.setCurrentPrice(request.getAmount());
        return auctionRepository.save(auction);
    }

    public void updateAuctionStatus() {
        LocalDateTime now = LocalDateTime.now();
        auctionRepository.findAll().forEach(auction -> {
            if (auction.getStatus() == AuctionStatus.UPCOMING && now.isAfter(auction.getStartTime())) {
                auction.setStatus(AuctionStatus.LIVE);
            }
            if (auction.getStatus() == AuctionStatus.LIVE && now.isAfter(auction.getEndTime())) {
                auction.setStatus(AuctionStatus.ENDED);
            }
            auctionRepository.save(auction);
        });
    }
}
