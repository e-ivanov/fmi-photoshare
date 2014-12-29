/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.photoshare.security;


import javax.security.auth.login.LoginException;

import org.jboss.security.auth.spi.DatabaseServerLoginModule;
import org.picketlink.idm.credential.util.BCrypt;

/**
 *
 * @author killer
 */
public class DBLoginModule extends DatabaseServerLoginModule {

    @Override
    protected boolean validatePassword(String inputPassword, String expectedPassword) {
       return BCrypt.checkpw(inputPassword, expectedPassword);
    }

    @Override
    protected String createPasswordHash(String username, String password, String digestOption) throws LoginException {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

}
