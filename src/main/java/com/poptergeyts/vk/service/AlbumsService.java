package com.poptergeyts.vk.service;

import com.poptergeyts.vk.jsonObject.JsonPost;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlbumsService {
    private final Map<Long, JsonPost> albumsMap = new HashMap<>();
    private final RestTemplate restTemplate = new RestTemplate();

    public List<JsonPost> getAllAlbums() {
        if (albumsMap.size() != 100) {
            ResponseEntity<List<JsonPost>> response =
                    restTemplate.exchange("https://jsonplaceholder.typicode.com/albums",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<JsonPost>>() {});

            for (int i = 0; i < 100; i++) {
                if (!albumsMap.containsKey((long) (i + 1))) {
                    albumsMap.put((long) (i + 1), response.getBody().get(i));
                }
            }

            return response.getBody();
        } else {
            List<JsonPost> postsList = new ArrayList<>(100);
            for (int i = 0; i < 100; i++) {
                postsList.add(albumsMap.get((long) i));
            }

            return postsList;
        }
    }

    public JsonPost getAlbum(Long albumId) {
        if (albumsMap.containsKey(albumId)) {
            return albumsMap.get(albumId);
        } else {
            JsonPost jsonPost =
                    restTemplate.getForObject("https://jsonplaceholder.typicode.com/albums/" + albumId.toString(),
                    JsonPost.class);
            assert jsonPost != null;
            albumsMap.put(jsonPost.getId(), jsonPost);
            return jsonPost;
        }
    }
}
