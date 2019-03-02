/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rencana.micro.service;

import rencana.micro.config.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import rencana.micro.model.RencanaJenis;

/**
 *
 * @author bianza
 */
public class RencanajenisService {
    
    public RencanaJenis save(RencanaJenis rk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(rk);
        session.getTransaction().commit();
        session.close();

        return rk;
    }
    
    public void batchSave(List<RencanaJenis> list) {
        for (RencanaJenis live : list) {
          this.save(live);
        }
    }
    
   public List<RencanaJenis> getAll() {
    String query = "from RencanaJenis";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaJenis> list = (List<RencanaJenis>) session.createQuery(
        query).list();
    session.getTransaction().commit();
    session.close();
    return list;
  }
   
  public List<RencanaJenis> findByAkun(String id) {
    String query = "from RencanaJenis where akun_jenis=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaJenis> jenis = (List<RencanaJenis>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return jenis;
  }
  
  public List<RencanaJenis> getAllByKelompok(String id) {
    String query = "from RencanaJenis where akun_kelompok=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaJenis> jenis = (List<RencanaJenis>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return jenis;
  }
  
  public void delete(RencanaJenis jenis) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    RencanaobyekService ros = new RencanaobyekService();
    ros.batchDeleteByJenis(jenis.getAkun());
    
    session.delete(jenis);
    session.getTransaction().commit();
    session.close();
  }
  
  public void batchDeleteByKelompok(String id){
    List<RencanaJenis> list = this.getAllByKelompok(id);
    for (RencanaJenis kill : list) {
      this.delete(kill);
    }
  }
  
  public void update(RencanaJenis jenis) {
    String query = "update RencanaJenis set nama_jenis= :name where akun_jenis= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("name", jenis.getNama())
            .setParameter("id", jenis.getAkun())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  } 
    
}
