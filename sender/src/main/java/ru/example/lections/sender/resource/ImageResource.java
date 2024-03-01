package ru.example.lections.sender.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.example.lections.sender.dto.ImageDto;
import ru.example.lections.sender.service.ImageService;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageResource {

    private final ImageService service;

    @PostMapping("/load")
    public ImageDto loadImage(MultipartFile file) throws Exception {
        return service.uploadImage(file);
    }

    @GetMapping(value = "/{link}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable String link) throws Exception {
        return service.downloadImage(link);
    }

    @GetMapping("/{id}/meta")
    public ImageDto getMeta(@PathVariable int id) {
        return service.getImageMeta(id);
    }
}
