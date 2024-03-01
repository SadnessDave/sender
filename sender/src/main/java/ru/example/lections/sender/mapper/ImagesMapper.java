package ru.example.lections.sender.mapper;

import org.mapstruct.Mapper;
import ru.example.lections.sender.domain.Image;
import ru.example.lections.sender.dto.ImageDto;

import java.util.List;

@Mapper
public interface ImagesMapper {

    List<ImageDto> imagesToImagesDto(List<Image> images);

    ImageDto imageToImageDto(Image image);

    Image imageDtoToImage(ImageDto imageDto);
}
