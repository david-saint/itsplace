package net.itsplace.util;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;



@Service("mailService")
public class MailService {
    
    @Autowired
    private MailSender mailSender;
    
    
    /**
	 * 메일발 <br>
	 *  
	 * <pre>
	 * Ex.		
	 * </pre>
	 * @author 김동훈
	 * @version 1.0, 2011. 9. 22.
	 * @param 보내는 메일주소 
	 * @param 받는 메일주소
	 * @param 받는 메일제목 
	 * @param 받는 메일내용   
	 * @return 
	 * @throws 
	 * @see 
	 */
    public void sendMail(String from, String to, String subject, String body) {
        
        SimpleMailMessage message = new SimpleMailMessage();
         
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        
    }
    
   
   
}

