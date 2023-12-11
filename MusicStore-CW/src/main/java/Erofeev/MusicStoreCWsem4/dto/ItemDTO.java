package Erofeev.MusicStoreCWsem4.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@NoArgsConstructor
@Getter
public class ItemDTO {
    private int id;
    private String title;
    private String description;
    private String body;
    private int count;
    private double price;
    private String category;
    private List<ImageDTO> images;
}
