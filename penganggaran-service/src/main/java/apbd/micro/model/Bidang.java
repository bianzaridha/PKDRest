/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbd.micro.model;

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
@Table(name="bidang")
public class Bidang implements Serializable {
    
    @ManyToOne
    @JoinColumn(name="id_apbd",
                nullable = false, updatable = false)
    @NotNull
    private Apbd apbd;
    
    @Id
    @Column(name = "id_bidang", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "kode_bidang")
    private String kode;
    
    @Column(name = "nama_bidang")
    private String nama;

    public Bidang(Apbd Apbd, Long id, String kode, String nama) {
        this.apbd = apbd;
        this.id = id;
        this.kode = kode;
        this.nama = nama;
    }

    public Bidang() {
    }
    
    

    public Apbd getApbd() {
        return apbd;
    }

    public void setApbd(Apbd apbd) {
        this.apbd = apbd;
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

}
