/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbd.micro.service;

import apbd.micro.config.HibernateUtil;
import apbd.micro.model.RincianPembiayaan;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author bianza
 */
public class RincianpembiayaanService {
    
    public RincianPembiayaan save(RincianPembiayaan rpem){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(rpem);
        session.getTransaction().commit();
        session.close();

        return rpem;
    }
    
  public void batchSave(List<RincianPembiayaan> list) {
    for (RincianPembiayaan live : list) {
      this.save(live);
    }
  }
  
  public List<RincianPembiayaan> getAll() {
    String query = "from RincianPembiayaan";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RincianPembiayaan> list = (List<RincianPembiayaan>) session.createQuery(
        query).list();
    session.getTransaction().commit();
    session.close();
    return list;
  }
  
  public List<RincianPembiayaan> findById(Long id) {
    String query = "from RincianPembiayaan where id_rincianpembiayaan=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RincianPembiayaan> rpem = (List<RincianPembiayaan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return rpem;
  }
  
    public List<RincianPembiayaan> getAllByObyek(Long id) {
    String query = "from RincianPembiayaan where id_obyekpembiayaan=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RincianPembiayaan> rpem = (List<RincianPembiayaan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return rpem;
  }
    
  public void delete(RincianPembiayaan rpem) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.delete(rpem);
    session.getTransaction().commit();
    session.close();
  }
  
  public void batchDeleteByObyek (Long id){
    List<RincianPembiayaan> list = this.getAllByObyek(id);
    for (RincianPembiayaan kill : list) {
      this.delete(kill);
    }
  }
  
  public void update(RincianPembiayaan rpem) {
    String query = "update RincianPembiayaan set "
            + "no_urutpembiayaan= :norut, "
            + "uraian= :uraian, "
            + "jumlah_satuan= :js, "
            + "harga_satuan= :hs, "
            + "sumberdana= :sumberdana "
            + "where id_rincianpembiayaan= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query).setParameter("norut",rpem.getNorut())
            .setParameter("uraian", rpem.getUraian())
            .setParameter("js", rpem.getJs())
            .setParameter("hs", rpem.getHs())
            .setParameter("sumberdana", rpem.getSumberdana())
            .setParameter("id", rpem.getId())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }     
}
