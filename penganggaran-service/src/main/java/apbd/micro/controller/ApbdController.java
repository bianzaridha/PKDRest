/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbd.micro.controller;

import apbd.micro.model.Apbd;
import apbd.micro.service.ApbdService;
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
@RequestMapping(path = "/apbd")
@Api(value = "ApbdController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Apbd.class)})
public class ApbdController {
    
    ApbdService apbdserv = new ApbdService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Apbd> getAll(){
        return apbdserv.getAll();
    }
    
    @RequestMapping(path = "/rpjm/{id}",method = RequestMethod.GET)
    public List<Apbd> getByRpjm(@PathVariable("id") long id){
        return apbdserv.getAllByRpjm(id);
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List<Apbd> getByid(@PathVariable("id") long id){
        return apbdserv.findById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Apbd insert(@RequestBody Apbd ap){
        
        apbdserv.save(ap);
        return ap;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody Apbd ap){
        apbdserv.update(ap);
        return "updated";
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") long id){
        apbdserv.delete(apbdserv.findById(id).get(0));
        
        return "Apbd with id "+ id +" deleted";
    }
    
    @RequestMapping(path = "rpjm/{id}", method = RequestMethod.DELETE)
    public String deleteByRpjm(@PathVariable("id") long id){
        apbdserv.batchDeleteByRpjm(id);
        
        return "Apbd with id Rpjm "+ id +" deleted";
    }
    
}
