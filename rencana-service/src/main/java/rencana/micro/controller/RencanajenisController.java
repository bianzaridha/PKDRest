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
import rencana.micro.model.RencanaJenis;
import rencana.micro.proxy.RencanaKelJenObProxy;
import rencana.micro.service.RencanajenisService;
import rencana.micro.service.RencanakelompokService;

/**
 *
 * @author bianza
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/rencanajenis")
@Api(value = "RencanaJenisController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = RencanaJenis.class)})
public class RencanajenisController {
    
    RencanajenisService renjenserv = new RencanajenisService();
    RencanakelompokService renkelserv = new RencanakelompokService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<RencanaJenis> getAll(){
        return renjenserv.getAll();
    }
    
    @RequestMapping(path = "/{id:.+}",method = RequestMethod.GET)
    public List<RencanaJenis> getBykode(@PathVariable("id") String id){
        return renjenserv.findByAkun(id);
    }
    
    @RequestMapping(path = "/kelompok/{id:.+}",method = RequestMethod.GET)
    public List<RencanaJenis> getByKelompok(@PathVariable("id") String id){
        return renjenserv.getAllByKelompok(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public RencanaJenis insert(@RequestBody RencanaKelJenObProxy ap){
        
        RencanaJenis rj = new RencanaJenis(renkelserv.findByAkun(ap.getId_parent()).get(0),
                                ap.getAkun(),
                                ap.getNama());
        
        renjenserv.save(rj);
        return rj;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody RencanaJenis ap){
        renjenserv.update(ap);
        return "updated";
    }
    
    @RequestMapping(path = "/{id:.+}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") String id){
        renjenserv.delete(renjenserv.findByAkun(id).get(0));
        
        return "RencanaJenis with id "+ id +" deleted";
    }
}
