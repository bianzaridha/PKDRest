/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpjm.micro.service;

import java.util.List;
import org.hibernate.Session;
import rpjm.micro.config.HibernateUtil;
import rpjm.micro.model.TahunKegiatan;

/**
 *
 * @author bianza
 */
public class TahunkegiatanService {
    public TahunKegiatan save(TahunKegiatan tk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(tk);
        session.getTransaction().commit();
        session.close();

        return tk;
    } 

    public void batchSave(List<TahunKegiatan> list) {
        for (TahunKegiatan live : list) {
        this.save(live);
        }
    }

    public List<TahunKegiatan> getAll() {
        String query = "from TahunKegiatan";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<TahunKegiatan> list = (List<TahunKegiatan>) session.createQuery(
            query).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public List<TahunKegiatan> getAllByKegiatan(Long keg) {
        String query = "from TahunKegiatan where id_kegiatan=:keg";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<TahunKegiatan> tk = (List<TahunKegiatan>) session.createQuery(
            query).setParameter("keg", keg).list();
        session.getTransaction().commit();
        session.close();
        return tk;
    }
    
    public List<TahunKegiatan> getAllByTahun(Long tah) {
        String query = "from TahunKegiatan where id_tahun=:tah";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<TahunKegiatan> tk = (List<TahunKegiatan>) session.createQuery(
            query).setParameter("keg", tah).list();
        session.getTransaction().commit();
        session.close();
        return tk;
    }
    
    public List<TahunKegiatan> getAllByKegTahun(Long tah, Long keg) {
        String query = "from TahunKegiatan where id_kegiatan=:keg and id_tahun=:tah";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<TahunKegiatan> tk = (List<TahunKegiatan>) session.createQuery(
            query).setParameter("keg", tah).list();
        session.getTransaction().commit();
        session.close();
        return tk;
    }
    
  public List<TahunKegiatan> findById(Long kid, Long tid) {
    String query = "from TahunKegiatan "
            + "where id_kegiatan=:kid and id_tahun=:tid";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<TahunKegiatan> tk = (List<TahunKegiatan>) session.createQuery(query)
                    .setParameter("kid", kid)
                    .setParameter("tid", tid)
                    .list();
    session.getTransaction().commit();
    session.close();
    return tk;
  }
  
  public void delete(TahunKegiatan tk) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.delete(tk);
    session.getTransaction().commit();
    session.close();
  }
  
  public void batchDeleteByKegiatan (Long id){
    List<TahunKegiatan> list = this.getAllByKegiatan(id);
    for (TahunKegiatan kill : list) {
      this.delete(kill);
    }
  }
  
  public void batchDeleteByTahun (Long id){
    List<TahunKegiatan> list = this.getAllByTahun(id);
    for (TahunKegiatan kill : list) {
      this.delete(kill);
    }
  }
  
  public void update(TahunKegiatan tk) {
    String query = "update TahunKegiatan set "
            + "lokasi= :lokasi, "
            + "volume= :volume, "
            + "sasaran_wanita= :sw, "
            + "sasaran_pria= :sp, "
            + "sasaran_rtm= :sr, "
            + "biaya= :biaya, "
            + "waktu= :waktu, "
            + "mulai= :mulai, "
            + "selesai= :selesai, "
            + "pelaksana= :pelaksana, "
            + "pola_pelaksanaan= :pk "
            + "sumberdana= :sumberdana, "
            + "where id_kegiatan= :kid and id_tahun= :tid";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("lokasi", tk.getLokasi())
            .setParameter("volume", tk.getVolume())
            .setParameter("sw", tk.getSaswan())
            .setParameter("sp", tk.getSaspri())
            .setParameter("sr", tk.getSasrtm())
            .setParameter("biaya", tk.getBiaya())
            .setParameter("waktu", tk.getWaktu())
            .setParameter("mulai", tk.getMulai())
            .setParameter("selesai", tk.getSelesai())
            .setParameter("pelaksana", tk.getPelaksana())
            .setParameter("pk", tk.getPk())
            .setParameter("sumberdana", tk.getSumberdana())
            .setParameter("kid", tk.getKegiatan().getId())
            .setParameter("tid", tk.getTp().getId())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
}
