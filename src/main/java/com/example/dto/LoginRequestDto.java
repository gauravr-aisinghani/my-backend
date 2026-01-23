//for transporter and driver login 
package com.example.dto;

public class LoginRequestDto {

    private String mobile;
    private String role; // DRIVER | TRANSPORTER

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

