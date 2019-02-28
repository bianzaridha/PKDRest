/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth.micro.controller;

import auth.micro.model.User;
import auth.micro.proxy.LoginProxy;
import auth.micro.proxy.UserProxy;
import auth.micro.service.DesaService;
import auth.micro.service.RoleService;
import auth.micro.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bianza
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="/user")
@Api(value = "DesaController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = User.class)})
public class UserController {
    
    UserService us = new UserService();
    DesaService ds = new DesaService();
    RoleService rs = new RoleService();
   
    
    
    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public User insert(@RequestBody UserProxy up){
        User usr = new User(ds.findById(up.getKodepos()).get(0),
                            rs.findById(up.getId_role()).get(0),
                            up.getUsername(),
                            up.getPassword(),
                            up.getToken());
        us.save(usr);
        return usr;
    }
    
    @RequestMapping(path = "/username/{id}",method = RequestMethod.GET)
    public List<User> getById(@PathVariable("id") String id){
        return us.findByUser(id);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll(){
        return us.getAll();
    }
    
    @RequestMapping(path = "/desa/{id}",method = RequestMethod.GET)
    public List<User> getByDesa(@PathVariable("id") String id){
        return us.getAllByDesa(id);
    }
    
    @RequestMapping(path = "/role/{id}",method = RequestMethod.GET)
    public List<User> getByRole(@PathVariable("id") Integer id){
        return us.getAllByRole(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public User Login(@RequestBody LoginProxy up ){
        
    return us.Login(up);
    
    }
    
    @RequestMapping(path = "/logout",method = RequestMethod.PUT)
    public String Logout(@RequestBody User user){
        us.logout(user);
        
        return "logout";
    
    }
    
    @RequestMapping(path = "/{token}", method = RequestMethod.GET)
    public User Ceklogin(@PathVariable("token") String token){
        List<User> user = us.findByToken(token);
        if(user.size() == 1){
            return user.get(0);
        }else{
            return null;
        }
    }
    
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") String id){
        us.delete(us.findByUser(id).get(0));
        
        return "USER with Username"+ id +" deleted";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody User user){
        us.update(user);
        
        return "updated";
    }
       
}
