/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbd.micro.controller;

import apbd.micro.model.JenisPendapatan;
import apbd.micro.proxy.KelJenObProxy;
import apbd.micro.service.JenispendapatanService;
import apbd.micro.service.KelompokpendapatanService;
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
@RequestMapping(path = "/jenispendapatan")
@Api(value = "JenisPendapatanController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = JenisPendapatan.class)})
public class JenispendapatanController {
    
    JenispendapatanService jpenserv = new JenispendapatanService();
    KelompokpendapatanService kpenserv = new KelompokpendapatanService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List <JenisPendapatan> getAll(){
        return jpenserv.getAll();
    }
    
    @RequestMapping(path = "/kelompok/{id}",method = RequestMethod.GET)
    public List <JenisPendapatan> getByKelompok(@PathVariable("id") long id){
        return jpenserv.getAllByKelompok(id);
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List <JenisPendapatan> getByid(@PathVariable("id") long id){
        return jpenserv.findById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public JenisPendapatan insert(@RequestBody KelJenObProxy kjop){
        JenisPendapatan jpen = new JenisPendapatan(
                        kpenserv.findById(kjop.getId_parent()).get(0),
                        kjop.getId(),
                        kjop.getAkun(),
                        kjop.getNama());
        
        
        jpenserv.save(jpen);
        return jpen;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody JenisPendapatan jpen){
        jpenserv.update(jpen);
        return "updated";
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") long id){
        jpenserv.delete(jpenserv.findById(id).get(0));
        
        return "Jenis Pendapatan with id "+ id +" deleted";
    }
    
}
