package dev.kodewana.movies.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Workaround for tmdb image api CORS issue.
 */
@Slf4j
@RestController
@RequestMapping("api/v1/content")
public class ContentController {

    @GetMapping(value = "/t/p/{imageSize}/{imageName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})

    public ResponseEntity<byte[]> getImage(@PathVariable String imageSize, @PathVariable String imageName) {
        String imageUrl = "https://image.tmdb.org/t/p/%s/%s".formatted(imageSize,imageName);
        try{
            URL url = URI.create(imageUrl).toURL();
            byte[] imageBytes = IOUtils.toByteArray(url);
            return new ResponseEntity<byte[]>(imageBytes, HttpStatus.FOUND);
        } catch (IOException ie) {
            log.error("Failed to fetch image content from target server", ie);
            return null;
        }


    }
}
