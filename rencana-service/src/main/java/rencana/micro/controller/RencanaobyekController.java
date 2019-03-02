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
import rencana.micro.model.RencanaObyek;
import rencana.micro.proxy.RencanaKelJenObProxy;
import rencana.micro.service.RencanajenisService;
import rencana.micro.service.RencanaobyekService;

/**
 *
 * @author bianza
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/rencanaobyek")
@Api(value = "RencanaObyekController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = RencanaObyek.class)})
public class RencanaobyekController {
    
    RencanaobyekService renobyserv = new RencanaobyekService();
    RencanajenisService renjenserv = new RencanajenisService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<RencanaObyek> getAll(){
        return renobyserv.getAll();
    }
    
    @RequestMapping(path = "/{id:.+}",method = RequestMethod.GET)
    public List<RencanaObyek> getBykode(@PathVariable("id") String id){
        return renobyserv.findByAkun(id);
    }
    
    @RequestMapping(path = "/jenis/{id:.+}",method = RequestMethod.GET)
    public List<RencanaObyek> getByJenis(@PathVariable("id") String id){
        return renobyserv.getAllByJenis(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public RencanaObyek insert(@RequestBody RencanaKelJenObProxy ap){
        
        RencanaObyek ro = new RencanaObyek(renjenserv.findByAkun(ap.getId_parent()).get(0),
                                ap.getAkun(),
                                ap.getNama());
        
        renobyserv.save(ro);
        return ro;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody RencanaObyek ap){
        renobyserv.update(ap);
        return "updated";
    }
    
    @RequestMapping(path = "/{id:.+}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") String id){
        renobyserv.delete(renobyserv.findByAkun(id).get(0));
        
        return "RencanaObyek with id "+ id +" deleted";
    }
}
