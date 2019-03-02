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
import rencana.micro.model.RencanaKelompok;
import rencana.micro.proxy.RencanaKelJenObProxy;
import rencana.micro.service.RencanabidangService;
import rencana.micro.service.RencanakelompokService;

/**
 *
 * @author bianza
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/rencanakelompok")
@Api(value = "RencanaKelompokController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = RencanaKelompok.class)})
public class RencanakelompokController {
    
    RencanakelompokService renkelserv = new RencanakelompokService();
    RencanabidangService renbidserv = new RencanabidangService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<RencanaKelompok> getAll(){
        return renkelserv.getAll();
    }
    
    @RequestMapping(path = "/{id:.+}",method = RequestMethod.GET)
    public List<RencanaKelompok> getBykode(@PathVariable("id") String id){
        return renkelserv.findByAkun(id);
    }
    
    @RequestMapping(path = "/bidang/{id:.+}",method = RequestMethod.GET)
    public List<RencanaKelompok> getByBidang(@PathVariable("id") String id){
        return renkelserv.getAllByBidang(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public RencanaKelompok insert(@RequestBody RencanaKelJenObProxy ap){
        RencanaKelompok rk = new RencanaKelompok(renbidserv.findByKode(ap.getId_parent()).get(0),
                                        ap.getAkun(),
                                        ap.getNama());
        
        renkelserv.save(rk);
        return rk;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody RencanaKelompok ap){
        renkelserv.update(ap);
        return "updated";
    }
    
    @RequestMapping(path = "/{id:.+}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") String id){
        renkelserv.delete(renkelserv.findByAkun(id).get(0));
        
        return "RencanaKelompok with id "+ id +" deleted";
    }
}
