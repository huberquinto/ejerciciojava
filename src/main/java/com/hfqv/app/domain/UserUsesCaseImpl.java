package com.hfqv.app.domain;

import com.hfqv.app.domain.model.PhoneDataMessage;
import com.hfqv.app.domain.model.ResponseMessage;
import com.hfqv.app.domain.model.UserCreatedDataRp;
import com.hfqv.app.domain.model.UserDataMessage;
import com.hfqv.app.domain.port.PhonePort;
import com.hfqv.app.domain.port.UserPort;
import com.hfqv.app.domain.port.UserRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("userUsesCaseImpl")
public class UserUsesCaseImpl implements UserPort {

    private static final Logger logger = LoggerFactory.getLogger(UserUsesCaseImpl.class);

    @Value("${app.user.password.reg-exp}")
    private String passwordRegExp;

    @Value("${app.user.email.reg-exp}")
    private String emailRegExp;

    @Autowired
    private UserRepositoryPort userRepositoryPort;
    @Autowired
    private PhonePort phonePort;

    @Override
    public ResponseMessage create(UserDataMessage userDataMessage) {

        ResponseMessage respMsg;

        try{
            logger.info("Uses Case Crear usuario Inicio ");
            //Realizar validaciones
             respMsg = executeValidations(userDataMessage);

            if(respMsg.getIdMessage()==1){
                Optional<UserCreatedDataRp> userOp = userRepositoryPort.save(userDataMessage);
                if(userOp.isPresent()){
                    respMsg.setIdMessage(1);
                    respMsg.setBody(userOp.get());
                }else{
                    respMsg.setIdMessage(-1);
                    respMsg.setMessage("No se pudo registrar el usuario");
                }
            }

        }catch(Exception ex){
            logger.error("Uses Case Error al crear usuario: ",ex);
            throw ex;
        }

        logger.info("Uses Case Crear usuario Fin ");

        return respMsg;
    }


    private ResponseMessage executeValidations(UserDataMessage userDataMessage){

        ResponseMessage responseMsg = validateEmailFormat(userDataMessage.getEmail());
        if(responseMsg.getIdMessage()==-1)
            return responseMsg;

        responseMsg = validatePasswordFormat(userDataMessage.getPassword());
        if(responseMsg.getIdMessage()==-1)
            return responseMsg;

         responseMsg = validateIfEmailIsRegistered(userDataMessage);
        if(responseMsg.getIdMessage()==-1)
            return responseMsg;

        return responseMsg;
    }

    private ResponseMessage validateIfEmailIsRegistered(UserDataMessage userDataMessage){
        ResponseMessage responseMessage = new ResponseMessage();

        Boolean hasAlreadyRegistered = userRepositoryPort.emailHasAlreadyRegistered(userDataMessage.getEmail());
        if(!hasAlreadyRegistered){
            responseMessage.setIdMessage(1);
            logger.info("Aun no esta registrado el email!");
        }else{
            responseMessage.setIdMessage(-1);
            responseMessage.setMessage("El Email ya esta registrado!");
            logger.info("El email ya esta registrado : "+userDataMessage.getEmail());
        }

        return responseMessage;

    }

    private ResponseMessage validateEmailFormat(String email){
        ResponseMessage responseMessage = new ResponseMessage();
        if (email.matches(emailRegExp)){

            responseMessage.setIdMessage(1);
            logger.info("El email tiene el formato correcto!");

        }else{
            responseMessage.setIdMessage(-1);
            responseMessage.setMessage("El email No tiene el formato correcto!");
            logger.info("El email debe tener el formato:"+emailRegExp);
        }

        return responseMessage;
    }

    private ResponseMessage validatePasswordFormat(String password){
        ResponseMessage responseMessage = new ResponseMessage();
        if (password.matches(passwordRegExp)){
            responseMessage.setIdMessage(1);
            logger.info("El password tiene el formato correcto!");

        }else{
            responseMessage.setIdMessage(-1);
            responseMessage.setMessage("El password No tiene el formato correcto!");
            logger.info("El password debe tener el formato:"+passwordRegExp);
        }

        return responseMessage;
    }


    //No era parte del requerimiento
    private Boolean validateIfAltLeastOnePhoneExists(UserDataMessage userDataMessage){

        Boolean wasRegistered= Boolean.FALSE;
        List<PhoneDataMessage> phoneDataMessageList =  userDataMessage.getPhones();
        for(PhoneDataMessage phoneData: phoneDataMessageList){
            wasRegistered = phonePort.numberIsAlreadyRegistered(phoneData.getNumber());
            if(wasRegistered){
                break;
            }
        }

        return wasRegistered;

    }

    @Override
    public List<UserDataMessage> list() {

        logger.info("Uses case listado de usuarios Inicio");
        //TODO se puede mejorar aplicando filtros
        List<UserDataMessage> userDataMessageList = userRepositoryPort.listAll();
        logger.info("Uses case listado de usuarios Fin");
        return userDataMessageList;
    }

    @Override
    public void delete(String userId) {
        // TODO
    }

    @Override
    public void update(UserDataMessage userDataMessage) {
        // TODO
    }
}
