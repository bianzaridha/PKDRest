/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apbd.micro.service;

import apbd.micro.config.HibernateUtil;
import apbd.micro.model.ObyekPembiayaan;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author bianza
 */
public class ObyekpembiayaanService {
    
    public ObyekPembiayaan save(ObyekPembiayaan opem){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(opem);
        session.getTransaction().commit();
        session.close();

        return opem;
    }
    
  public void batchSave(List<ObyekPembiayaan> list) {
    for (ObyekPembiayaan live : list) {
      this.save(live);
    }
  }
  
  public List<ObyekPembiayaan> getAll() {
    String query = "from ObyekPembiayaan";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<ObyekPembiayaan> list = (List<ObyekPembiayaan>) session.createQuery(
        query).list();
    session.getTransaction().commit();
    session.close();
    return list;
  }
  
  public List<ObyekPembiayaan> findById(Long id) {
    String query = "from ObyekPembiayaan where id_obyekpembiayaan=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<ObyekPembiayaan> opem = (List<ObyekPembiayaan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return opem;
  }
  
    public List<ObyekPembiayaan> getAllByJenis(Long id) {
    String query = "from ObyekPembiayaan where id_jenispembiayaan=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<ObyekPembiayaan> opem = (List<ObyekPembiayaan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return opem;
  }
    
  public void delete(ObyekPembiayaan opem) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
     
    RincianpembiayaanService rps = new RincianpembiayaanService();
    rps.batchDeleteByObyek(opem.getId());
    
    session.delete(opem);
    session.getTransaction().commit();
    session.close();
  }
  
  public void batchDeleteByJenis (Long id){
    List<ObyekPembiayaan> list = this.getAllByJenis(id);
    for (ObyekPembiayaan kill : list) {
      this.delete(kill);
    }
  }
  
  public void update(ObyekPembiayaan opem) {
    String query = "update ObyekPembiayaan set "
            + "akun_obyekpembiayaan= :akun, "
            + "nama_obyekpembiayaan= :nama "
            + "where id_obyekpembiayaan= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query).setParameter("akun", opem.getAkun())
            .setParameter("nama", opem.getNama())
            .setParameter("id", opem.getId())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }  
}
