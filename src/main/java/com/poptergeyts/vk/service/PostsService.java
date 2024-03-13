package com.poptergeyts.vk.service;

import com.poptergeyts.vk.jsonObject.JsonPost;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PostsService {
    private final Map<Long, JsonPost> postsMap = new HashMap<>();
    private final RestTemplate restTemplate = new RestTemplate();

    public List<JsonPost> getAllPosts() {
        if (postsMap.size() != 100) {
            ResponseEntity<List<JsonPost>> response =
                    restTemplate.exchange("https://jsonplaceholder.typicode.com/posts",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<JsonPost>>() {});

            for (int i = 0; i < 100; i++) {
                if (!postsMap.containsKey((long) (i + 1))) {
                    postsMap.put((long) (i + 1), response.getBody().get(i));
                }
            }

            return response.getBody();
        } else {
            List<JsonPost> postsList = new ArrayList<>(100);
            for (int i = 0; i < 100; i++) {
                postsList.add(postsMap.get((long) i));
            }

            return postsList;
        }
    }

    public JsonPost getPost(Long postId) {
        if (postsMap.containsKey(postId)) {
            return postsMap.get(postId);
        } else {
            JsonPost jsonPost = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/" + postId.toString(),
                    JsonPost.class);
            assert jsonPost != null;
            postsMap.put(jsonPost.getId(), jsonPost);
            return jsonPost;
        }
    }
}
