package com.nico.store.store.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageService {

    @Value("${article.image.path}")
    private String imagePath;

    public String create(MultipartFile multipartFile) throws IOException {
        try {
            byte[] pictureBytes = multipartFile.getBytes();
            Path path = Paths.get(imagePath + multipartFile.getOriginalFilename());
            Files.write(path, pictureBytes);
        } catch (IOException ioException){
            throw ioException;
        }
        return multipartFile.getOriginalFilename();
    }

    public void delete(String pictureName) throws IOException {
        try {
            Files.delete(Paths.get(imagePath,pictureName));
        } catch (IOException ioException) {
            throw ioException;
        }
    }
}
