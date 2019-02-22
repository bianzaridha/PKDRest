/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author bianza
 */
@Entity
@Table(name="rpjm")
public class Rpjm implements Serializable{
   
    @Id
    @Column(name = "id_rpjm", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "kode_desa", nullable = false)
    private Long kode_desa;
    
    @Column(name = "tahun_berlangsung")
    private String tb;

    public Rpjm(Long id, Long kode_desa, String tb) {
        this.id = id;
        this.kode_desa = kode_desa;
        this.tb = tb;
    }

    public Rpjm() {
    }

    public Long getKode_desa() {
        return kode_desa;
    }
    
    public void setKode_desa(Long kode_desa) {
        this.kode_desa = kode_desa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTb() {
        return tb;
    }

    public void setTb(String tb) {
        this.tb = tb;
    }    
}
