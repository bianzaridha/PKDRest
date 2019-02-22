/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.service;

import java.util.List;
import org.hibernate.Session;
import rpjm.micro.config.HibernateUtil;
import rpjm.micro.model.Bidang;

/**
 *
 * @author bianza
 */
public class BidangService {
    
    public Bidang save(Bidang bidang){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.save(bidang);
    session.getTransaction().commit();
    session.close();
    
    return bidang;
    }

  public void batchSave(List<Bidang> list) {
    for (Bidang live : list) {
      this.save(live);
    }
  }
  
    public List<Bidang> getAll() {
    String query = "from Bidang";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Bidang> list = (List<Bidang>) session.createQuery(
        query).list();
    session.getTransaction().commit();
    session.close();
    return list;
  }
    
  public List<Bidang> findById(Long id) {
    String query = "from Bidang where id_bidang=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Bidang> Bidang = (List<Bidang>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return Bidang;
  }
  
  public List<Bidang> getAllByRpjm(Long id) {
    String query = "from Bidang where id_rpjm=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Bidang> bidang = (List<Bidang>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return bidang;
  }
  
  public void delete(Bidang bidang) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    KegiatanService keg = new KegiatanService();
    keg.batchDeleteByBidang(bidang.getId());
    
    session.delete(bidang);
    session.getTransaction().commit();
    session.close();
  }
  
  public void batchDeleteByRpjm (Long id){
    List<Bidang> list = this.getAllByRpjm(id);
    for (Bidang kill : list) {
      this.delete(kill);
    }
  }
  
  public void update(Bidang bidang) {
    String query = "update Bidang set nama_bidang= :name where id_bidang= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("name", bidang.getNama())
            .setParameter("id", bidang.getKode())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
}
