/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itsplace.controller.admin;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class MyController {
    
    private boolean throwEx = true;
    
   
    
    /*
     * Invoked from the UI in this example project to
     * demonstrate the handling of two different Exception Types
     * Note that RuntimeException is specifically configured in the the 
     * application context (dispatcher-servlet.xml in this example)
     * 
     * Any sub class of RuntimeException or Exception is handled inhereted types.
     * Specifi Exceptions can thus be declared in the application context for specific handling.
     */
    @RequestMapping(value="/ex", method= RequestMethod.POST)
    public String doException( String execptionType ) throws Exception {
        if( throwEx ) {
            if( execptionType.equals("checked") )
                throw new Exception("This Exception was thrown intentionally");
            else
                throw new RuntimeException("This RuntimeException was thrown intentionally");
        }
        return "/index";
    }
    
    /*
     * Invoked from jQuery.load() in this example
     * The Exception is handled by the 
     * com.outbottle.support.ExceptionHandler class declared in the application context
     */
    @RequestMapping("/loadme")
    public String loadMe() throws Exception {
        if( throwEx )
            throw new Exception("thrown intentionally");
        else
            return "/loadme";
    }
    
    /*
     * Intentionally throws a NullPointerException to demonstrate
     * handling of specific exceptions at the controller level
     */
    @RequestMapping("/null")
    public String doNullPointerException() {
        if( throwEx ) {
            String ex = "I'm a NullPointerException handled in this Controller.";
            ex += "<br />If I were a different Exception type ";
            ex += "(excluding sub-types of NullPointerException), ";
            ex += "I would not be handled in this Controller";
            throw new NullPointerException(ex);
        }
        return "/index";
    }
    
    /*
     * This will be invoked when a NullPointerException
     * is thrown in this @Controller class.
     * It overrites the global handler declared in
     * the application context (dispatcher-servlet.xml in this example project)
     * 
     * NOTE THAT Multiple Exceptions can be specified as so:
     *  @ExceptionHandler( value={NullPointerException.class, IllegalArgumentException.class} )
     */
    @ExceptionHandler( value=NullPointerException.class )
    public String iHandleNullPointerExceptions(HttpServletRequest request, Exception e) {
        //do loads of interesting stuff to deal with the exception
        request.setAttribute("exception", e);
        return "/unchecked";
    }
}
