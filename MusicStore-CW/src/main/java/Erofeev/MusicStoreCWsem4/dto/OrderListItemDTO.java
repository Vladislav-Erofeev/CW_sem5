package Erofeev.MusicStoreCWsem4.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderListItemDTO {
    private long id;

    private String address;

    private String orderDate;

    private String status;

    private float price;
}
