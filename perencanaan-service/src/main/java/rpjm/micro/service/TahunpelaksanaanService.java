/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.service;

import java.util.List;
import org.hibernate.Session;
import rpjm.micro.config.HibernateUtil;
import rpjm.micro.model.TahunPelaksanaan;

/**
 *
 * @author bianza
 */
public class TahunpelaksanaanService {
    public TahunPelaksanaan save(TahunPelaksanaan tp){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(tp);
        session.getTransaction().commit();
        session.close();

        return tp;
    } 

    public void batchSave(List<TahunPelaksanaan> list) {
        for (TahunPelaksanaan live : list) {
        this.save(live);
        }
    }

    public List<TahunPelaksanaan> getAll() {
        String query = "from TahunPelaksanaan";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<TahunPelaksanaan> list = (List<TahunPelaksanaan>) session.createQuery(
            query).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }    
    
  public List<TahunPelaksanaan> findById(Long id) {
    String query = "from TahunPelaksanaan where id_tahun=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<TahunPelaksanaan> tp = (List<TahunPelaksanaan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return tp;
  }
  
    public void delete(TahunPelaksanaan tp) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    TahunkegiatanService tk = new TahunkegiatanService();
    tk.batchDeleteByTahun(tp.getId());
    
    session.delete(tk);
    session.getTransaction().commit();
    session.close();
  }
  
  
  public void update(TahunPelaksanaan tp) {
    String query = "update TahunPelaksanaan set tahun= :name "
                    + "where id_tahun= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("name", tp.getTahun())
            .setParameter("id", tp.getId())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }    
}
