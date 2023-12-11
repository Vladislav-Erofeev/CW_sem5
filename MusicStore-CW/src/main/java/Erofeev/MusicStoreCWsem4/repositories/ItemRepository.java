package Erofeev.MusicStoreCWsem4.repositories;

import Erofeev.MusicStoreCWsem4.entities.Item;
import Erofeev.MusicStoreCWsem4.entities.ItemCategory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategory(ItemCategory category, PageRequest pageRequest);

    List<Item> findByTitleContainingIgnoreCase(String title, PageRequest pageRequest);

    List<Item> findAllByIdIn(List<Long> id);
}
