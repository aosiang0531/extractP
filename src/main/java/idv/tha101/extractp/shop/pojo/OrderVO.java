package idv.tha101.extractp.shop.pojo;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
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
@Accessors(chain = true, prefix = "order_")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "ORDER_INFO")
public class OrderVO{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private Integer order_id;
	@Column
	private Integer member_id;
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	@Column(name = "order_status")
	private String orderStatus;
	@Column
	@CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp order_created_date;
	@Column
	private String order_shipping_method;
	@Column
	private Integer order_payment_amount;
	@Column
	private Timestamp order_payment_duedate;
	@Column
	private String order_payment_method;
	@Column
	private String order_shipping_status;
	@Column
	private String order_payment_status;
	@Column
	private String order_shipping_address;
	@Column
	private String order_contact_number;
	@Column
	private String order_shipping_name;
	
//	@OneToMany
//	@JoinColumn(name = "order_id",
//	referencedColumnName = "order_id")
//	private List<OrderDetail> orderDetails;

    

}
