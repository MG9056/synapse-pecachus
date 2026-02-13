package com.example.control.synapse.dto.request;


public class UserUpdateDto {

    private String name;
    private String email;
    private String password;
    private String role;
    private String gender;
    public UserUpdateDto() {}
    
    public UserUpdateDto(String name, String email, String password, String role, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    

}
