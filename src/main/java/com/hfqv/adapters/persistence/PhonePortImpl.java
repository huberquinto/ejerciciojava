package com.hfqv.adapters.persistence;

import com.hfqv.adapters.persistence.repository.PhoneRepository;
import com.hfqv.adapters.persistence.repository.model.Phone;
import com.hfqv.app.domain.UserUsesCaseImpl;
import com.hfqv.app.domain.port.PhonePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhonePortImpl implements PhonePort {
    private static final Logger logger = LoggerFactory.getLogger(PhonePortImpl.class);
    @Autowired
    private PhoneRepository phoneRepository;


    @Override
    public Boolean numberIsAlreadyRegistered(String number) {

        List<Phone> phoneList = phoneRepository.findByNumber(number);
        logger.info(" numberIsAlreadyRegistered phoneLits: ",phoneList);
        logger.info(" numberIsAlreadyRegistered phoneList.size(): "+(phoneList!=null?phoneList.size():null));



        if(phoneList.isEmpty()){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
