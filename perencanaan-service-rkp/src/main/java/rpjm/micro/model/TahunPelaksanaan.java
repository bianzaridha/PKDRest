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
import javax.persistence.Table;

/**
 *
 * @author bianza
 */
@Entity
@Table(name="tahun_pelaksanaan")
public class TahunPelaksanaan implements Serializable {
     @Id
    @Column(name = "id_tahun", nullable = false)
     @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     
     @Column(name = "tahun")
     private String tahun;

    public TahunPelaksanaan(Long id, String tahun) {
        this.id = id;
        this.tahun = tahun;
    }

    public TahunPelaksanaan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
   
}
