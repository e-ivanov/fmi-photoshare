/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.photoshare.fmi.photoshare.ejb;

import com.photoshare.fmi.photoshare.entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
