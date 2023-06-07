package idv.tha101.extractp.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

//Class
public class MemberEmailDetails {

 // Class data members
 private String recipient;
 private String msgBody;
 private String subject;
}