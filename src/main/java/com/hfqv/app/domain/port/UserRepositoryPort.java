package com.hfqv.app.domain.port;

import com.hfqv.app.domain.model.UserCreatedDataRp;
import com.hfqv.app.domain.model.UserDataMessage;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    Optional<UserCreatedDataRp> save(UserDataMessage userDataMessage);

    Boolean emailHasAlreadyRegistered(String email);
    List<UserDataMessage> listAll();

}
