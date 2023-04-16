package com.example.demo.controllers;

import java.io.FileInputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.utils.ImageStorage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImageController.class)
class ImageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageStorage imageStorage;

    @Test
    @DisplayName("POST /images - with file")
    void createWithFile() throws Exception {
        String filename = "src/test/resources/files/test.jpg";

        MockMultipartFile file = new MockMultipartFile(
                "image", "test.jpg", "image/jpeg",
                new FileInputStream(filename));

        mockMvc.perform(multipart("/images")
                        .file(file))
                .andExpect(status().isCreated());

        verify(imageStorage).save(any());
    }

    @Test
    @DisplayName("POST /images - without file")
    void createWithoutFile() throws Exception {
        mockMvc.perform(multipart("/images"))
                .andExpect(status().isCreated());

        verify(imageStorage, never()).save(any());
    }
}
