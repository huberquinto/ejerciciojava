package com.hfqv.adapters.ctrl;

import com.hfqv.adapters.ctrl.model.CreateUserData;
import com.hfqv.app.domain.model.PhoneDataMessage;
import com.hfqv.app.domain.model.ResponseMessage;
import com.hfqv.app.domain.model.UserDataMessage;
import com.hfqv.app.domain.port.UserPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hfqv.app.domain.util.JWTUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
    @Autowired
    private UserPort userPort;

    @Autowired
    private JWTUtil jWTUtil;

    @PostMapping(value = "/create", consumes = "application/json",produces ="application/json")
    public ResponseEntity<Object> createUser(@RequestHeader("Authorization") String authorization,
                                             @RequestHeader("UserLogin") String userLogin,
                                             @RequestBody CreateUserData createUserData){

        ResponseMessage responseMessage = new ResponseMessage();
        try{

             responseMessage = validateToken(authorization,userLogin);

            if(responseMessage.getIdMessage()==-1){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
            }

            UserDataMessage userDataMessage = buildCreateUserMessage(createUserData,authorization);
            responseMessage  = userPort.create(userDataMessage);

            return  responseMessage.getIdMessage()==1 ? ResponseEntity.ok(responseMessage.getBody()) :
                    ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);

        }catch(Exception ex){
            logger.error("Error al crear usuario: ",ex);
            responseMessage.setIdMessage(-1);
            responseMessage.setMessage("Error al crear el usuario");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        }

    }

    @GetMapping(value="/list", produces = "application/json")
    public ResponseEntity<Object> listUsers(
            @RequestHeader("UserLogin") String userLogin,
            @RequestHeader("Authorization") String authorization){

        ResponseMessage responseMessage = new ResponseMessage();
        try{
            responseMessage = validateToken(authorization,userLogin);

            if(responseMessage.getIdMessage()==-1){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
            }
            List<UserDataMessage>  userDataMessageList = userPort.list();
            return ResponseEntity.ok(userDataMessageList);

        }catch(Exception ex){
            logger.error("Error al listar los usuarios: ",ex);
            responseMessage.setIdMessage(-1);
            responseMessage.setMessage("Error al listar usuarios");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
    }

    private UserDataMessage buildCreateUserMessage(CreateUserData createUserData, String authorization){

        UserDataMessage userData = new UserDataMessage();
        userData.setEmail(createUserData.getEmail());
        userData.setName(createUserData.getName());
        userData.setPassword(createUserData.getPassword());

        List<PhoneDataMessage> listPhones = createUserData.getPhones().stream().map(phone ->{
            PhoneDataMessage phoneData = new PhoneDataMessage();
            phoneData.setNumber(phone.getNumber());
            phoneData.setCityCode(phone.getCityCode());
            phoneData.setCountryCode(phone.getCountryCode());
            return phoneData;
        }).collect(Collectors.toList());
        userData.setPhones(listPhones);
        userData.setToken(authorization);

        return userData;

    }


    //TODO Hacer la validacion en otro componente puede ser en un filter
    private ResponseMessage validateToken(String authorization,String userLogin){
        logger.info("Validando toke jwt para: "+userLogin);
        ResponseMessage responseMsg = new ResponseMessage();
        try{
            responseMsg.setIdMessage(1);
            Boolean isValid = jWTUtil.validateToken(authorization,userLogin);
            if(!isValid){
                responseMsg.setIdMessage(-1);
                responseMsg.setMessage("El token no es valido");
            }
        }catch(Exception ex){
            logger.error("Error al validar el token",ex);
            responseMsg.setIdMessage(-1);
            responseMsg.setMessage("Fallo la validacion del token: "+ex.getMessage());
        }

        return responseMsg;
    }

}