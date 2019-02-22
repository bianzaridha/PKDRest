/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbd.micro.controller;

import apbd.micro.model.KelompokPembiayaan;
import apbd.micro.proxy.KelJenObProxy;
import apbd.micro.service.ApbdService;
import apbd.micro.service.BidangService;
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
@RequestMapping(path = "/kelompokpembiayaan")
@Api(value = "KelompokPembiayaanController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = KelompokPembiayaan.class)})
public class KelompokpembiayaanController {
        
    KelompokpembiayaanService kpemserv = new KelompokpembiayaanService();
    BidangService bserv = new BidangService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<KelompokPembiayaan> getAll(){
        return kpemserv.getAll();
    }
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List<KelompokPembiayaan> getByid(@PathVariable("id") long id){
        return kpemserv.findById(id);
    }
    
    @RequestMapping(path = "/bidang/{id}",method = RequestMethod.GET)
    public List<KelompokPembiayaan> getByBidang(@PathVariable("id") long id){
        return kpemserv.getAllByBidang(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public KelompokPembiayaan insert(@RequestBody KelJenObProxy kjop){
        KelompokPembiayaan kpem = new KelompokPembiayaan(
                        bserv.findById(kjop.getId_parent()).get(0),
                        kjop.getId(),
                        kjop.getAkun(),
                        kjop.getNama());
        
        
        kpemserv.save(kpem);
        return kpem;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody KelompokPembiayaan kpem){
        kpemserv.update(kpem);
        return "updated";
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") long id){
        kpemserv.delete(kpemserv.findById(id).get(0));
        
        return "Kelompok Pembiayaan with id "+ id +" deleted";
    }

    
}
