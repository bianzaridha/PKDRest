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
import rencana.micro.model.RencanaKegiatan;
import rencana.micro.service.RencanakegiatanService;
import rencana.micro.proxy.RencanaKegiatanProxy;
import rencana.micro.service.RencanabidangService;

/**
 *
 * @author bianza
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/rencanakegiatan")
@Api(value = "RencanaKegiatanController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = RencanaKegiatan.class)})
public class RencanakegiatanController {
    
    RencanakegiatanService renkegserv = new RencanakegiatanService();
    RencanabidangService renbidserv = new RencanabidangService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<RencanaKegiatan> getAll(){
        return renkegserv.getAll();
    }
    
    @RequestMapping(path = "/{id:.+}",method = RequestMethod.GET)
    public List<RencanaKegiatan> getBykode(@PathVariable("id") String id){
        return renkegserv.findByKode(id);
    }
    
    @RequestMapping(path = "/bidang/{id:.+}",method = RequestMethod.GET)
    public List<RencanaKegiatan> getByBidang(@PathVariable("id") String id){
        return renkegserv.getAllByBidang(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public RencanaKegiatan insert(@RequestBody RencanaKegiatanProxy ap){
        RencanaKegiatan rk = new RencanaKegiatan(renbidserv.findByKode(ap.getId_parent()).get(0),
                                    ap.getKode(),
                                    ap.getNama());
        
        renkegserv.save(rk);
        return rk;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody RencanaKegiatan ap){
        renkegserv.update(ap);
        return "updated";
    }
    
    @RequestMapping(path = "/{id:.+}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") String id){
        renkegserv.delete(renkegserv.findByKode(id).get(0));
        
        return "RencanaKegiatan with id "+ id +" deleted";
    }
}
