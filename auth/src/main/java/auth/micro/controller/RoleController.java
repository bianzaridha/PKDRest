/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth.micro.controller;

import auth.micro.model.Role;
import auth.micro.service.RoleService;
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
@RequestMapping(path="/role")
@Api(value = "RoleController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Role.class)})
public class RoleController {
    
    RoleService role = new RoleService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Role> getAll(){
        return role.getAll();
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List<Role> getById(@PathVariable("id") Integer id){
        return role.findById(id);
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public Role insert(@RequestBody Role ds){
        return role.save(ds);
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        role.delete(role.findById(id).get(0));
        
        return "role with id "+ id +" deleted";
    }
}
