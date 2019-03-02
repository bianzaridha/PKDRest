/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rencana.micro.service;

import rencana.micro.config.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import rencana.micro.model.RencanaKelompok;

/**
 *
 * @author bianza
 */
public class RencanakelompokService {
    
    public RencanaKelompok save(RencanaKelompok rk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(rk);
        session.getTransaction().commit();
        session.close();

        return rk;
    }
    
    public void batchSave(List<RencanaKelompok> list) {
        for (RencanaKelompok live : list) {
          this.save(live);
        }
    }
    
   public List<RencanaKelompok> getAll() {
    String query = "from RencanaKelompok";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaKelompok> list = (List<RencanaKelompok>) session.createQuery(
        query).list();
    session.getTransaction().commit();
    session.close();
    return list;
  }
   
  public List<RencanaKelompok> findByAkun(String id) {
    String query = "from RencanaKelompok where akun_kelompok=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaKelompok> kelompok = (List<RencanaKelompok>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return kelompok;
  }
  
  public List<RencanaKelompok> getAllByBidang(String id) {
    String query = "from RencanaKelompok where kode_bidang=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaKelompok> kelompok = (List<RencanaKelompok>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return kelompok;
  }
  
  public void delete(RencanaKelompok kelompok) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    RencanajenisService rjs = new RencanajenisService();
    rjs.batchDeleteByKelompok(kelompok.getAkun());
    
    session.delete(kelompok);
    session.getTransaction().commit();
    session.close();
  }
  
  public void batchDeleteByBidang (String id){
    List<RencanaKelompok> list = this.getAllByBidang(id);
    for (RencanaKelompok kill : list) {
      this.delete(kill);
    }
  }
  
  public void update(RencanaKelompok kelompok) {
    String query = "update RencanaKelompok set nama_kelompok= :name where akun_kelompok= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("name", kelompok.getNama())
            .setParameter("id", kelompok.getAkun())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  } 
    
}
