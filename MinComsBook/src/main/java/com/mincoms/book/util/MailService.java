package com.mincoms.book.util;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mincoms.test.TestApplicationContext;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;



@Service("mailService")
public class MailService {
    
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    
    /**
	 * 메일발ㅅ송 <br>
	 
	 * @author 김동훈
	 * @version 1.0, 2011. 9. 22.
	 * @param from 보내는 메일주소 
	 * @param recipients 받는 메일주소
	 * @param subject 받는 메일제목 
	 * @param body 받는 메일내용   
	 * @param userName 받는사람 이름  
	 * @return 
     * @throws MessagingException 
	 * @throws 
	 * @see 
	 */
    public void sendMail(String from, String to, String subject, String body, String userName)  {
    	MimeMessage mimeMessage = mailSender.createMimeMessage();
    			
	    try {
	    	  String mailSubject = subject;
	    	 // String mailContent = mailTemplate().replace("$subject$", subject);
	    	 // mailContent = mailContent.replace("$content$", body);
	    	//  mailContent = mailContent.replace("$userName$", userName);
	    	  
			  mimeMessage.setSubject(mailSubject, "euc-kr");
			  mimeMessage.setText(body, "euc-kr", "html");
			  mimeMessage.addRecipients(RecipientType.TO,  new InternetAddress[]{new InternetAddress(to)});		
			  mailSender.send(mimeMessage);
		   } catch (MessagingException e) {
			   e.printStackTrace();
		   }
	}
    /**
   	 * 메일발송 <br>
   	 
   	 * @author 김동훈
   	 * @version 1.0, 2011. 9. 22.
   	 * @param from 보내는 메일주소 
   	 * @param recipients 받는 메일주소
   	 * @param subject 받는 메일제목 
   	 * @param body 받는 메일내용   
   	 * @return 
        * @throws MessagingException 
   	 * @throws 
   	 * @see 
   	 */
       public void sendMail(String from, List<String> recipients, String subject, String body)  {
       	MimeMessage mimeMessage = mailSender.createMimeMessage();
       			
   	    try {
   	    	  String mailSubject = subject;
   	    	 
   			  mimeMessage.setSubject(mailSubject, "euc-kr");
   			  mimeMessage.setText(getNoticeContent("", subject, body), "euc-kr", "html");
   			  
   			  mimeMessage.setFrom(new InternetAddress(from));
   			  
   			  String[] recipientsArray = recipients.toArray(new String[recipients.size()]);
   			  
   			  InternetAddress[] addressTo = new InternetAddress[recipientsArray.length];
   			  for (int i = 0; i < recipientsArray.length; i++) {
   				  addressTo[i] = new InternetAddress(recipientsArray[i]);
   			  }
   			  mimeMessage.setRecipients(RecipientType.TO, addressTo);
   			  mailSender.send(mimeMessage);
   		   } catch (MessagingException e) {
   			   e.printStackTrace();
   		   }
   	}
       /**
      	 * 개별메일발송 <br>
      	 
      	 * @author 김동훈
      	 * @version 1.0, 2011. 9. 22.
      	 * @param from 보내는 메일주소 
      	 * @param recipients 받는 메일주소
      	 * @param subject 받는 메일제목 
      	 * @param body 받는 메일내용   
      	 * @param username 받는 시림 이름
      	 * @return 
           * @throws MessagingException 
      	 * @throws 
      	 * @see 
      	 */ 
    public void sendTextMail(String from, String to, String subject, String body,String username) {
    	  SimpleMailMessage message = new SimpleMailMessage();          
          message.setFrom(from);
          message.setTo(to);
          message.setSubject(subject);
          message.setText(getNoticeContent(username,subject,body));
          mailSender.send(message);
    }
   
    public String getNoticeContent(String userName, String subject, String body) {
		 Map model = new HashMap();
		 
         model.put("userName", userName);
         model.put("subject", subject);
         model.put("content", body);
	
		 String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/mincoms/book/util/MailNotice.html","UTF-8",model);
		 return text;
	}
    
   @Deprecated
   private String mailTemplate(){
	   StringBuffer sb = new StringBuffer();
	   sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html> <style type='text/css'> </style> <body> <div style=\"margin: 0; padding: 0; background: #ffffff\"> <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" style=\"background-color: #dddddd\"> <tbody> <tr> <td> <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"670\" style=\"border: 0px; border-left: 1px solid #cccccc; border-right: 1px solid #cccccc; background-color: #ffffff; background-color: #dddddd\"> <tbody> <tr> <td> <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"670\" style=\"background-color: #f2f2f2; table-layout: fixed\"> <tbody> <tr> <td style=\"width: 19px; min-height: 77px\">&nbsp;</td> <td width=\"9\">&nbsp;</td> <td width=\"10\" height=\"77\">&nbsp;</td> <td width=\"458\" height=\"77\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; color: #333333\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> <tbody> <tr> <td style=\"font-size: 14px; font-weight: bold; color: #000000\"> $userName$  </td> </tr> <tr> <td style=\"font-size: 14px; color: #666666\">민커뮤니케이션에서 전해 드립니다 </td> </tr> </tbody> </table> </td> <td width=\"10\" height=\"77\">&nbsp;</td> <td width=\"32\" style=\"text-align: right\"> </td> <td style=\"background-color: #f2f2f2; table-layout: fixed; width: 85px\"></td> </tr> <tr> <td style=\"border-top: 1px solid #dddddd; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; background-color: #ffffff\">&nbsp;</td> <td style=\"border-top: 1px solid #dddddd; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; background-color: #ffffff\">&nbsp;</td> <td height=\"17\" style=\"border-top: 1px solid #dddddd; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; background-color: #ffffff\"></td> <td height=\"17\" colspan=\"4\" style=\"border-top: 1px solid #dddddd; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; background-color: #ffffff\">&nbsp;</td> </tr> </tbody> </table></td> <td rowspan=\"3\"></td> </tr> <tr> <td style=\"background: #ffffff\"> <table width=\"670\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; background-color: #ffffff\"> <tbody> <tr> <td> <table width=\"500\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 18px; line-height: 18px; font-weight: bold; color: #333\"> <tbody> <tr> <td>$subject$</td> </tr> <tr height=\"14\" style=\"background-color: white\"></tr> </tbody> </table> <hr width=\"520\" height=\"1\" style=\"border: 0; margin: 0 auto; color: #e8e8e8; background-color: #e8e8e8; min-height: 1px\"> <table width=\"500\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color: white\"> <tbody> <tr> <td width=\"500\" height=\"20\"></td> </tr> </tbody> </table> <table width=\"500\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; background-color: white; table-layout: fixed; overflow: hidden\"> <tbody> <tr> <td>$content$</td> </tr> <tr valign=\"top\"> <td height=\"10\" style=\"background-color: white\"></td> </tr> </tbody> </table> <table width=\"500\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color: white\"> <tbody> <tr> <td width=\"500\" height=\"20\"></td> </tr> </tbody> </table> <hr width=\"520\" height=\"1\" style=\"border: 0; margin: 0 auto; color: #e8e8e8; background-color: #e8e8e8; min-height: 1px\"> <table width=\"500\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color: white\"> <tbody> <tr> <td width=\"500\" height=\"20\"></td> </tr> </tbody> </table> <table width=\"670\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color: white\"> <tbody> <tr> <td width=\"670\" height=\"30\"></td> </tr> </tbody> </table> </td> </tr> <tr> <td> <table bgcolor=\"#eeeeee\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"670\" style=\"background-color: #eeeeee; background-repeat: repeat-x; border-top-color: #dddddd; border-top-style: solid; border-top-width: 1px\"> <tbody> <tr> <td colspan=\"4\" height=\"28\"></td> </tr> <tr> <td style=\"width: 85px\"></td> <td style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 12px; line-height: 17px; color: #777; text-shadow: 0 1px 0 #ffffff\"> <div> <a href=\"http://www.mincoms.com\" style=\"border: none; color: #0084b4; text-decoration: none\" target=\"_blank\">MinCommunications</a> </div> <div> <a href=\"#13a435dcb1e75ada_\" style=\"border: none; color: #0084b4; text-decoration: none; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; text-decoration: none; font-size: 11px; line-height: 17px; color: #999999; text-shadow: 0 1px 0 #ffffff\"> <pan>2012 Mincoms, Inc. All rights reserved</span> </a> </div> </td> <td style=\"width: 85px\"></td> </tr> <tr> <td colspan=\"3\" height=\"25\"></td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> <div class=\"yj6qo\"></div> <div class=\"adL\"></div> </div> </body> </html>");
	   return sb.toString();
   }
   
}

