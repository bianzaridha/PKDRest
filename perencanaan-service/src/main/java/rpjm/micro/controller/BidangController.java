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
import org.springframework.web.bind.annotation.RestController;
import rpjm.micro.model.Bidang;
import rpjm.micro.model.Rpjm;
import rpjm.micro.proxy.BidangProxy;
import rpjm.micro.service.BidangService;
import rpjm.micro.service.RpjmService;
import rpjm.micro.service.ServiceResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author bianza
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/bidang")
@Api(value = "BidangController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Bidang.class)})
public class BidangController {
    
    BidangService bidang = new BidangService();
    RpjmService rpjm = new RpjmService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Bidang> getAll(){
        return bidang.getAll();
    }

    @RequestMapping(path = "/rpjm/{id}",method = RequestMethod.GET)
    public List<Bidang> getByRpjm(@PathVariable("id") long id){
        return bidang.getAllByRpjm(id);
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List<Bidang> getByid(@PathVariable("id") long id){
        return bidang.findById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ServiceResponse insert(@RequestBody BidangProxy bd){
        
        Bidang bdg = new Bidang(rpjm.findById(bd.getIdrpjm()).get(0),
                bd.getIdbid(), 
                bd.getKode(), 
                bd.getNama());
        return new ServiceResponse(bidang.save(bdg));
        
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody Bidang bd){
        bidang.update(bd);
        return "updated";
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") long id){
        bidang.delete(bidang.findById(id).get(0));
        
        return "Bidang with id "+ id +" deleted";
    }
    
}
