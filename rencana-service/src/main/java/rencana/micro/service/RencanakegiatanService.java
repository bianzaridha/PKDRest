/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rencana.micro.service;

import rencana.micro.config.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import rencana.micro.model.RencanaKegiatan;

/**
 *
 * @author bianza
 */
public class RencanakegiatanService {
    
    public RencanaKegiatan save(RencanaKegiatan rk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(rk);
        session.getTransaction().commit();
        session.close();

        return rk;
    }
    
    public void batchSave(List<RencanaKegiatan> list) {
        for (RencanaKegiatan live : list) {
          this.save(live);
        }
    }
    
   public List<RencanaKegiatan> getAll() {
    String query = "from RencanaKegiatan";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaKegiatan> list = (List<RencanaKegiatan>) session.createQuery(
        query).list();
    session.getTransaction().commit();
    session.close();
    return list;
  }
   
  public List<RencanaKegiatan> findByKode(String id) {
    String query = "from RencanaKegiatan where kode_kegiatan=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaKegiatan> kegiatan = (List<RencanaKegiatan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return kegiatan;
  }
  
  public List<RencanaKegiatan> getAllByBidang(String id) {
    String query = "from RencanaKegiatan where kode_bidang=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaKegiatan> kegiatan = (List<RencanaKegiatan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return kegiatan;
  }
  
  public void delete(RencanaKegiatan kegiatan) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.delete(kegiatan);
    session.getTransaction().commit();
    session.close();
  }
  
  public void batchDeleteByBidang (String id){
    List<RencanaKegiatan> list = this.getAllByBidang(id);
    for (RencanaKegiatan kill : list) {
      this.delete(kill);
    }
  }
  
  public void update(RencanaKegiatan kegiatan) {
    String query = "update RencanaKegiatan set nama_kegiatan= :name where kode_kegiatan= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("name", kegiatan.getNama())
            .setParameter("id", kegiatan.getKode())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  } 
    
}
