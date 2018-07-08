/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbd.micro.controller;

import apbd.micro.model.JenisPembiayaan;
import apbd.micro.proxy.KelJenObProxy;
import apbd.micro.service.JenispembiayaanService;
import apbd.micro.service.KelompokpembiayaanService;
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
@RequestMapping(path = "/jenispembiayaan")
@Api(value = "JenisPembiayaanController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = JenisPembiayaan.class)})
public class JenispembiayaanController {
    
    JenispembiayaanService jpemserv = new JenispembiayaanService();
    KelompokpembiayaanService kpemserv = new KelompokpembiayaanService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List <JenisPembiayaan> getAll(){
        return jpemserv.getAll();
    }
    
    @RequestMapping(path = "/kelompok/{id}",method = RequestMethod.GET)
    public List <JenisPembiayaan> getByKelompok(@PathVariable("id") long id){
        return jpemserv.getAllByKelompok(id);
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List <JenisPembiayaan> getByid(@PathVariable("id") long id){
        return jpemserv.findById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public JenisPembiayaan insert(@RequestBody KelJenObProxy kjop){
        JenisPembiayaan jpem = new JenisPembiayaan(
                        kpemserv.findById(kjop.getId_parent()).get(0),
                        kjop.getId(),
                        kjop.getAkun(),
                        kjop.getNama());
        
        
        jpemserv.save(jpem);
        return jpem;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody JenisPembiayaan jpem){
        jpemserv.update(jpem);
        return "updated";
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") long id){
        jpemserv.delete(jpemserv.findById(id).get(0));
        
        return "Jenis Pembiayaan with id "+ id +" deleted";
    }
    
}
