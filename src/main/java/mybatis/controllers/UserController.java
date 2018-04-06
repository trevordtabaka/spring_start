package mybatis.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import mybatis.exception.AuthenticationException;
import mybatis.model.User;
import mybatis.services.User.SecurityService;
import mybatis.services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    //RequestMapping maps URLs to methods
    @RequestMapping("/age")
    public ArrayList<User> getUsers(@RequestParam(value="age") int age) {
        return userService.getUserByAge(age);
    }
    //Get
    @RequestMapping("/")
    public ArrayList<User> getUsers(@RequestParam("apiKey") String apiKey ) throws AuthenticationException {
        if(securityService.authenticate(apiKey)){
            return userService.getAllUsers();
        }
        throw new AuthenticationException("API key invalid");


    }

    @RequestMapping("/{id}")
    public User getById(@RequestParam("apiKey") String apiKey,
            @PathVariable(value="id")int id) throws AuthenticationException{
        if(securityService.authenticate(apiKey)){
            return userService.getById(id);
        }

        throw new AuthenticationException("API key is Invalid");
    }

    @RequestMapping("/manual")
    public ArrayList<User> getUsersManually() {
        //write the necessary code to get all users and return
        //them in json to the browser without using mybatis
        return userService.getAllUsersManually();
    }

    //Create
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public User addNew(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.addNew(user);
    }

    //Update
    @RequestMapping(method = RequestMethod.PATCH, value = "/")
    public User updateById(@RequestBody User user) {
        return userService.updateById(user);
    }

    //Delete
    @RequestMapping(method= RequestMethod.DELETE, value="/")
    public User deleteById(@RequestParam(value="id")int id){
        return userService.deleteById(id);
    }


}
