/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.proxy;

import java.util.List;
import rpjm.micro.model.PolaPelaksanaan;

/**
 *
 * @author bianza
 */
public class KegiatanProxy {
    
    private Long id_bidang;
    private Long id;
    private String kode;
    private String nama;
    private String lokasi;
    private String keluaran;
    private Short pk;
    private String sasmanfaat;
    private String sasren;
    private Boolean status;
    

    public Long getId_bidang() {
        return id_bidang;
    }

    public void setId_bidang(Long id_bidang) {
        this.id_bidang = id_bidang;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKeluaran() {
        return keluaran;
    }

    public void setKeluaran(String keluaran) {
        this.keluaran = keluaran;
    }

    public Short getPk() {
        return pk;
    }

    public void setPk(Short pk) {
        this.pk = pk;
    }

    public String getSasmanfaat() {
        return sasmanfaat;
    }

    public void setSasmanfaat(String sasmanfaat) {
        this.sasmanfaat = sasmanfaat;
    }

    public String getSasren() {
        return sasren;
    }

    public void setSasren(String sasren) {
        this.sasren = sasren;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
