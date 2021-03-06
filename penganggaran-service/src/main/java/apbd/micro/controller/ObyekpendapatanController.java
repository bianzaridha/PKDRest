/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbd.micro.controller;

import apbd.micro.model.ObyekPendapatan;
import apbd.micro.proxy.KelJenObProxy;
import apbd.micro.service.JenispendapatanService;
import apbd.micro.service.ObyekpendapatanService;
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
@RequestMapping(path = "/openyekpendapatan")
@Api(value = "ObyekPendapatanController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ObyekPendapatan.class)})
public class ObyekpendapatanController {
    
    JenispendapatanService jpenserv = new JenispendapatanService();
    ObyekpendapatanService openserv = new ObyekpendapatanService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<ObyekPendapatan> getAll(){
        return openserv.getAll();
    }
    
    @RequestMapping(path = "/jenis/{id}",method = RequestMethod.GET)
    public List<ObyekPendapatan> getByJenis(@PathVariable("id") long id){
        return openserv.getAllByJenis(id);
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List<ObyekPendapatan> getByid(@PathVariable("id") long id){
        return openserv.findById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ObyekPendapatan insert(@RequestBody KelJenObProxy kjop){
        ObyekPendapatan open = new ObyekPendapatan(
                        jpenserv.findById(kjop.getId_parent()).get(0),
                        kjop.getId(),
                        kjop.getAkun(),
                        kjop.getNama());
        
        
        openserv.save(open);
        return open;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody ObyekPendapatan open){
        openserv.update(open);
        return "updated";
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") long id){
        openserv.delete(openserv.findById(id).get(0));
        
        return "Obyek Pendapatan with id "+ id +" deleted";
    }
    
    
}
