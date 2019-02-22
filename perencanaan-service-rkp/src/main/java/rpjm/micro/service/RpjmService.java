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
import rpjm.micro.model.Rpjm;

/**
 *
 * @author bianza
 */
public class RpjmService {
    
  public Rpjm save(Rpjm rpjm){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.save(rpjm);
    session.getTransaction().commit();
    session.close();
    
    return rpjm;
    } 

    public void batchSave(List<Rpjm> list) {
        for (Rpjm live : list) {
        this.save(live);
        }
    } 
    
    public List<Rpjm> getAll() {
        String query = "from Rpjm";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Rpjm> list = (List<Rpjm>) session.createQuery(
            query).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
    
  public List<Rpjm> findById(Long id) {
    String query = "from Rpjm where id_rpjm=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Rpjm> Rpjm = (List<Rpjm>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return Rpjm;
  }
  
  public List<Rpjm> getAllByDesa(Long id) {
    String query = "from Rpjm where kode_desa=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Rpjm> Rpjm = (List<Rpjm>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return Rpjm;
  }
  
  public void delete(Rpjm rpjm) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    BidangService bs = new BidangService();
    bs.batchDeleteByRpjm(rpjm.getId());
    
    
    
    session.delete(rpjm);
    session.getTransaction().commit();
    session.close();
  }
  
  public void batchDeleteByDesa (Long id){
    List<Rpjm> list = this.getAllByDesa(id);
    for (Rpjm kill : list) {
      this.delete(kill);
    }
  }
  
  public void update(Rpjm rpjm) {
    String query = "update Rpjm set tahun_berlangsung= :name where id_rpjm= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("name", rpjm.getTb())
            .setParameter("id", rpjm.getId())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
}
