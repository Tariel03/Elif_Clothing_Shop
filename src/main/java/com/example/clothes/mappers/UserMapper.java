package com.example.clothes.mappers;

import com.example.clothes.dto.response.UserInfoResponse;
import com.example.clothes.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserInfoResponse userToUserInfoResponse(User user);
}