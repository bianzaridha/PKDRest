/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Date;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rpjm.micro.model.Kegiatan;
import rpjm.micro.model.TahunKegiatan;
import rpjm.micro.model.TahunPelaksanaan;
import rpjm.micro.proxy.KegiatanProxy;
import rpjm.micro.proxy.TahunkegiatanProxy;
import rpjm.micro.service.KegiatanService;
import rpjm.micro.service.ServiceResponse;
import rpjm.micro.service.TahunkegiatanService;
import rpjm.micro.service.TahunpelaksanaanService;

/**
 *
 * @author KIBULADA
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/tahunkegiatan")
@Api(value = "RkpController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = TahunKegiatan.class)})
public class TahunKegiatanController {
 
    TahunpelaksanaanService tps = new TahunpelaksanaanService();
    KegiatanService kk = new KegiatanService();
    TahunkegiatanService tks = new TahunkegiatanService();
    @RequestMapping(method = RequestMethod.POST)
    public ServiceResponse insert(@RequestBody TahunkegiatanProxy kg){
        final String uri = "http://localhost:8303/kegiatan/";
    
        TahunKegiatan keg = new TahunKegiatan(kk.findById(kg.getId_kegiatan()).get(0), tps.findById(kg.getId_tp()).get(0), kg.getId(), kg.getLokasi(), kg.getVolume(), kg.getSaswan(), kg.getSaspri(), kg.getSasrtm(), kg.getBiaya(), kg.getWaktu(), kg.getMulai(), kg.getSelesai(), kg.getPelaksana(), kg.getPk(), kg.getSumberdana());
        tks.save(keg);
        return new ServiceResponse(kg);
    }
    
    
}
