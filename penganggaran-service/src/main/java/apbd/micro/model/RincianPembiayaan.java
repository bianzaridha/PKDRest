/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbd.micro.model;

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
@Table(name="rincian_pembiayaan")
public class RincianPembiayaan {
    
    @ManyToOne
    @JoinColumn(name="id_obyekpembiayaan",
                nullable = false, updatable = false)
    @NotNull
    private ObyekPembiayaan obypembiayaan;
    
    @Id
    @Column(name = "id_rincianpembiayaan", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "no_urutpembiayaan")
    private Long norut;
    
    @Column(name = "uraian")
    private String uraian;
    
    @Column(name = "jumlah_satuan")
    private Long js;
    
    @Column(name = "harga_satuan")
    private Long hs;
    
    @Column(name = "sumberdana")
    private String sumberdana;

    public RincianPembiayaan(ObyekPembiayaan obypembiayaan, Long id, Long norut, String uraian, Long js, Long hs, String sumberdana) {
        this.obypembiayaan = obypembiayaan;
        this.id = id;
        this.norut = norut;
        this.uraian = uraian;
        this.js = js;
        this.hs = hs;
        this.sumberdana = sumberdana;
    }


    public RincianPembiayaan() {
    }

    public ObyekPembiayaan getObypembiayaan() {
        return obypembiayaan;
    }

    public void setObypembiayaan(ObyekPembiayaan obypembiayaan) {
        this.obypembiayaan = obypembiayaan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNorut() {
        return norut;
    }

    public void setNorut(Long norut) {
        this.norut = norut;
    }

    public String getUraian() {
        return uraian;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    public Long getJs() {
        return js;
    }

    public void setJs(Long js) {
        this.js = js;
    }

    public Long getHs() {
        return hs;
    }

    public void setHs(Long hs) {
        this.hs = hs;
    }

    public String getSumberdana() {
        return sumberdana;
    }

    public void setSumberdana(String sumberdana) {
        this.sumberdana = sumberdana;
    }
    
    
    
}
