package com.example.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public Map uploadFile(MultipartFile file, String folder) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        // convert MultipartFile to temp File
        File convFile = convertMultipartFileToFile(file);
        try {
            Map uploadResult = cloudinary.uploader().upload(convFile,
                    ObjectUtils.asMap("folder", folder));
            return uploadResult;
        } finally {
            // delete temp file
            convFile.delete();
        }
    }

    @Override
    public Map uploadFileByPath(String localPath, String folder) throws IOException {
        File file = new File(localPath);
        if (!file.exists()) {
            throw new IllegalArgumentException("Local file not found: " + localPath);
        }
        Map uploadResult = cloudinary.uploader().upload(file,
                ObjectUtils.asMap("folder", folder));
        return uploadResult;
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = File.createTempFile("upload-", file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }
}
