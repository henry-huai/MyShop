package dev.huai.controllers;

import dev.huai.models.Amount;
import dev.huai.models.User;
import dev.huai.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@CrossOrigin
public class UserController {

    // token :  userId:MANAGER

        /*
        GET     /user - get all dog records         localhost:8080/dogs
                optional request param: breed       localhost:8080/dogs?breed=pitbull
                .... etc
        GET     /user/{id} - get user information by id
        POST    /user - validate user credential, return boolean
        PUT     /user/{id} - update an existing user password
     */

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity<?> getUserByCredential(@RequestBody User user){
        User returnedUser = userService.getUserByCredential(user.getUserId(), user.getPassword());
        if(returnedUser ==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else{
            return new ResponseEntity<>(returnedUser, HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/alluser")
    @ResponseBody
    public ResponseEntity<?> getAllUserByManager(){
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authToken = request.getHeader("Authorization");
        String[] str = authToken.split(":");
        System.out.println("token is "+ authToken);
        //str[0] is the userId
        //str[1] is the MANAGER/CUSTOMER
        //get user information by userId
        // compare user.isManager with str[1]
        //if...
        User user = userService.getUserById(Integer.parseInt(str[0]));
        if(user.isManager()==true && str[1].equals("MANAGER")){
            return new ResponseEntity<>(userService.getUserByManager(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/newuser")
    @ResponseBody
    public ResponseEntity<?> signUpNewCustomer(@RequestBody User user){
        if(userService.addUser(user)){
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/updatebalance")
    @ResponseBody
    public ResponseEntity<?> updateBalanceOnUser(@RequestBody Amount amount){
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authToken = request.getHeader("Authorization");
        String[] str = authToken.split(":");
        if(amount.getAmount() > 0){
            // this is a deposit update
            if(userService.updateBalanceDeposit(Integer.parseInt(str[0]), new BigDecimal(amount.getAmount())))
                return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
            else
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }else{
            // this is a cash_out update
            if(userService.updateBalanceCashOut(Integer.parseInt(str[0]), new BigDecimal(-amount.getAmount())))
                return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
            else
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
    }



}