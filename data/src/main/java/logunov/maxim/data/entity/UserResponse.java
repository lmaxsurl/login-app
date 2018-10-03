package logunov.maxim.data.entity;

import com.google.gson.annotations.SerializedName;

public class UserResponse implements DataModel {

    @SerializedName("login")
    private String login;

    @SerializedName("token")
    private String token;

    @SerializedName("userId")
    private int userId;


    public String getLogin() {
        return login;
    }

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }
}
