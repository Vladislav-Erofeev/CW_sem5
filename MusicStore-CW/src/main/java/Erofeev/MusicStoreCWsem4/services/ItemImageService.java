package Erofeev.MusicStoreCWsem4.services;

import Erofeev.MusicStoreCWsem4.entities.Item;
import Erofeev.MusicStoreCWsem4.entities.ItemImage;
import Erofeev.MusicStoreCWsem4.errors.ItemNotFoundException;
import Erofeev.MusicStoreCWsem4.repositories.ItemImageRepository;
import Erofeev.MusicStoreCWsem4.repositories.ItemRepository;
import Erofeev.MusicStoreCWsem4.utils.ImageNameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemImageService {
    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;
    private final ImageNameService nameService;
    @Value("${upload-directory}")
    private String UPLOAD_DIRECTORY;

    @Transactional
    public void save(long itemId, MultipartFile file) throws IOException, ItemNotFoundException {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isEmpty())
            throw new ItemNotFoundException("Item not found");
        Item item = optionalItem.get();

        String imageName = nameService.generate(file.getContentType());
        Path filenameAndPath = Paths.get(UPLOAD_DIRECTORY + "/items", imageName);
        Files.write(filenameAndPath, file.getBytes());

        ItemImage itemImage = new ItemImage();
        itemImage.setItem(item);
        itemImage.setUrl("/items/" + imageName);

        itemImageRepository.save(itemImage);
    }
}
