/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.photoshare.fmi.photoshare.ejb;

import com.photoshare.fmi.photoshare.entity.User;
import com.photoshare.fmi.photoshare.entity.UserRoles;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.picketlink.idm.credential.util.BCrypt;


/**
 *
 * @author killer
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal{
    @PersistenceContext(unitName = "com.photoshare.fmi_photoshare_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @RolesAllowed("admin")
    @Override
    public List<User> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(User entity) {
        
        String hashedPassword = BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(12));
        entity.setPassword(hashedPassword);
        getEntityManager().merge(entity);
    }
    
    
    

    @Override
    public void create(User entity) {
        String hashedPassword = BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(12));
        entity.setPassword(hashedPassword);
        getEntityManager().persist(entity);
        getEntityManager().flush();
        UserRoles userRole = new UserRoles(entity.getUsername(), "user");
        getEntityManager().persist(userRole);
        getEntityManager().flush();
    }
    
    
    

    
    
    
}
