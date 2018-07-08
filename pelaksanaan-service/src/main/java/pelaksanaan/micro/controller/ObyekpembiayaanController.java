/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pelaksanaan.micro.controller;

import pelaksanaan.micro.model.ObyekPembiayaan;
import pelaksanaan.micro.proxy.KelJenObProxy;
import pelaksanaan.micro.service.JenispembiayaanService;
import pelaksanaan.micro.service.ObyekpembiayaanService;
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
@RequestMapping(path = "/obyekpembiayaan")
@Api(value = "ObyekPembiayaanController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ObyekPembiayaan.class)})
public class ObyekpembiayaanController {
    
    JenispembiayaanService jpemserv = new JenispembiayaanService();
    ObyekpembiayaanService opemserv = new ObyekpembiayaanService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<ObyekPembiayaan> getAll(){
        return opemserv.getAll();
    }
    
    @RequestMapping(path = "/jenis/{id}",method = RequestMethod.GET)
    public List<ObyekPembiayaan> getByJenis(@PathVariable("id") long id){
        return opemserv.getAllByJenis(id);
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List<ObyekPembiayaan> getByid(@PathVariable("id") long id){
        return opemserv.findById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ObyekPembiayaan insert(@RequestBody KelJenObProxy kjop){
        ObyekPembiayaan opem = new ObyekPembiayaan(
                        jpemserv.findById(kjop.getId_parent()).get(0),
                        kjop.getId(),
                        kjop.getAkun(),
                        kjop.getNama());
        
        
        opemserv.save(opem);
        return opem;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody ObyekPembiayaan opem){
        opemserv.update(opem);
        return "updated";
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") long id){
        opemserv.delete(opemserv.findById(id).get(0));
        
        return "Obyek Pembiayaan with id "+ id +" deleted";
    }
    
    
}
