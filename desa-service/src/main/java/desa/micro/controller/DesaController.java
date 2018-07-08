/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desa.micro.controller;

import desa.micro.model.Desa;
import desa.micro.service.DesaService;
import desa.micro.service.ServiceResponse;
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
@RequestMapping(path="/desa")
@Api(value = "DesaController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Desa.class)})
public class DesaController {
    
    DesaService desa = new DesaService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Desa> getAll(){
        return desa.getAll();
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List<Desa> getById(@PathVariable("id") long id){
        return desa.findById(id);
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ServiceResponse insert(@RequestBody Desa ds){
        return new ServiceResponse(desa.save(ds));
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") long id){
        desa.delete(desa.findById(id).get(0));
        
        return "desa with id "+ id +" deleted";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody Desa ds){
        desa.update(ds);
        
        return "updated";
    }
}
