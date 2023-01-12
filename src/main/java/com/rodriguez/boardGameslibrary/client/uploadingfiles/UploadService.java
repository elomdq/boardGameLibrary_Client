package com.rodriguez.boardGameslibrary.client.uploadingfiles;

import com.rodriguez.boardGameslibrary.client.config.Location;
import com.rodriguez.boardGameslibrary.client.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UploadService {

    public static Image consumeImageFile(MultipartFile file, Long id){

        String fileName = file.getOriginalFilename();
        String uploadDir = "D:/Java/Documentos/temp/"+ id;
        String uploadPath = uploadDir + "/" + fileName;

        Image img = new Image();

        File uploadFile = new File(uploadPath);

        try{
            if(!Files.exists(Paths.get(uploadDir)))
                Files.createDirectory(Paths.get(uploadDir));

            if(!uploadFile.exists()){
                uploadFile.createNewFile();
                OutputStream os = new FileOutputStream(uploadFile);
                os.write(file.getBytes());
                os.close();
            }

        }catch(IOException e){
            throw new RuntimeException(e.getMessage());
        }

        img.setUrl(uploadDir);
        img.setName(fileName);

        return img;
    }

}
