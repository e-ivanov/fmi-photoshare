/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.photoshare.fmi.photoshare.rest.service;

import com.photoshare.fmi.photoshare.ejb.ImageFacadeLocal;
import com.photoshare.fmi.photoshare.entity.Image;
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

@Path("image")
public class ImageFacadeREST  {
    @PersistenceContext(unitName = "com.photoshare.fmi_photoshare_war_1.0PU")
    private EntityManager em;
    
    @EJB
    private ImageFacadeLocal imageFacade;

    

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Image entity) {
        //super.create(entity);
        imageFacade.create(entity);
    }

    
    @GET
    @Path("test")
    public String getstr(){
        
        return "Ping";
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        //super.remove(super.find(id));
        imageFacade.remove(imageFacade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Image find(@PathParam("id") Integer id) {
       // return super.find(id);
        return imageFacade.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Image> findAll() {
        //return super.findAll();
        return imageFacade.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Image> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        //return super.findRange(new int[]{from, to});
        return imageFacade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        //return String.valueOf(super.count());
        return String.valueOf(imageFacade.count());
    }


    
}
