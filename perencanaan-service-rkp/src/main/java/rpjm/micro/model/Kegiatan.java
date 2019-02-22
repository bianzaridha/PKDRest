/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.model;

import java.io.Serializable;
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
@Table(name="kegiatan")
public class Kegiatan implements Serializable {
    
    @ManyToOne
    @JoinColumn(name="id_bidang",
                nullable = false, updatable = false)
    @NotNull
    private Bidang bidang;
    
    
    @Id
    @Column(name = "id_kegiatan", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "kode_kegiatan")
    private String kode;
    
    @Column(name = "nama_kegiatan")
    private String nama;
    
    @Column(name = "lokasi_kegiatan")
    private String lokasi;
    
    @Column(name = "keluaran")
    private String keluaran;
    
    @Column(name = "pelaksanaan")
    private Short pk;
    
    @Column(name = "sasaran_manfaat")
    private String sasmanfaat;
    
    @Column(name = "sasaran_renstra")
    private String sasren;
    
    @Column(name = "status")
    private Boolean status;

    public Kegiatan(Bidang bidang,
            Long id, 
            String kode, 
            String nama, 
            String lokasi, 
            String keluaran, 
            Short pk, 
            String sasmanfaat, 
            String sasren,
            Boolean status) {
        this.bidang = bidang;
        this.id = id;
        this.kode = kode;
        this.nama = nama;
        this.lokasi = lokasi;
        this.keluaran = keluaran;
        this.pk = pk;
        this.sasmanfaat = sasmanfaat;
        this.sasren = sasren;
        this.status = status;
    }

    public Kegiatan() {
    }

    public Bidang getBidang() {
        return bidang;
    }

    public void setBidang(Bidang bidang) {
        this.bidang = bidang;
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
