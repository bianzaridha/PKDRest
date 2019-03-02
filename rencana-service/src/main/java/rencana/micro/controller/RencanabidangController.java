/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rencana.micro.controller;

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
import rencana.micro.model.RencanaBidang;
import rencana.micro.service.RencanabidangService;

/**
 *
 * @author bianza
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/rencanabidang")
@Api(value = "RencanaBidangController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = RencanaBidang.class)})
public class RencanabidangController {
    
    RencanabidangService renbidserv = new RencanabidangService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<RencanaBidang> getAll(){
        return renbidserv.getAll();
    }
    
    @RequestMapping(path = "/{id:.+}",method = RequestMethod.GET)
    public List<RencanaBidang> getBykode(@PathVariable("id") String id){
        return renbidserv.findByKode(id);
    }
    
    @RequestMapping(path = "/jenis/{id}",method = RequestMethod.GET)
    public List<RencanaBidang> getByJenis(@PathVariable("id") Long id){
        return renbidserv.getAllByJenis(id);
    }
    
    @RequestMapping(path = "/notjenis/{id}",method = RequestMethod.GET)
    public List<RencanaBidang> getByNotJenis(@PathVariable("id") Long id){
        return renbidserv.getAllByNotJenis(id);
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public RencanaBidang insert(@RequestBody RencanaBidang ap){
        
        renbidserv.save(ap);
        return ap;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody RencanaBidang ap){
        renbidserv.update(ap);
        return "updated";
    }
    
    @RequestMapping(path = "/{id:.+}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") String id){
        renbidserv.delete(renbidserv.findByKode(id).get(0));
        
        return "RencanaBidang with id "+ id +" deleted";
    }
}
