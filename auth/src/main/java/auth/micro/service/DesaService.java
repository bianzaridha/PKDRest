/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth.micro.service;


import auth.micro.config.HibernateUtil;
import auth.micro.model.Desa;
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
  
  public List<Desa> findById(String id) {
    query = "from Desa where kode_pos=:id";
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
    
    String url1 = "http://localhost:8100/pelaksanaan/desa/" + desa.getId();
    RestTemplate rt = new RestTemplate();
    rt.delete(url1);
    
    UserService us = new UserService();
    us.batchDeleteByDesa(desa.getId());
    
    session.delete(desa);
    session.getTransaction().commit();
    session.close();
  }
  
  
  public void update(Desa desa) {
    query = "update Desa set nama_desa= :nama,"
            + "kecamatan= :kec,"
            + "kabupaten_kota= :kabkot,"
            + "provinsi= :prov,"
            + "luas_wilayah= :lw,"
            + "jumlah_penduduk= :jp"
            + " where kode_pos=:kode_pos";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("nama", desa.getNama())
            .setParameter("kec", desa.getKecamatan())
            .setParameter("kabkot", desa.getKabkot())
            .setParameter("prov", desa.getProvinsi())
            .setParameter("lw", desa.getLwilayah())
            .setParameter("jp", desa.getJp())
            .setParameter("kode_pos", desa.getId())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
}