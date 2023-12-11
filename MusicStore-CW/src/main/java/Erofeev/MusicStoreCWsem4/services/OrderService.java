package Erofeev.MusicStoreCWsem4.services;

import Erofeev.MusicStoreCWsem4.entities.Item;
import Erofeev.MusicStoreCWsem4.entities.Order;
import Erofeev.MusicStoreCWsem4.entities.OrderStatus;
import Erofeev.MusicStoreCWsem4.errors.PersonNotFoundException;
import Erofeev.MusicStoreCWsem4.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemService itemService;

    public List<Order> findAll(int personId) {
        return orderRepository.findAllByPersonId(personId, Sort.by("id").descending());
    }

    public List<Order> findAll(int page, int limit) {
        return orderRepository.findAll(PageRequest.of(page, limit)).getContent();
    }

    public Order findById(long id) {
        return orderRepository.findById(id).get();
    }

    @Transactional
    public void createNewOrder(int personId, String address, List<Long> items) throws PersonNotFoundException {

        Order order = new Order();
        order.setPersonId(personId);
        order.setItems(itemService.getItemByIdIn(items));
        order.setAddress(address);
        float sum = 0;
        for (Item x : order.getItems())
            sum += x.getPrice();
        order.setPrice(sum);
        order.setStatus(OrderStatus.CREATED);
        order.setOrderDate(new Date());

        orderRepository.save(order);
    }
}
