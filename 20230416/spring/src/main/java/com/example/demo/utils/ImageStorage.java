package com.example.demo.utils;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.hypersistence.tsid.TSID;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Component
public class ImageStorage {
    private final Cloudinary cloudinary;

    public ImageStorage(
            @Value("${cloudinary.url}") String cloudinaryUrl
    ) {
        cloudinary = new Cloudinary(cloudinaryUrl);
        cloudinary.config.secure = true;
    }

    public String save(byte[] bytes) {
        String id = TSID.Factory.getTsid().toString();

        Map options = ObjectUtils.asMap(
                "public_id", "test/" + id
        );

        try {
            Map result = cloudinary.uploader().upload(bytes, options);
            return result.get("secure_url").toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
