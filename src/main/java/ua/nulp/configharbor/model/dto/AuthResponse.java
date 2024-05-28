package ua.nulp.configharbor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String userEmail;
    private String userToken;
    private String message;
}
