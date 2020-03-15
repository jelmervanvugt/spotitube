package nl.han.ica.dea.dto;

public class LoginResponseDTO {

    private String user, token;

    public LoginResponseDTO() {
        //
    }

    public LoginResponseDTO(String user, String token) {

        this.user = user;
        this.token = token;
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
