/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.service;

import java.util.List;
import org.hibernate.Session;
import rpjm.micro.config.HibernateUtil;
import rpjm.micro.model.PolaPelaksanaan;

/**
 *
 * @author bianza
 */
public class PolapelaksanaanService {
    
    public PolaPelaksanaan save(PolaPelaksanaan pk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(pk);
        session.getTransaction().commit();
        session.close();

        return pk;
    } 

    public void batchSave(List<PolaPelaksanaan> list) {
        for (PolaPelaksanaan live : list) {
        this.save(live);
        }
    }

    public List<PolaPelaksanaan> getAll() {
        String query = "from PolaPelaksanaan";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<PolaPelaksanaan> list = (List<PolaPelaksanaan>) session.createQuery(
            query).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }    
    
  public List<PolaPelaksanaan> findById(Long id) {
    String query = "from PolaPelaksanaan where id_pola=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<PolaPelaksanaan> pk = (List<PolaPelaksanaan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return pk;
  }

  public List<PolaPelaksanaan> getAllByKegiatan(Long id) {
    String query = "from PolaPelaksanaan where id_kegiatan=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<PolaPelaksanaan> pk = (List<PolaPelaksanaan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return pk;
  }
  
    public void delete(PolaPelaksanaan pp) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.delete(pp);
    session.getTransaction().commit();
    session.close();
  }
  
  public void batchDeleteByKegiatan (Long id){
    List<PolaPelaksanaan> list = this.getAllByKegiatan(id);
    for (PolaPelaksanaan kill : list) {
      this.delete(kill);
    }
  }
  
  public void update(PolaPelaksanaan pk) {
    String query = "update PolaPelaksanaan set nama_pola= :name "
                    + "where id_pola= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("name", pk.getNama())
            .setParameter("id", pk.getId())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
}
