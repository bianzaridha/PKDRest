/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbd.micro.service;

import apbd.micro.config.HibernateUtil;
import apbd.micro.model.Apbd;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author bianza
 */
public class ApbdService {
    
    public Apbd save(Apbd apbd){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(apbd);
        session.getTransaction().commit();
        session.close();

        return apbd;
    }
    
  public void batchSave(List<Apbd> list) {
    for (Apbd live : list) {
      this.save(live);
    }
  }
  
  public List<Apbd> getAll() {
    String query = "from Apbd";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Apbd> list = (List<Apbd>) session.createQuery(
        query).list();
    session.getTransaction().commit();
    session.close();
    return list;
  }
  
  public List<Apbd> findById(Long id) {
    String query = "from Apbd where id_apbd=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Apbd> apbd = (List<Apbd>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return apbd;
  }
  
    public List<Apbd> getAllByRpjm(Long id) {
    String query = "from Apbd where id_rpjm=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Apbd> apbd = (List<Apbd>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return apbd;
  }
    
  public void delete(Apbd apbd) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    BidangService bs = new BidangService();
    bs.batchDeleteByApbd(apbd.getId());
    
    
    session.delete(apbd);
    session.getTransaction().commit();
    session.close();
  }
  
  public void batchDeleteByRpjm (Long id){
    List<Apbd> list = this.getAllByRpjm(id);
    for (Apbd kill : list) {
      this.delete(kill);
    }
  }
  
  public void update(Apbd apbd) {
    String query = "update Apbd set tahun_anggaran= :ta where id_apbd= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query).setParameter("ta", apbd.getTa())
            .setParameter("id", apbd.getId())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
}
