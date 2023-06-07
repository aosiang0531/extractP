package idv.tha101.extractp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.web.pojo.MemberEmailDetails;
import idv.tha101.extractp.web.service.MemberEmailService;

//Annotation
@RestController
@RequestMapping("member")
//Class
public class MemberEmailController {

 @Autowired private MemberEmailService emailService;

 // Sending a simple Email
 @PostMapping("/sendMail")
 public String
 sendMail(@RequestBody MemberEmailDetails details)
 {
     String status
         = emailService.sendSimpleMail(details);

     return status;
 }

}
