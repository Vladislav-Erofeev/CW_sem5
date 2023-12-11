package Erofeev.MusicStoreCWsem4.controllers;

import Erofeev.MusicStoreCWsem4.dto.*;
import Erofeev.MusicStoreCWsem4.errors.ItemNotFoundException;
import Erofeev.MusicStoreCWsem4.mappers.ItemMapper;
import Erofeev.MusicStoreCWsem4.mappers.OrderMapper;
import Erofeev.MusicStoreCWsem4.services.ItemImageService;
import Erofeev.MusicStoreCWsem4.services.ItemService;
import Erofeev.MusicStoreCWsem4.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('admin')")
public class AdminController {
    private final OrderService orderService;
    private final ItemService itemService;
    private final ItemImageService itemImageService;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;


    @GetMapping("/items")
    public List<ListItemDTO> getItems(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "limit", defaultValue = "20") int limit) {
        return itemService.findAll(page, limit).stream()
                .map(itemMapper::convertItemToListItemDTO).collect(Collectors.toList());
    }

    @GetMapping("/orders")
    public List<OrderListItemDTO> getOrders(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "limit", defaultValue = "20") int limit) {
        return orderService.findAll(page, limit).stream()
                .map(orderMapper::convertOrderToOrderListItemDTO).collect(Collectors.toList());
    }

    @GetMapping("/order/{id}")
    public OrderDTO getOrder(@PathVariable("id") long id) {
        return orderMapper.convertOrderToOrderDTO(orderService.findById(id));
    }

    @GetMapping("/item/{id}")
    public ItemDTO getItem(@PathVariable("id") long id) throws ItemNotFoundException {
        return itemMapper.convertItemToItemDTO(itemService.findById(id));
    }

    @PostMapping("/add_item")
    public ResponseEntity<Long> addNewItem(@RequestBody NewItemDTO newItemDTO) {
        long id = itemService.save(itemMapper.convertNewItemDTOToItem(newItemDTO));
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/item/{id}")
    public void deleteItemById(@PathVariable("id") long id) {
        itemService.deleteById(id);
    }

    @PostMapping("/load_image/{id}")
    public void loadImage(@PathVariable("id") long itemId, @RequestBody MultipartFile file)
            throws IOException, ItemNotFoundException {
        itemImageService.save(itemId, file);
    }
}
