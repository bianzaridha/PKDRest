package desa.micro.service;


import desa.micro.config.HibernateUtil;
import desa.micro.model.Desa;
import java.util.List;
import org.hibernate.Session;
import org.springframework.web.client.RestTemplate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bianza
 */
  public class DesaService {
    
    private String query1;
    private String query2;
    private String query;
    
    public Desa save(Desa desa) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.save(desa);
    session.getTransaction().commit();
    session.close();
    
    return desa;
    }
    
  public void batchSave(List<Desa> list) {
    for (Desa live : list) {
      this.save(live);
    }
  }
  
  public List<Desa> getAll() {
      query = "from Desa";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Desa> list = (List<Desa>) session.createQuery(
        query).list();
    session.getTransaction().commit();
    session.close();
    return list;
  }
  
  public List<Desa> findById(Long id) {
    query = "from Desa where kode_desa=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Desa> desa = (List<Desa>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return desa;
  }
  
  public void delete(Desa desa) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    String url = "http://localhost:8300/rpjm/desa/" + desa.getId();
    RestTemplate rt = new RestTemplate();
    rt.delete(url);
//    RpjmService rs = new RpjmService();
//    rs.batchDeleteByDesa(desa.getId());
    
    session.delete(desa);
    session.getTransaction().commit();
    session.close();
  }
  
  public void update(Desa desa) {
    query = "update Desa set nama_desa= :nama,"
            + "kode_pos= :kp,"
            + "kecamatan= :kec,"
            + "kabupaten_kota= :kabkot,"
            + "provinsi= :prov,"
            + "luas_wilayah= :lw,"
            + "jumlah_penduduk= :jp"
            + " where kode_desa=:kode_desa";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("nama", desa.getNama())
            .setParameter("kp", desa.getKodepos())
            .setParameter("kec", desa.getKecamatan())
            .setParameter("kabkot", desa.getKabkot())
            .setParameter("prov", desa.getProvinsi())
            .setParameter("lw", desa.getLwilayah())
            .setParameter("jp", desa.getJp())
            .setParameter("kode_desa", desa.getId())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
}