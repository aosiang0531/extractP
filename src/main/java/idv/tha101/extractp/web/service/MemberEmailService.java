package idv.tha101.extractp.web.service;

import idv.tha101.extractp.web.pojo.MemberEmailDetails;

public interface MemberEmailService {
	 
    // Method
    // To send a simple email
    String sendSimpleMail(MemberEmailDetails details);
}
