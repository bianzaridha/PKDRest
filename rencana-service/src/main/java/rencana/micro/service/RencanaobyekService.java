/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rencana.micro.service;

import rencana.micro.config.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import rencana.micro.model.RencanaObyek;

/**
 *
 * @author bianza
 */
public class RencanaobyekService {
    
    public RencanaObyek save(RencanaObyek rk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(rk);
        session.getTransaction().commit();
        session.close();

        return rk;
    }
    
    public void batchSave(List<RencanaObyek> list) {
        for (RencanaObyek live : list) {
          this.save(live);
        }
    }
    
   public List<RencanaObyek> getAll() {
    String query = "from RencanaObyek";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaObyek> list = (List<RencanaObyek>) session.createQuery(
        query).list();
    session.getTransaction().commit();
    session.close();
    return list;
  }
   
  public List<RencanaObyek> findByAkun(String id) {
    String query = "from RencanaObyek where akun_obyek=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaObyek> obyek = (List<RencanaObyek>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return obyek;
  }
  
  public List<RencanaObyek> getAllByJenis(String id) {
    String query = "from RencanaObyek where akun_jenis=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<RencanaObyek> obyek = (List<RencanaObyek>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return obyek;
  }
  
  public void delete(RencanaObyek obyek) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.delete(obyek);
    session.getTransaction().commit();
    session.close();
  }
  
  public void batchDeleteByJenis(String id){
    List<RencanaObyek> list = this.getAllByJenis(id);
    for (RencanaObyek kill : list) {
      this.delete(kill);
    }
  }
  
  public void update(RencanaObyek obyek) {
    String query = "update RencanaObyek set nama_obyek= :name where akun_obyek= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("name", obyek.getNama())
            .setParameter("id", obyek.getAkun())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  } 
    
}
