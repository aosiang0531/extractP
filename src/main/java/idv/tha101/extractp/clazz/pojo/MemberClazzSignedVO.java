package idv.tha101.extractp.clazz.pojo;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
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
@Accessors(chain = true, prefix = "class_")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "member_class_signed")
public class MemberClazzSignedVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer class_member_id;
	
	@Column(name="member_id")
	private Integer class_members_id;
	
	private Integer class_id;
	
	@Column(name ="member_class_signed_status")
	private String class_member_class_signed_status;
	
	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name ="member_class_signed_created_time")
	private Timestamp class_member_class_created_time;
	
	@LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name ="member_class_signed_last_modified_time")
	private Timestamp class_signed_last_modified_time;
	
	@LastModifiedBy
	@Column(name ="member_class_signed_last_modified")
	private String class_signed_last_modified;
	
	
}
