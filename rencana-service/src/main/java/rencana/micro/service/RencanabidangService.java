/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rencana.micro.service;

import rencana.micro.config.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import rencana.micro.model.RencanaBidang;

/**
 *
 * @author bianza
 */
public class RencanabidangService {
    
    public RencanaBidang save(RencanaBidang rk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(rk);
        session.getTransaction().commit();
        session.close();

        return rk;
    }
    
    public void batchSave(List<RencanaBidang> list) {
        for (RencanaBidang live : list) {
          this.save(live);
        }
    }
    
   public List<RencanaBidang> getAll() {
    String query = "from RencanaBidang";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaBidang> list = (List<RencanaBidang>) session.createQuery(
        query).list();
    session.getTransaction().commit();
    session.close();
    return list;
  }
   
  public List<RencanaBidang> findByKode(String id) {
    String query = "from RencanaBidang where kode_bidang=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaBidang> bidang = (List<RencanaBidang>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return bidang;
  }
  
  public List<RencanaBidang> getAllByJenis(Long id) {
    String query = "from RencanaBidang where jenis_bidang=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaBidang> bidang = (List<RencanaBidang>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return bidang;
  }
  
  public List<RencanaBidang> getAllByNotJenis(Long id) {
    String query = "from RencanaBidang where jenis_bidang!=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaBidang> bidang = (List<RencanaBidang>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return bidang;
  }
  
  
  public void delete(RencanaBidang bidang) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    RencanakegiatanService rks = new RencanakegiatanService();
    rks.batchDeleteByBidang(bidang.getKode());
    
    RencanakelompokService rkss = new RencanakelompokService();
    rkss.batchDeleteByBidang(bidang.getKode());
    
    session.delete(bidang);
    session.getTransaction().commit();
    session.close();
  }
  
  
  public void update(RencanaBidang bidang) {
    String query = "update RencanaBidang set nama_bidang= :name, jenis_bidang= :jenis where kode_bidang= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("name", bidang.getNama())
            .setParameter("id", bidang.getKode())
            .setParameter("jenis", bidang.getJenis())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  } 
    
}
