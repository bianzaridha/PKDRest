/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pelaksanaan.micro.model;

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
@Table(name="pelaksanaan")
public class Pelaksanaan {
    
    @Id
    @Column(name = "id_pelaksanaan", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "id_rpjm", nullable = false)
    private Long id_rpjm;
    
    @Column(name = "tahun_anggaran")
    private String ta;

    public Pelaksanaan(Long id, Long id_rpjm, String ta) {
        this.id = id;
        this.id_rpjm = id_rpjm;
        this.ta = ta;
    }

    public Pelaksanaan() {
    }
    

    public Long getId_rpjm() {
        return id_rpjm;
    }

    public void setId_rpjm(Long id_rpjm) {
        this.id_rpjm = id_rpjm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTa() {
        return ta;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }
    
}
