package ua.nulp.configharbor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthCredentialsRequest {
    private String userEmail;
    private String userPassword;
}
