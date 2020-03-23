package nl.han.ica.dea.dto;

public class LoginResponseDTO {

    private String token, user;

    public LoginResponseDTO() {}

    public LoginResponseDTO(String user, String token) {

        this.token = token;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
