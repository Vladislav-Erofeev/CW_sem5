package Erofeev.MusicStoreCWsem4.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "order_item")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int personId;

    private String address;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private float price;

    @ManyToMany(mappedBy = "orders")
    private List<Item> items;

    public void addItem(Item item) {
        if (items == null)
            items = new LinkedList<>();
        items.add(item);
    }
}
