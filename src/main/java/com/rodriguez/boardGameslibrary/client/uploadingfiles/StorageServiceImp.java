package com.rodriguez.boardGameslibrary.client.uploadingfiles;

import com.rodriguez.boardGameslibrary.client.models.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

public class StorageServiceImp implements StorageService{
    @Override
    public void init() {

    }

    @Override
    public void store(MultipartFile file) {

    }

    @Override
    public Stream<Image> loadAll() {

        return null;
    }

    @Override
    public Image load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
