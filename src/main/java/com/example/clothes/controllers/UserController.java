package com.example.clothes.controllers;

import com.example.clothes.dto.request.UpdatePasswordRequest;
import com.example.clothes.dto.request.UpdateUserInfoRequest;
import com.example.clothes.dto.response.UserInfoResponse;
import com.example.clothes.entities.User;
import com.example.clothes.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordRequest request, Authentication authentication)  {

        User user=(User) authentication.getPrincipal();

        if(!request.getNewPassword().equals(request.getConfirmPassword())){
            return ResponseEntity.badRequest().body("Passwords don't match");
        }

        try {
            userService.updatePassword(user, request);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body("Old password was entered incorrectly");
        }

        return ResponseEntity.ok("Password changed");
    }

    @PutMapping("/update-user-info")
    public ResponseEntity<String> updateUserInfo(@RequestBody UpdateUserInfoRequest request, Authentication authentication) {

        User user=(User) authentication.getPrincipal();
        userService.updateUserInfo(user, request);

        return ResponseEntity.ok("User's information has been updated");
    }

    @GetMapping("/get-user-info")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        User user=(User) authentication.getPrincipal();

        return ResponseEntity.ok(userService.getUserInfo(user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(Authentication authentication) {
        User user=(User) authentication.getPrincipal();

        userService.deleteUser(user);

        return ResponseEntity.ok(String.format("User with id '%s' has been deleted successfully", user.getId()));
    }


}
