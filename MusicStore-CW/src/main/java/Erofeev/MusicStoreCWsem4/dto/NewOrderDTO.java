package Erofeev.MusicStoreCWsem4.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NewOrderDTO {
    private String address;
    private List<Long> items;
}
