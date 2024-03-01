package ru.example.lections.sender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.example.lections.sender.domain.Image;
import ru.example.lections.sender.domain.Operation;
import ru.example.lections.sender.dto.ImageDto;
import ru.example.lections.sender.dto.OperationDto;
import ru.example.lections.sender.exception.ImageNotFoundException;
import ru.example.lections.sender.mapper.ImagesMapper;
import ru.example.lections.sender.repository.ImageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository repository;
    private final ImagesMapper mapper;

    private final OperationService operationService;

    private final MinioService service;

    public boolean existsAll(List<Integer> imagesId) {
        return repository.existsImagesByIdIn(imagesId);
    }

    public List<Image> getAllImages(List<Integer> imagesId) {
        return repository.findAllByIdIn(imagesId);
    }

    @Cacheable(value = "ImageService::getImageMeta", key = "#id")
    public ImageDto getImageMeta(int id) {
        Optional<Image> imageOptional = repository.findImageById(id);
        if (imageOptional.isEmpty()) {
            throw new ImageNotFoundException(id);
        }

        ImageDto image = mapper.imageToImageDto(imageOptional.get());

        operationService.logOperation(
                new OperationDto(
                        String.format("Read image metadata: %s", image),
                        LocalDateTime.now(),
                        Operation.OperationType.WRITE
                )
        );

        return image;
    }

    public byte[] downloadImage(String link) throws Exception {
        if (!repository.existsImagesByLink(link)) {
            throw new ImageNotFoundException(link);
        }

        return service.downloadImage(link);
    }

    @Cacheable(value = "ImageService::getImageMeta", key = "#file.originalFilename")
    public ImageDto uploadImage(MultipartFile file) throws Exception {
        ImageDto image = service.uploadImage(file);
        repository.save(mapper.imageDtoToImage(image));

        operationService.logOperation(
                new OperationDto(
                        String.format("Upload image: %s", image),
                        LocalDateTime.now(),
                        Operation.OperationType.WRITE
                )
        );

        return image;
    }
}
