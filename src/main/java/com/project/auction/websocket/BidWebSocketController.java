package com.project.auction.websocket;

import com.project.auction.dto.BidRequest;
import com.project.auction.entity.Auction;
import com.project.auction.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BidWebSocketController {

    private final BidService bidService;

    @MessageMapping("/bid")
    @SendTo("/topic/bids")
    public Auction placeBid(BidRequest request) {
        return bidService.placeBid(request);
    }
}
