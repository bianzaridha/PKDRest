/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rpjm.micro.model.PolaPelaksanaan;
import rpjm.micro.proxy.PolapelaksanaanProxy;
import rpjm.micro.proxy.RincianProxy;
import rpjm.micro.service.KegiatanService;
import rpjm.micro.service.PolapelaksanaanService;
import rpjm.micro.service.ServiceResponse;
import rpjm.micro.service.TahunkegiatanService;

/**
 *
 * @author bianza
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/rincian")
@Api(value = "RincianController" , produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = PolaPelaksanaan.class)})
public class RincianController {
    
    PolapelaksanaanService ppserv = new PolapelaksanaanService();
    TahunkegiatanService tkserv = new TahunkegiatanService();
        
    @RequestMapping(method = RequestMethod.POST)
    public ServiceResponse insert(@RequestBody RincianProxy kg){
        
        ppserv.batchSave(kg.getPp());
        tkserv.batchSave(kg.getTk());
        
        
        return new ServiceResponse(kg);
    }
    
}
