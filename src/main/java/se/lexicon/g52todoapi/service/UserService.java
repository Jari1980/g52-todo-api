package se.lexicon.g52todoapi.service;

import se.lexicon.g52todoapi.domain.dto.UserDTOForm;
import se.lexicon.g52todoapi.domain.dto.UserDTOView;

public interface UserService {

    UserDTOView register(UserDTOForm userDTOForm);

    boolean authorizeUser(String email, String password);

    UserDTOView getByEmail(String email);

    void disableByEmail(String email);

    void enableByEmail(String email);

}
