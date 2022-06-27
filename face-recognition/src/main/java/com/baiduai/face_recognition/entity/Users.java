package com.baiduai.face_recognition.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userPhoto;
}
