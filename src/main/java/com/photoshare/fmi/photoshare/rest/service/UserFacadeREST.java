/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.photoshare.fmi.photoshare.rest.service;

import com.photoshare.fmi.photoshare.ejb.UserFacadeLocal;
import com.photoshare.fmi.photoshare.entity.User;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author killer
 */

@Path("user")
public class UserFacadeREST {
    @PersistenceContext(unitName = "com.photoshare.fmi_photoshare_war_1.0PU")
    private EntityManager em;
    
    @EJB
    private UserFacadeLocal userFacade;

    

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(User entity) {
        userFacade.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, User entity) {
       // super.edit(entity);
        userFacade.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        //super.remove(super.find(id));
        userFacade.remove(userFacade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public User find(@PathParam("id") Integer id) {
       // return super.find(id);
        return userFacade.find(id);
    }

    @GET
    @Produces({"application/json"})
    public List<User> findAll() {
        //return super.findAll();
        return userFacade.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
       // return super.findRange(new int[]{from, to});
        return userFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(userFacade.count());
    }

 
    
}
