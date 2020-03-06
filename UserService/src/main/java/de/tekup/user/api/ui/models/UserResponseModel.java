package de.tekup.user.api.ui.models;
import java.util.List;

import lombok.Data;

@Data
public class UserResponseModel {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AlbumResponseModel> albums;
    

    
	
	
}