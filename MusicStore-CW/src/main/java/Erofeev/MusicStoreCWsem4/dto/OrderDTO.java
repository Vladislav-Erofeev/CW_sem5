package Erofeev.MusicStoreCWsem4.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private long id;
    private int personId;

    private String address;

    private String orderDate;

    private String status;

    private float price;

    private List<ItemDTO> items;
}
