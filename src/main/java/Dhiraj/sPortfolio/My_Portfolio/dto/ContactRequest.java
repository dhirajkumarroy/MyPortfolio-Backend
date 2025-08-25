package Dhiraj.sPortfolio.My_Portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactRequest {
    @NotBlank @Size(min = 2, max = 80)
    private String name;

    @NotBlank @Email @Size(max = 120)
    private String email;

    @NotBlank @Size(min = 5, max = 4000)
    private String message;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
