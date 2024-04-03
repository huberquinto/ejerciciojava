package com.hfqv.app.domain.port;

import com.hfqv.app.domain.model.UserCreatedDataRp;
import com.hfqv.app.domain.model.UserDataMessage;
import com.hfqv.app.domain.model.ResponseMessage;

import java.util.List;
import java.util.Optional;

public interface UserPort {

    ResponseMessage create(UserDataMessage userDataMessage);
    List<UserDataMessage> list();
    void delete(String userId);
    void update(UserDataMessage userDataMessage);
}
