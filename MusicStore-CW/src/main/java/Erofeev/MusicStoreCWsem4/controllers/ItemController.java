package Erofeev.MusicStoreCWsem4.controllers;

import Erofeev.MusicStoreCWsem4.dto.ItemDTO;
import Erofeev.MusicStoreCWsem4.dto.ListItemDTO;
import Erofeev.MusicStoreCWsem4.errors.ErrorResponse;
import Erofeev.MusicStoreCWsem4.errors.ItemNotFoundException;
import Erofeev.MusicStoreCWsem4.mappers.ItemMapper;
import Erofeev.MusicStoreCWsem4.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")

public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;


    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public List<ListItemDTO> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "limit", defaultValue = "20") int limit,
                                    @RequestParam(value = "category", defaultValue = "") String category) {
        return itemService.findAll(page, limit, category).stream()
                .map(itemMapper::convertItemToListItemDTO).collect(Collectors.toList());
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/search")
    public List<ListItemDTO> getSearchResult(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "20") int limit,
                                             @RequestParam(value = "search", defaultValue = "") String search) {
        return itemService.search(search, page, limit).stream()
                .map(itemMapper::convertItemToListItemDTO).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ItemDTO getById(@PathVariable("id") long id) throws ItemNotFoundException {
        return itemMapper.convertItemToItemDTO(itemService.findById(id));
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> itemNotFound(ItemNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
