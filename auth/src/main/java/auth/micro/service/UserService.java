/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth.micro.service;

import auth.micro.config.HibernateUtil;
import auth.micro.model.User;
import auth.micro.proxy.LoginProxy;
import auth.micro.proxy.UserProxy;
import java.util.List;
import org.hibernate.Session;
import java.security.*;
import java.util.Random;

/**
 *
 * @author bianza
 */
public class UserService {
    
     Random rand = new Random();
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    public User save(User user) {
        try{
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(user.getPw().getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            user.setPw(sb.toString());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(user);
        session.getTransaction().commit();
        session.close();

        return user;
    }
    
   public List<User> findByUser(String id) {
    String query = "from User where username=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<User> user = (List<User>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    
   return user;
  }
   
   public User Login(LoginProxy up){
       List<User> usr = this.findByUser(up.getUsername());
        if(usr.size() == 1){
            User user = usr.get(0);
             try{
                // Create MessageDigest instance for MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                //Add password bytes to digest
                md.update(up.getPassword().getBytes());
                //Get the hash's bytes
                byte[] bytes = md.digest();
                //This bytes[] has bytes in decimal format;
                //Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< bytes.length ;i++)
                {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                //Get complete hashed password in hex format
                up.setPassword(sb.toString());
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
             
             if(user.getPw().equals(up.getPassword())){
                 StringBuilder builder = new StringBuilder();
                 Integer count = 10;
                 while (count-- != 0) {
                    int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
                    builder.append(ALPHA_NUMERIC_STRING.charAt(character));
                 }
                 String token = builder.toString();
                 user.setToken(token);
                 this.updateLogin(user);
                 
                return user;   
              }else{
                 return null; 
              }
        }else{
            return null;
        }
   }
   
   public List<User> findByToken(String token) {
    String query = "from User where token=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<User> user = (List<User>) session.createQuery(
        query).setParameter("id", token).list();
    session.getTransaction().commit();
    session.close();
    
   return user;
  }
   
   public List<User> getAll() {
    String query = "from User";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<User> user = (List<User>) session.createQuery(
        query).list();
    session.getTransaction().commit();
    session.close();
    return user;
  }
   
   public List<User> getAllByDesa(String id) {
    String query = "from User where kode_pos=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<User> user = (List<User>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return user;
  }
   
   public List<User> getAllByRole(Integer id) {
    String query = "from User where id_role=:id";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    List<User> user = (List<User>) session.createQuery(
        query).setParameter("id", id).list();
    session.getTransaction().commit();
    session.close();
    return user;
  }
   
    public void delete(User user) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    session.delete(user);
    session.getTransaction().commit();
    session.close();
  }
   
   public void batchDeleteByDesa(String id){
    List<User> list = this.getAllByDesa(id);
    for (User kill : list) {
      this.delete(kill);
    }
  }
   
  public void batchDeleteByRole(Integer id){
    List<User> list = this.getAllByRole(id);
    for (User kill : list) {
      this.delete(kill);
    }
  }
  
  public void logout(User user) {
    String query = "update User set password= :pw, "
            + "token= :token "
            + "where username=:username";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
      
   StringBuilder builder = new StringBuilder();
   Integer count = 10;
   while (count-- != 0) {
       int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
       builder.append(ALPHA_NUMERIC_STRING.charAt(character));
   }
   String token = builder.toString();
   user.setToken(token);
   
   @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("pw", user.getPw())
            .setParameter("token", user.getToken())
            .setParameter("username", user.getUsername())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  
  }
   
   public void update(User user) {
    String query = "update User set password= :pw, "
            + "token= :token "
            + "where username=:username";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    try{
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(user.getPw().getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            user.setPw(sb.toString());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("pw", user.getPw())
            .setParameter("token", user.getToken())
            .setParameter("username", user.getUsername())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
   
   public void updateLogin(User user) {
    String query = "update User set password= :pw, "
            + "token= :token "
            + "where username=:username";
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    @SuppressWarnings("unchecked")
    int result = session.createQuery(
        query)
            .setParameter("pw", user.getPw())
            .setParameter("token", user.getToken())
            .setParameter("username", user.getUsername())
            .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
    
}
