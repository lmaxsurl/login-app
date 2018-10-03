package logunov.maxim.domain.entity;

public class UserSignUp implements DomainModel {

    private String login;
    private String password;

    public UserSignUp(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
