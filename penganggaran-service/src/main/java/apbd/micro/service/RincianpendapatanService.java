/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbd.micro.service;

import apbd.micro.config.HibernateUtil;
import apbd.micro.model.RincianPendapatan;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author bianza
 */
public class RincianpendapatanService {
    
    public RincianPendapatan save(RincianPendapatan rpen){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(rpen);
        session.getTransaction().commit();
        session.close();

        return rpen;
    }
    
  public void batchSave(List<RincianPendapatan> list) {
    for (RincianPendapatan live : list) {
      this.save(live);
    }
  }
  
  public List<RincianPendapatan> getAll() {
    String query = "from RincianPendapatan";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RincianPendapatan> list = (List<RincianPendapatan>) session.createQuery(
        query).list();
    session.getTransaction().commit();
    session.close();
    return list;
  }
  
  public List<RincianPendapatan> findById(Long id) {
    String query = "from RincianPendapatan where id_rincianpendapatan=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RincianPendapatan> rpen = (List<RincianPendapatan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return rpen;
  }
  
    public List<RincianPendapatan> getAllByObyek(Long id) {
    String query = "from RincianPendapatan where id_obyekpendapatan=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RincianPendapatan> rpen = (List<RincianPendapatan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return rpen;
  }
    
  public void delete(RincianPendapatan rpen) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.delete(rpen);
    session.getTransaction().commit();
    session.close();
  }
  
  public void batchDeleteByObyek (Long id){
    List<RincianPendapatan> list = this.getAllByObyek(id);
    for (RincianPendapatan kill : list) {
      this.delete(kill);
    }
  }
  
  public void update(RincianPendapatan rpen) {
    String query = "update RincianPendapatan set "
            + "no_urutpendapatan= :norut, "
            + "uraian= :uraian, "
            + "jumlah_satuan= :js, "
            + "harga_satuan= :hs, "
            + "sumberdana= :sumberdana "
            + "where id_rincianpendapatan= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query).setParameter("norut",rpen.getNorut())
            .setParameter("uraian", rpen.getUraian())
            .setParameter("js", rpen.getJs())
            .setParameter("hs", rpen.getHs())
            .setParameter("sumberdana", rpen.getSumberdana())
            .setParameter("id", rpen.getId())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }    
}
