/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 *
 * @author Dhia
 */
public class ServiceMail {

  
    
    public static void sendMail(String to,String subject,String body) throws AddressException, MessagingException{
          
        try{
         String host ="smtp.gmail.com";
                String username = "techevents11@gmail.com";
                String password = "Techevent2019";
                String from = "TechEvents";
                boolean sessionDebug = false;
                
                Properties props = System.getProperties();
                
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.required", "true");
                
                java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                Session mailSession = Session.getDefaultInstance(props, null);
                mailSession.setDebug(sessionDebug);
                Message msg = new MimeMessage(mailSession);
                msg.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject); msg.setSentDate(new Date());
                msg.setText(body);
                
                Transport transport=mailSession.getTransport("smtp");
                transport.connect(host, username, password);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();
                System.out.println("message send successfully");
        }catch(MessagingException ex)
            {
                System.out.println(ex);
            }  
                
            }  
    
    

}
