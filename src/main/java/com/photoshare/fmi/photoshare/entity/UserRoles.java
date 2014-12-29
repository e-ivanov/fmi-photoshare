/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.photoshare.fmi.photoshare.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author killer
 */
@Entity
@Table(name = "UserRoles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserRoles.findAll", query = "SELECT u FROM UserRoles u"),
    @NamedQuery(name = "UserRoles.findByUsername", query = "SELECT u FROM UserRoles u WHERE u.userRolesPK.username = :username"),
    @NamedQuery(name = "UserRoles.findByRole", query = "SELECT u FROM UserRoles u WHERE u.userRolesPK.role = :role")})
public class UserRoles implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserRolesPK userRolesPK;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public UserRoles() {
    }

    public UserRoles(UserRolesPK userRolesPK) {
        this.userRolesPK = userRolesPK;
    }

    public UserRoles(String username, String role) {
        this.userRolesPK = new UserRolesPK(username, role);
    }

    public UserRolesPK getUserRolesPK() {
        return userRolesPK;
    }

    public void setUserRolesPK(UserRolesPK userRolesPK) {
        this.userRolesPK = userRolesPK;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userRolesPK != null ? userRolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRoles)) {
            return false;
        }
        UserRoles other = (UserRoles) object;
        if ((this.userRolesPK == null && other.userRolesPK != null) || (this.userRolesPK != null && !this.userRolesPK.equals(other.userRolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.photoshare.fmi.photoshare.entity.UserRoles[ userRolesPK=" + userRolesPK + " ]";
    }
    
}
