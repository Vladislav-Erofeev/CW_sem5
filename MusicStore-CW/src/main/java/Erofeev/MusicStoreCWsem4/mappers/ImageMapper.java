package Erofeev.MusicStoreCWsem4.mappers;

import Erofeev.MusicStoreCWsem4.dto.ImageDTO;
import Erofeev.MusicStoreCWsem4.entities.ItemImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDTO convertImageToImageDTO(ItemImage itemImage);
}
