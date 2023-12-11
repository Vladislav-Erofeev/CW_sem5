package Erofeev.MusicStoreCWsem4.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewItemDTO {
    private String title;
    private String description;
    private String body;
    private int count;
    private double price;
    private String category;
}
