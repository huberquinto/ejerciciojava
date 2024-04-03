package com.hfqv.adapters.ctrl;


import com.hfqv.app.domain.model.TokenRp;
import com.hfqv.app.domain.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JWTController {


    @Autowired
    private JWTUtil jWTUtil;

    @PostMapping(value = "/token",produces = "application/json")
    public ResponseEntity<Object> getToken(@RequestHeader("UserLogin") String userLogin){
        TokenRp tokenRp = new TokenRp();
        try{
            String token =  jWTUtil.generateToken(userLogin);
            tokenRp.setTokenCode(token);
            return ResponseEntity.ok().body(tokenRp);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
