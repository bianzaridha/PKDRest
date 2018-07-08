/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and opem the template in the editor.
 */
package apbd.micro.controller;

import apbd.micro.model.RincianPembiayaan;
import apbd.micro.proxy.RincianProxy;
import apbd.micro.service.ObyekpembiayaanService;
import apbd.micro.service.RincianpembiayaanService;
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
@RequestMapping(path = "/rincianpembiayaan")
@Api(value = "RincianPembiayaanController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = RincianPembiayaan.class)})
public class RincianpembiayaanController {
    
    RincianpembiayaanService rpemserv = new RincianpembiayaanService();
    ObyekpembiayaanService opemserv = new ObyekpembiayaanService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<RincianPembiayaan> getAll(){
        return rpemserv.getAll();
    }
    
    @RequestMapping(path = "/obyek/{id}",method = RequestMethod.GET)
    public List<RincianPembiayaan> getByObyek(@PathVariable("id") long id){
        return rpemserv.getAllByObyek(id);
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List<RincianPembiayaan> getByid(@PathVariable("id") long id){
        return rpemserv.findById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public RincianPembiayaan insert(@RequestBody RincianProxy rp){
        RincianPembiayaan rpem = new RincianPembiayaan(
                        opemserv.findById(rp.getId_parent()).get(0),
                        rp.getId(),
                        rp.getNorut(),
                        rp.getUraian(),
                        rp.getJs(),
                        rp.getHs(),
                        rp.getSumberdana());
        
        
        rpemserv.save(rpem);
        return rpem;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody RincianPembiayaan rpem){
        rpemserv.update(rpem);
        return "updated";
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") long id){
        rpemserv.delete(rpemserv.findById(id).get(0));
        
        return "Rincian Pembiayaan with id "+ id +" deleted";
    }
    
}
