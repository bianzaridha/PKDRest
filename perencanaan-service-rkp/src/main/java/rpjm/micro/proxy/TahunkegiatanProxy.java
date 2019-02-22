/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.proxy;

import java.sql.Date;


/**
 *
 * @author bianza
 */
public class TahunkegiatanProxy {
 
    private Long id_kegiatan;
    private Long id_tp;
    private Long id;
    private String lokasi;
    private String volume;
    private Long saswan;
    private Long saspri;
    private Long sasrtm;
    private Float biaya;
    private String waktu;
    private Date mulai;
    private Date selesai;
    private String pelaksana;
    private Short pk;
    private String sumberdana;

    public Long getId_kegiatan() {
        return id_kegiatan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setId_kegiatan(Long id_kegiatan) {
        this.id_kegiatan = id_kegiatan;
    }
    

    public Long getId_tp() {
        return id_tp;
    }

    public void setId_tp(Long id_tp) {
        this.id_tp = id_tp;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    
    public Long getSaswan() {
        return saswan;
    }

    public void setSaswan(Long saswan) {
        this.saswan = saswan;
    }

    public Long getSaspri() {
        return saspri;
    }

    public void setSaspri(Long saspri) {
        this.saspri = saspri;
    }

    public Long getSasrtm() {
        return sasrtm;
    }

    public void setSasrtm(Long sasrtm) {
        this.sasrtm = sasrtm;
    }

    public Float getBiaya() {
        return biaya;
    }

    public void setBiaya(Float biaya) {
        this.biaya = biaya;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public Date getMulai() {
        return mulai;
    }

    public void setMulai(Date mulai) {
        this.mulai = mulai;
    }

    public Date getSelesai() {
        return selesai;
    }

    public void setSelesai(Date selesai) {
        this.selesai = selesai;
    }

    public String getPelaksana() {
        return pelaksana;
    }

    public void setPelaksana(String pelaksana) {
        this.pelaksana = pelaksana;
    }

    public Short getPk() {
        return pk;
    }

    public void setPk(Short pk) {
        this.pk = pk;
    }

    public String getSumberdana() {
        return sumberdana;
    }

    public void setSumberdana(String sumberdana) {
        this.sumberdana = sumberdana;
    }
    
    
}
