package Erofeev.MusicStoreCWsem4.repositories;

import Erofeev.MusicStoreCWsem4.entities.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByPersonId(int ownerId, Sort sort);
}
