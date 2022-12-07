package com.rodriguez.boardGameslibrary.client.uploadingfiles;

import com.rodriguez.boardGameslibrary.client.models.Image;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public interface StorageService {

    void init();
    void store(MultipartFile file);
    Stream<Image> loadAll();
    Image load(String filename);
    Resource loadAsResource(String filename);
    void deleteAll();

}
