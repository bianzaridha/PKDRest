/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbd.micro.controller;

import apbd.micro.model.JenisBelanja;
import apbd.micro.proxy.KelJenObProxy;
import apbd.micro.service.JenisbelanjaService;
import apbd.micro.service.KelompokbelanjaService;
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
@RequestMapping(path = "/jenisbelanja")
@Api(value = "JenisBelanjaController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = JenisBelanja.class)})
public class JenisbelanjaController {
    
    JenisbelanjaService jbserv = new JenisbelanjaService();
    KelompokbelanjaService kbserv = new KelompokbelanjaService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<JenisBelanja> getAll(){
        return jbserv.getAll();
    }
    
    @RequestMapping(path = "/kelompok/{id}",method = RequestMethod.GET)
    public List<JenisBelanja> getByKelompok(@PathVariable("id") long id){
        return jbserv.getAllByKelompok(id);
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List<JenisBelanja> getByid(@PathVariable("id") long id){
        return jbserv.findById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public JenisBelanja insert(@RequestBody KelJenObProxy kjop){
        JenisBelanja jb = new JenisBelanja(
                        kbserv.findById(kjop.getId_parent()).get(0),
                        kjop.getId(),
                        kjop.getAkun(),
                        kjop.getNama());
        
        
        jbserv.save(jb);
        return jb;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody JenisBelanja jb){
        jbserv.update(jb);
        return "updated";
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") long id){
        jbserv.delete(jbserv.findById(id).get(0));
        
        return "Jenis Belanja with id "+ id +" deleted";
    }
    
}
