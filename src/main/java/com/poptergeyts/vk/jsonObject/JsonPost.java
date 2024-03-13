package com.poptergeyts.vk.jsonObject;

import lombok.Data;

@Data
public class JsonPost {
    private Long id;
    private String title;
    private String body;
    private Long userId;
}
