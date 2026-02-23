package com.example.control.synapse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.control.synapse.dto.request.MerchandiseOrderRequest;
import com.example.control.synapse.dto.response.MerchandiseOrderResponseDto;
import com.example.control.synapse.service.interfaces.IMerchandiseOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/merchandiseOrder")
@RequiredArgsConstructor
public class MerchandiseOrderController {

    private final IMerchandiseOrderService merchandiseOrderService;

    @GetMapping("/allMerchandiseOrders")
    public List<MerchandiseOrderResponseDto> getAllMerchandiseOrders() {
        return merchandiseOrderService.getAllMerchandiseOrders();
    }

    @PostMapping("/placeMerchandiseOrder")
    public Map<String, String> bookMerchandiseOrder(@RequestBody MerchandiseOrderRequest request) {
        return merchandiseOrderService.bookMerchandiseOrder(
                request.getMerchandiseIdlist(),
                request.getUserId(),
                request.getPrice(),
                request.getSeatId(),
                request.getStadiumId(),
                request.getEventId(),
                request.getOrderTime());
    }

    @GetMapping("/user/{userId}")
    public List<MerchandiseOrderResponseDto> getMerchandiseOrderByUserId(@PathVariable Long userId) {
        return merchandiseOrderService.getMerchandiseOrderByUserId(userId);
    }

    @GetMapping("/{id}")
    public MerchandiseOrderResponseDto getMerchandiseOrderById(@PathVariable Long id) {
        return merchandiseOrderService.getMerchandiseOrderById(id);
    }

    @GetMapping("/stadium/{stadiumId}")
    public List<MerchandiseOrderResponseDto> getMerchandiseOrderByStadiumId(@PathVariable Long stadiumId) {
        return merchandiseOrderService.getMerchandiseOrderByStadiumId(stadiumId);
    }

}
