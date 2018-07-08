/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.controller;

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
import rpjm.micro.model.Kegiatan;
import rpjm.micro.model.PolaPelaksanaan;
import rpjm.micro.proxy.KegiatanProxy;
import rpjm.micro.service.BidangService;
import rpjm.micro.service.KegiatanService;
import rpjm.micro.service.PolapelaksanaanService;
import rpjm.micro.service.RpjmService;
import rpjm.micro.service.ServiceResponse;

/**
 *
 * @author bianza
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/kegiatan")
@Api(value = "KegiatanController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Kegiatan.class)})
public class KegiatanController {
    
    KegiatanService kegiatan = new KegiatanService();
    PolapelaksanaanService  polapelaksanaan = new PolapelaksanaanService();
    BidangService bidang = new BidangService();
    RpjmService rpjm = new RpjmService();
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Kegiatan> getAll(){
        return kegiatan.getAll();
    }
    
    
    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public List<Kegiatan> getByid(@PathVariable("id") long id){
        return kegiatan.findById(id);
    }    
    
    
    @RequestMapping(path = "/bidang/{id}",method = RequestMethod.GET)
    public List<Kegiatan> getByBidang(@PathVariable("id") long id){
        return kegiatan.getAllByBidang(id);
    }    
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ServiceResponse insert(@RequestBody KegiatanProxy kg){
        Kegiatan keg = new Kegiatan(bidang.findById(kg.getId_bidang()).get(0),
                kg.getId(),
                kg.getKode(),
                kg.getNama(),
                kg.getLokasi(),
                kg.getKeluaran(),
                kg.getPk(),
                kg.getSasmanfaat(),
                kg.getSasren(),
                kg.getStatus());
        kegiatan.save(keg);
        
        PolaPelaksanaan pp = new PolaPelaksanaan(kegiatan.findById(kg.getPp().getId_kegiatan()).get(0), 
                kg.getPp().getId(), 
                kg.getPp().getNama());
        
        polapelaksanaan.save(pp);
        return new ServiceResponse(kg);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody Kegiatan kg){
        kegiatan.update(kg);
        return "updated";
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") long id){
        kegiatan.delete(kegiatan.findById(id).get(0));
        
        return "kegiatan with id "+ id +" deleted";
    }
}
