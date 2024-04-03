package com.hfqv.adapters.persistence;

import com.hfqv.adapters.persistence.repository.UserRepository;
import com.hfqv.adapters.persistence.repository.model.Phone;
import com.hfqv.adapters.persistence.repository.model.User;
import com.hfqv.app.domain.model.PhoneDataMessage;
import com.hfqv.app.domain.model.UserCreatedDataRp;
import com.hfqv.app.domain.model.UserDataMessage;
import com.hfqv.app.domain.port.UserRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userRepositoryPortImpl")
public class UserRepositoryPortImpl implements UserRepositoryPort {
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryPortImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Override
    public Optional<UserCreatedDataRp> save(UserDataMessage userDataMessage) {

        final User user = new User();
        user.setEmail(userDataMessage.getEmail());
        user.setName(userDataMessage.getName());
        user.setPassword(userDataMessage.getPassword());
        List<Phone> listPhones = userDataMessage.getPhones().stream().map(phoneDataMessage -> {
            Phone phone = new Phone();
            phone.setNumber(phoneDataMessage.getNumber());
            phone.setCityCode(phoneDataMessage.getCityCode());
            phone.setCountryCode(phoneDataMessage.getCountryCode());
            phone.setUser(user);
            return phone;
        }).collect(Collectors.toList());
        user.setPhones(listPhones);

        user.setCreated(new Date());
        user.setModified(user.getCreated());
        user.setLastLogin(user.getCreated());

        user.setToken(userDataMessage.getToken());
        user.setActive(Boolean.TRUE);

        userRepository.save(user);
        logger.info("Resultado de creacion USERID "+user.getUserId());
        logger.info("Resultado de creacion NAME: "+user.getName());
        if(user.getUserId()!=null){

            UserCreatedDataRp userCreatedRp = new UserCreatedDataRp();
            userCreatedRp.setId(user.getUserId().toString());
            userCreatedRp.setToken(user.getToken());
            userCreatedRp.setActive(user.getActive());
            userCreatedRp.setCreated(user.getCreated());
            userCreatedRp.setModified(user.getModified());
            userCreatedRp.setLastLogin(user.getLastLogin());

            return Optional.of(userCreatedRp);
        }
        return Optional.empty();

    }

    @Override
    public Boolean emailHasAlreadyRegistered(String email) {

        Optional<User>  userOp =  userRepository.findByEmail(email);
        if(userOp.isPresent()){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public List<UserDataMessage> listAll(){

       List<User> userList = userRepository.findAll();

       return userList.stream().map(user ->{

           UserDataMessage  userData = new UserDataMessage();
           userData.setId(user.getUserId().toString());
           userData.setName(user.getName());
           userData.setEmail(user.getEmail());
           userData.setPassword(user.getPassword());
           List<PhoneDataMessage> phonesDataList = user.getPhones().stream().map(phone -> {
               PhoneDataMessage  phoneDataMessage = new PhoneDataMessage();
               phoneDataMessage.setNumber(phone.getNumber());
               phoneDataMessage.setCityCode(phone.getCityCode());
               phoneDataMessage.setCountryCode(phone.getCountryCode());
               return phoneDataMessage;
           }).collect(Collectors.toList());

           logger.info("phonesDataList: ",phonesDataList);

           userData.setPhones(phonesDataList);
           userData.setToken(user.getToken());

           return userData;
       }).collect(Collectors.toList());

    }


}
