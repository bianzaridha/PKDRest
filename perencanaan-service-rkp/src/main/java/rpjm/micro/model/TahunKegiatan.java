/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author bianza
 */
@Entity
@Table(name="tahun_kegiatan")
public class TahunKegiatan implements Serializable {
    
    @ManyToOne
    @JoinColumn(name="id_kegiatan",
                nullable = false, updatable = false)
    @NotNull
    private Kegiatan kegiatan;
    
    
    @ManyToOne
    @JoinColumn(name="id_tahun",
                nullable = false, updatable = false)
    @NotNull
    private TahunPelaksanaan tp;
    
    @Id
    @Column(name = "id_tahunkegiatan", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
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

    public TahunKegiatan(Kegiatan kegiatan, TahunPelaksanaan tp, Long id, String lokasi, String volume, Long saswan, Long saspri, Long sasrtm, Float biaya, String waktu, Date mulai, Date selesai, String pelaksana, Short pk, String sumberdana) {
        this.kegiatan = kegiatan;
        this.tp = tp;
        this.id =  id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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