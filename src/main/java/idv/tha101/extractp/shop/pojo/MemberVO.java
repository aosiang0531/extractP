package idv.tha101.extractp.shop.pojo;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true, prefix = "member")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "MEMBER")
public class MemberVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Integer memberId;
	
	@Column(name = "member_email")
	private String memberEmail;
	
	@Column(name = "member_name")
	private String memberName;
	
	@Column(name = "member_password")
	private String memberPassword;
	
	@Column(name = "member_phone")
	private String memberPhone;
	
	@Column(name = "member_identity")
	private String memberIdentity;
	
	@Column(name = "member_is_suspended")
	private Boolean memberIsSuspended;
	
	@Column(name = "member_image")
	private byte[] memberImage;
	
	@Column(name = "member_created_time")
	@CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp memberCreatedTime;
	
	@Column(name = "member_created_user")
	@CreatedBy
	private String memberCreatedUser;
	
	@Column(name = "member_last_modified_time")
	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp memberLastModifiedTime;
	

}
