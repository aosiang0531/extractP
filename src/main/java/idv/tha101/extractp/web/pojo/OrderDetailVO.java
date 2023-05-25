package idv.tha101.extractp.web.pojo;

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
@Table(name = "ORDER_DETAIL", catalog = "TEAM07")
public class OrderDetailVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer order_detail_id;
	@Column(insertable = false, updatable = false)
	private Integer order_id;
	@Column(insertable = false, updatable = false)
	private Integer product_id;
	private Integer order_detail_quantity;
	private Double order_product_price;

//	@ManyToOne
//	@JoinColumn(name = "order_id",insertable = false, updatable = false)
//	private OrderInfo orderInfo;
}
