package com.hfqv.adapters.ctrl;


import com.hfqv.app.domain.model.TokenRp;
import com.hfqv.app.domain.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JWTController {

    private static final Logger logger = LoggerFactory.getLogger(JWTController.class);

    @Autowired
    private JWTUtil jWTUtil;

    @GetMapping(value = "/token", produces = "application/json")
    public ResponseEntity<Object> getToken(@RequestHeader("UserLogin") String userLogin){
        TokenRp tokenRp = new TokenRp();
        try{
            String token =  jWTUtil.generateToken(userLogin);
            logger.info("TOKEN GENERADO: "+token);
            tokenRp.setTokenCode(token);
            return ResponseEntity.ok().body(tokenRp);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
