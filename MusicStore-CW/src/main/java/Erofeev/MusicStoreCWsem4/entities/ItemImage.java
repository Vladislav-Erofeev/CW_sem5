package Erofeev.MusicStoreCWsem4.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ItemImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
}
