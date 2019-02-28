/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth.micro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author bianza
 */
@Entity
@Table(name="user")
public class User {
 
    @ManyToOne
    @JoinColumn(name="kode_pos",
                nullable = false, updatable = false)
    @NotNull
    private Desa desa;
    
    @ManyToOne
    @JoinColumn(name="id_role",
                nullable = false, updatable = false)
    @NotNull
    private Role role;
     
    @Id
    @Column(name = "username", nullable = false)
    private String username;
    
    
    @Column(name = "password", nullable = false)
    private String pw;
    
    @Column(name = "token")
    private String token;
    

    public User(Desa desa, Role role, String username, String pw, String token) {
        this.desa = desa;
        this.role = role;
        this.username = username;
        this.pw = pw;
        this.token = token;
    }

    public User() {
    }

    public Desa getDesa() {
        return desa;
    }

    public void setDesa(Desa desa) {
        this.desa = desa;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }    

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}
