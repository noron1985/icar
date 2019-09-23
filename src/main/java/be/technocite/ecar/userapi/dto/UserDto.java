package be.technocite.ecar.userapi.dto;

import be.technocite.ecar.user.model.UserType;

public class UserDto {
    private String id;
    private String pseudo;
    private UserType userType;

    public UserDto(String id, String pseudo, UserType userType) {
        this.id = id;
        this.pseudo = pseudo;
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
}
