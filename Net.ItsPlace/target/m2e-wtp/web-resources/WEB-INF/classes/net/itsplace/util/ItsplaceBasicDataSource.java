package net.itsplace.util;

import org.apache.commons.dbcp.BasicDataSource;

public class ItsplaceBasicDataSource extends BasicDataSource {
   
    
    public void setUsername(String username){
    
         super.setUsername("faya");
    }
    
    public void setPassword(String password) {
       
        super.setPassword("qp2apdx");
    }
   
}