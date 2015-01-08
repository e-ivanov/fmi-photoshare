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
import java.io.FileInputStream;
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
public class ImageFacadeREST {

    @PersistenceContext(unitName = "com.photoshare.fmi_photoshare_war_1.0PU")
    private EntityManager em;

    @Context
    HttpServletRequest request;

    @EJB
    private ImageFacadeLocal imageFacade;
    @EJB
    private UserFacadeLocal userFacade;
    
    private final String FILE_PATH = "/home/killer/temprest/";

    @POST
    @Path("create")
    @Consumes("multipart/form-data")
    public Response create(MultipartFormDataInput form) {
        
        String filename = "";
        Map<String, List<InputPart>> uploadForm = form.getFormDataMap();
        String FINAL_PATH = "";
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
            
            
            
            
            List<InputPart> inputParts = uploadForm.get("fileUpload");
            
             for (InputPart inputPart : inputParts)
        {
            try
            {
                @SuppressWarnings("unused")
                MultivaluedMap<String, String> headerParts = inputPart.getHeaders();
                fileName = getOriginalFileName(headerParts);
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                 
                byte[] bytes = IOUtils.toByteArray(inputStream);
                // constructs upload file path
                FINAL_PATH = FILE_PATH+fileName;
                writeFile(bytes, FINAL_PATH);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
             entity.setFileName(fileName);
             imageFacade.create(entity);
        return Response.status(200).entity("Uploaded file name : "+ fileName).build();
        } catch (IOException ex) {
            Logger.getLogger(ImageFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(409).entity("An error has occured while uploading file").build();
        }
     

    }

    
    @GET
    @Produces("image/*")
    @Path("getfile/{filepath}")
    public InputStream getFileInputStream(@PathParam("filepath") String filename)throws Exception{
    FileInputStream fileIs = new FileInputStream(FILE_PATH+filename);
    return fileIs;
  }
    
    @GET
    @Path("test")
    public String getstr() {

        return "pingg";
    }

    private void writeFile(byte[] content, String filename) throws IOException {
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

    private String getOriginalFileName(MultivaluedMap<String, String> headers) {

        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");

        for (String name : contentDispositionHeader) {

            if ((name.trim().startsWith("filename"))) {
                String[] tmp = name.split("=");
                String fileName = tmp[1].trim().replaceAll("\"", "");
                return fileName;
            }
        }
        return "randomName";

    }

}
