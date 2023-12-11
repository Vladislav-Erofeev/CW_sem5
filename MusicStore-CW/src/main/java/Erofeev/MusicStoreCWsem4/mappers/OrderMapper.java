package Erofeev.MusicStoreCWsem4.mappers;

import Erofeev.MusicStoreCWsem4.dto.OrderDTO;
import Erofeev.MusicStoreCWsem4.dto.OrderListItemDTO;
import Erofeev.MusicStoreCWsem4.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ItemMapper.class})
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO convertOrderToOrderDTO(Order order);

    OrderListItemDTO convertOrderToOrderListItemDTO(Order order);
}
