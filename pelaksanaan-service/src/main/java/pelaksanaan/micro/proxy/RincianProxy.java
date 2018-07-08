/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pelaksanaan.micro.proxy;

/**
 *
 * @author bianza
 */
public class RincianProxy {
    private Long id_parent;
    private Long id;
    private Long norut;
    private String uraian;
    private Long js;
    private Long hs;
    private String sumberdana;

    public Long getId_parent() {
        return id_parent;
    }

    public void setId_parent(Long id_parent) {
        this.id_parent = id_parent;
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
