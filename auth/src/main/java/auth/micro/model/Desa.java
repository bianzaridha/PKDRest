/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth.micro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author bianza
 */
@Entity
@Table(name="desa")
public class Desa {
    
    @Id
    @Column(name = "kode_pos", nullable = false)
    private String id;
    
    @Column(name = "nama_desa")
    private String nama;
    
    @Column(name = "kecamatan")
    private String kecamatan;
    
    @Column(name = "kabupaten_kota")
    private String kabkot;
    
    @Column(name = "provinsi")
    private String provinsi;
    
    @Column(name = "luas_wilayah")
    private Long lwilayah;
    
    @Column(name = "jumlah_penduduk")
    private Long jp;

    public Desa(String id, String nama, String kecamatan, String kabkot, String provinsi, Long lwilayah, Long jp) {
        this.id = id;
        this.nama = nama;
        this.kecamatan = kecamatan;
        this.kabkot = kabkot;
        this.provinsi = provinsi;
        this.lwilayah = lwilayah;
        this.jp = jp;
    }
    
    public Desa(){
        
    }

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKabkot() {
        return kabkot;
    }

    public void setKabkot(String kabkot) {
        this.kabkot = kabkot;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public Long getLwilayah() {
        return lwilayah;
    }

    public void setLwilayah(Long lwilayah) {
        this.lwilayah = lwilayah;
    }

    public Long getJp() {
        return jp;
    }

    public void setJp(Long jp) {
        this.jp = jp;
    }   
}