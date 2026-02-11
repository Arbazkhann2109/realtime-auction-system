package com.project.auction.controller;

import com.project.auction.dto.CreateAuctionRequest;
import com.project.auction.entity.Auction;
import com.project.auction.security.JwtUtil;
import com.project.auction.service.AuctionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auctions")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public Auction create(@RequestBody CreateAuctionRequest request, HttpServletRequest httpRequest) {
        String authHeader = httpRequest.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);

        return auctionService.createAuction(request, email);
    }

    @GetMapping("/live")
    public List<Auction> liveAuctions() {
        return auctionService.getLiveAuctions();
    }

    @GetMapping
    public List<Auction> allAuctions() {
        return auctionService.getAllAuctions();
    }
}
