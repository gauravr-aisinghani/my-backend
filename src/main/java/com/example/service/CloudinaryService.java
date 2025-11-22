package com.example.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudinaryService {
    /**
     * Uploads multipart file to Cloudinary and returns the resulting info map.
     */
    Map uploadFile(MultipartFile file, String folder) throws IOException;

    /**
     * Utility: if you need to upload local files (for development/testing) by path.
     * Returns map response from Cloudinary.
     */
    Map uploadFileByPath(String localPath, String folder) throws IOException;
}
