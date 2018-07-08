/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author bianza
 */
@Entity
@Table(name="tahun_kegiatan")
public class TahunKegiatan implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="id_kegiatan",
                nullable = false, updatable = false)
    private Kegiatan kegiatan;
    
    @Id
    @ManyToOne
    @JoinColumn(name="id_tahun",
                nullable = false, updatable = false)
    private TahunPelaksanaan tp;
    
    @Column(name = "lokasi")
    private String lokasi;
    
    @Column(name = "volume")
    private String volume;
    
    @Column(name = "sasaran_wanita")
    private Long saswan;
    
    @Column(name = "sasaran_pria")
    private Long saspri;
    
    @Column(name = "sasaran_rtm")
    private Long sasrtm;
    
    @Column(name = "biaya")
    private Float biaya;
    
    @Column(name = "waktu")
    private String waktu;
    
    @Column(name = "mulai")
    private Date mulai;
    
    @Column(name = "selesai")
    private Date selesai;
    
    @Column(name = "pelaksana")
    private String pelaksana;
    
    @Column(name = "pola_pelaksanaan")
    private Short pk;
    
    @Column(name = "sumberdana")
    private String sumberdana;

    public TahunKegiatan(Kegiatan kegiatan, TahunPelaksanaan tp, String lokasi, String volume, Long saswan, Long saspri, Long sasrtm, String sumberdana, Float biaya, String waktu, Date mulai, Date selesai, String pelaksana, Short pk) {
        this.kegiatan = kegiatan;
        this.tp = tp;
        this.lokasi = lokasi;
        this.volume = volume;
        this.saswan = saswan;
        this.saspri = saspri;
        this.sasrtm = sasrtm;
        this.biaya = biaya;
        this.waktu = waktu;
        this.mulai = mulai;
        this.selesai = selesai;
        this.pelaksana = pelaksana;
        this.pk = pk;
        this.sumberdana = sumberdana;
    }

    public TahunKegiatan() {
    }

    public Kegiatan getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(Kegiatan kegiatan) {
        this.kegiatan = kegiatan;
    }

    public TahunPelaksanaan getTp() {
        return tp;
    }

    public void setTp(TahunPelaksanaan tp) {
        this.tp = tp;
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

    public String getSumberdana() {
        return sumberdana;
    }

    public void setSumberdana(String sumberdana) {
        this.sumberdana = sumberdana;
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
    
}