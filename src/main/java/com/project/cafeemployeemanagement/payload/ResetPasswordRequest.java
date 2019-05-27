package com.project.cafeemployeemanagement.payload;

import javax.validation.constraints.NotBlank;

public class ResetPasswordRequest {
    @NotBlank
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
