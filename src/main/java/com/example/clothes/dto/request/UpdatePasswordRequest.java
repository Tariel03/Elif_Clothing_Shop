package com.example.clothes.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {
    @JsonProperty("old_password")
    private String oldPassword;

    @JsonProperty("new_password")
    private String newPassword;

    @JsonProperty("confirm_password")
    private String confirmPassword;
}
