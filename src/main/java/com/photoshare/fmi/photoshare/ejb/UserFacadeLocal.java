/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.photoshare.fmi.photoshare.ejb;

import com.photoshare.fmi.photoshare.entity.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author killer
 */
@Local
public interface UserFacadeLocal {

    void create(User user);

    
    User findUserByUserName(String username);

  
    
}
