/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.proxy;

import java.util.List;
import rpjm.micro.model.PolaPelaksanaan;
import rpjm.micro.model.TahunKegiatan;

/**
 *
 * @author bianza
 */
public class RincianProxy {
    
    private List<PolapelaksanaanProxy> pp;
    private List<TahunkegiatanProxy> tk;

    
    public List<PolapelaksanaanProxy> getPp() {
        return pp;
    }

    public void setPp(List<PolapelaksanaanProxy> pp) {
        this.pp = pp;
    }

    public List<TahunkegiatanProxy> getTk() {
        return tk;
    }

    public void setTk(List<TahunkegiatanProxy> tk) {
        this.tk = tk;
    }
    
    

    
    
}
