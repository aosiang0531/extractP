package idv.tha101.extractp.web.pojo;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ORDER_INFO", catalog = "TEAM07")
public class OrderInfoVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer order_id;
	private Integer member_id;
	private String order_status;
	@Column(insertable = false, updatable = false)
	private Timestamp order_date;
	private String order_shipping_method;
	private Integer order_payment_amount;
	private Timestamp order_payment_duedate;
	private String order_payment_method;
	private String order_shipping_status;
	private String order_payment_status;
	@Column(insertable = false, updatable = false)
	private Timestamp order_created_time;
	private Timestamp order_last_modified_time;
	private String order_shipping_address;
	private String order_contact_number;
	private String order_shipping_name;
//	@OneToMany(mappedBy = "orderInfo")
//	private List<OrderDetail> orderDetail;

}
