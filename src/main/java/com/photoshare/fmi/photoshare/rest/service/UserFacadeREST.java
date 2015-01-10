/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.photoshare.fmi.photoshare.rest.service;

import com.photoshare.fmi.photoshare.ejb.UserFacadeLocal;
import com.photoshare.fmi.photoshare.entity.User;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author killer
 */

@Path("user")
public class UserFacadeREST {
    @PersistenceContext(unitName = "com.photoshare.fmi_photoshare_war_1.0PU")
    private EntityManager em;
    
    @Context
    private HttpServletRequest request;
    
    @EJB
    private UserFacadeLocal userFacade;

    
    
    
    

    @POST
    @Path("create")
    @Consumes({"application/json"})
    public void create(User entity) {
        userFacade.create(entity);
    }

    

    @POST
    @Path("login")
    public Response login() {
       return Response.status(200).entity("Login successful").build();
    }
 
}
