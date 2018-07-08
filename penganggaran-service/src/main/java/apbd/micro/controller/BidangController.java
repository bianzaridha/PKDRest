/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbd.micro.controller;

import apbd.micro.model.Bidang;
import apbd.micro.proxy.BidangProxy;
import apbd.micro.service.ApbdService;
import apbd.micro.service.BidangService;
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
@RequestMapping(path = "/bidang")
@Api(value = "BidangController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Bidang.class)})
public class BidangController {
    
    BidangService bserv = new BidangService();
    ApbdService apbdserv = new ApbdService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Bidang> getAll(){
        return bserv.getAll();
    }
    
    @RequestMapping(path = "/apbd/{id}",method = RequestMethod.GET)
    public List<Bidang> getByApbd(@PathVariable("id") long id){
        return bserv.getAllByApbd(id);
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List<Bidang> getByid(@PathVariable("id") long id){
        return bserv.findById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Bidang insert(@RequestBody BidangProxy bp){
        Bidang bid = new Bidang(
                        apbdserv.findById(bp.getId_apbd()).get(0),
                        bp.getId(),
                        bp.getKode(),
                        bp.getNama());
        
        
        bserv.save(bid);
        return bid;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody Bidang bid){
        bserv.update(bid);
        return "updated";
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") long id){
        bserv.delete(bserv.findById(id).get(0));
        
        return "Bidang with id "+ id +" deleted";
    }
    
    
}
