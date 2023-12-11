package Erofeev.MusicStoreCWsem4.controllers;

import Erofeev.MusicStoreCWsem4.dto.NewOrderDTO;
import Erofeev.MusicStoreCWsem4.dto.OrderDTO;
import Erofeev.MusicStoreCWsem4.dto.OrderListItemDTO;
import Erofeev.MusicStoreCWsem4.errors.PersonNotFoundException;
import Erofeev.MusicStoreCWsem4.mappers.OrderMapper;
import Erofeev.MusicStoreCWsem4.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    @GetMapping
    public List<OrderListItemDTO> getOrders(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        return orderService.findAll(principal.getAttribute("id")).stream()
                .map(orderMapper::convertOrderToOrderListItemDTO).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public void createOrder(@RequestBody NewOrderDTO newOrderDTO,
                            @AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) throws PersonNotFoundException {
        orderService.createNewOrder(principal.getAttribute("id"), newOrderDTO.getAddress(), newOrderDTO.getItems());
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable("id") long id) {
        return orderMapper.convertOrderToOrderDTO(orderService.findById(id));
    }
}
