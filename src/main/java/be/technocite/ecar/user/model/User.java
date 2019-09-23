package be.technocite.ecar.user.model;

public class User {
    private String id;
    private String pseudo;
    private String password;
    private UserType userType;

    public User(String id, String pseudo, String password, UserType userType) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
