/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.photoshare.fmi.photoshare.rest.service;

import com.photoshare.fmi.photoshare.ejb.ImageFacadeLocal;
import com.photoshare.fmi.photoshare.ejb.UserFacadeLocal;
import com.photoshare.fmi.photoshare.entity.Image;
import com.photoshare.fmi.photoshare.entity.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
/**
 *
 * @author killer
 */

@Path("image")
public class ImageFacadeREST  {
    @PersistenceContext(unitName = "com.photoshare.fmi_photoshare_war_1.0PU")
    private EntityManager em;
    
    @Context HttpServletRequest request;
    
    @EJB
    private ImageFacadeLocal imageFacade;
    @EJB
    private UserFacadeLocal userFacade;

    

    @POST
    @Path("create")
    @Consumes("multipart/form-data")
    public Response create(MultipartFormDataInput form) {
        //super.create(entity);
        String FILE_PATH = "/home/killer/temprest/";
        String filename = "";
        Map<String, List<InputPart>> uploadForm = form.getFormDataMap();
        
        String fileName;
        String username;
        String description;
        
        try {
            fileName = uploadForm.get("fileName").get(0).getBodyAsString();
            username = request.getUserPrincipal().getName();
            description = uploadForm.get("description").get(0).getBodyAsString();
            Image entity = new Image();
            entity.setDescription(description);
            entity.setUserId(userFacade.findUserByUserName(username));
            entity.setFileName(fileName);
            
            imageFacade.create(entity);
            
            List<InputPart> inputParts = uploadForm.get("fileUpload");
            
             for (InputPart inputPart : inputParts)
        {
            try
            {
                @SuppressWarnings("unused")
                MultivaluedMap<String, String> headerParts = inputPart.getHeaders();
                 
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                 
                byte[] bytes = IOUtils.toByteArray(inputStream);
                // constructs upload file path
                fileName = FILE_PATH + fileName;
                writeFile(bytes, fileName);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return Response.status(200).entity("Uploaded file name : "+ fileName).build();
        } catch (IOException ex) {
            Logger.getLogger(ImageFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(200).entity("Failuer").build();
        }
     
        
    }
    

    
    @GET
    @Path("test")
    public String getstr(){
        
        return "pingg";
    }
    
    private void writeFile(byte[] content, String filename) throws IOException
    {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();
    }

   

    @GET
    @Produces({"application/json"})
    public List<Image> findAll() {
        //return super.findAll();
        return imageFacade.findAll();
    }


    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        //return String.valueOf(super.count());
        return String.valueOf(imageFacade.count());
    }


    
}
