package Erofeev.MusicStoreCWsem4.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    private String body;

    private int count;

    private double price;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ItemCategory category;

    @OneToMany(mappedBy = "item")
    private List<ItemImage> images;

    @ManyToMany
    @JoinTable(name = "ordered_item",
    joinColumns = @JoinColumn(name = "item_id"),
    inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;

    public void addImage(ItemImage itemImage) {
        if (images == null)
            images = new LinkedList<>();
        images.add(itemImage);
    }

    public void addOrder(Order order) {
        if (order == null)
            orders = new LinkedList<>();
        orders.add(order);
    }
}
