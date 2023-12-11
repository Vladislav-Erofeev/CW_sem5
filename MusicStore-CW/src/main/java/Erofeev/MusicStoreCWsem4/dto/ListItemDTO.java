package Erofeev.MusicStoreCWsem4.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListItemDTO {
    private int id;
    private String title;
    private int count;
    private double price;
    private String category;
    private List<ImageDTO> images;
}
