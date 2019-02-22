/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import rpjm.micro.model.Rpjm;
import rpjm.micro.service.RpjmService;
import rpjm.micro.service.ServiceResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
/**
 *
 * @author bianza
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/rpjm")
@Api(value = "RpjmController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Rpjm.class)})
public class RpjmController {
    
    RpjmService rpjm = new RpjmService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Rpjm> getAll(){
        return rpjm.getAll();
    }
    
    @RequestMapping(path = "/desa/{id}",method = RequestMethod.GET)
    public List<Rpjm> getByDesa(@PathVariable("id") long id){
        return rpjm.getAllByDesa(id);
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List<Rpjm> getByid(@PathVariable("id") long id){
        return rpjm.findById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ServiceResponse insert(@RequestBody Rpjm rj){
        
        return new ServiceResponse(rpjm.save(rj));
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody Rpjm rj){
        rpjm.update(rj);
        return "updated";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") long id){
        rpjm.delete(rpjm.findById(id).get(0));
        
        return "RPJM with id "+ id +" deleted";
    }

    @RequestMapping(path = "/desa/{id}", method = RequestMethod.DELETE)
    public String deleteByDesa(@PathVariable("id") long id){
        rpjm.batchDeleteByDesa(id);
        
        return "RPJM with id desa "+ id +" deleted";
    }    
    
}
