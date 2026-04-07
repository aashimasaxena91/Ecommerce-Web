package com.ecommerce.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FIleServiceImpl implements FileService{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //File names of current / original file
        String originalFileName = file.getOriginalFilename();

        //Generate a unique file name
        String randomId = UUID.randomUUID().toString();

        //the below line is doing: eg: we have originalFileName = mat.jpg, and randomId = 1234
        //than new fileName = 1234.jpg
        String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
        String filePath = path + File.separator + fileName;

        //check if path exist and create
        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdirs();
        }

        //upload to server
        Files.copy(file.getInputStream(), Paths.get(filePath));

        //returning file name
        return fileName;
    }
}
