/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.service;

import java.util.List;
import org.hibernate.Session;
import org.springframework.web.client.RestTemplate;
import rpjm.micro.config.HibernateUtil;
import rpjm.micro.model.Kegiatan;
import rpjm.micro.model.Rpjm;

/**
 *
 * @author bianza
 */
public class KegiatanService {
        
  public Kegiatan save(Kegiatan kegiatan){
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.save(kegiatan);
    session.getTransaction().commit();
    session.close();
    
    return kegiatan;
    } 

    public void batchSave(List<Kegiatan> list) {
        for (Kegiatan live : list) {
        this.save(live);
        }
    } 
    
    public List<Kegiatan> getAll() {
        String query = "from Kegiatan";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Kegiatan> list = (List<Kegiatan>) session.createQuery(
            query).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
    
  public List<Kegiatan> findById(Long id) {
    String query = "from Kegiatan where id_kegiatan=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Kegiatan> kegiatan = (List<Kegiatan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return kegiatan;
  }
  
  public List<Kegiatan> getAllByBidangStatus(Long id) {
    String query = "from Kegiatan where id_bidang=:id and status=false";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Kegiatan> kegiatan = (List<Kegiatan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return kegiatan;
  }
  
    public List<Kegiatan> getAllByBidang(Long id) {
    String query = "from Kegiatan where id_bidang=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Kegiatan> kegiatan = (List<Kegiatan>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return kegiatan;
  }

  
  public void delete(Kegiatan keg) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
//    
     PolapelaksanaanService pks = new PolapelaksanaanService();
     pks.batchDeleteByKegiatan(keg.getId());
//   
    TahunkegiatanService tks = new TahunkegiatanService();
    tks.batchDeleteByKegiatan(keg.getId());
    
    
    session.delete(keg);
    session.getTransaction().commit();
    session.close();
  }
  
  
  public void batchDeleteByBidang (Long id){
    List<Kegiatan> list = this.getAllByBidang(id);
    for (Kegiatan kill : list) {
      this.delete(kill);
    }
  }
  
    public void update(Kegiatan kegiatan) {
    String query = "update Kegiatan set kode_kegiatan= :kode, "
                     + "nama_kegiatan= :name, "
                     + "lokasi_kegiatan= :lokasi, "
                     + "keluaran= :keluaran, "
                     + "pelaksanaan= :pk, "
                     + "sasaran_manfaat= :sm, "
                     + "sasaran_renstra= :sr, "
                     + "status= :status "
                     + "where id_kegiatan= :id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("kode", kegiatan.getKode())
            .setParameter("name", kegiatan.getNama())
            .setParameter("lokasi", kegiatan.getLokasi())
            .setParameter("keluaran", kegiatan.getKeluaran())
            .setParameter("pk", kegiatan.getPk())
            .setParameter("sm", kegiatan.getSasmanfaat())
            .setParameter("sr", kegiatan.getSasren())
            .setParameter("status", kegiatan.getStatus())
            .setParameter("id", kegiatan.getId())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
}
