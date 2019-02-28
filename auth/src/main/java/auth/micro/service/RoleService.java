/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth.micro.service;

import auth.micro.config.HibernateUtil;
import auth.micro.model.Role;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author bianza
 */
public class RoleService {
    
    public Role save(Role role) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.save(role);
    session.getTransaction().commit();
    session.close();
    
    return role;
    }
    
    public List<Role> getAll() {
    String query = "from Role";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Role> list = (List<Role>) session.createQuery(
        query).list();
    session.getTransaction().commit();
    session.close();
    return list;
  }
    
   public List<Role> findById(Integer id) {
    String query = "from Role where id_role=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<Role> role = (List<Role>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return role;
  }
   
   public void delete(Role role) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    UserService us = new UserService();
    us.batchDeleteByRole(role.getId());
    
    session.delete(role);
    session.getTransaction().commit();
    session.close();
  }
}