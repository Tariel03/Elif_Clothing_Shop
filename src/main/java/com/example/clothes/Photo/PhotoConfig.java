package com.example.clothes.Photo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Data
public class PhotoConfig {
//    @Value(value = "${home_dir}")
//    private String uploadDir = "/home/iskender/Tariel/selim_team3";
    private String uploadDir = "static/images";
    private Path path = Paths.get(uploadDir);

    public void savePhoto(MultipartFile file){
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ignored) {

            }
        }

        try {
            byte[] bytes = file.getBytes();
            Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
            Files.write(filePath, bytes);
        } catch (IOException e) {
            throw new RuntimeException("Can not upload a photo!");
        }

    }


}
