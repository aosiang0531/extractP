package idv.tha101.extractp.web.pojo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import idv.tha101.extractp.shop.pojo.ProductVO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Accessors(chain = true, prefix = "order_detail_")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "ORDER_DETAIL")
public class OrderDetailVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer order_detail_id;
	@Column(name = "order_id", insertable = false, updatable = false)
	private Integer orderId;
	private Integer order_detail_quantity;
	private Double order_product_price;

	public Integer getOrder_id() {
		return orderId;
	}

	public void setOrder_id(Integer order_id) {
		this.orderId = order_id;
	}

	public Double getPrice() {
		return order_product_price;
	}

	public void setPrice(Double order_product_price) {
		this.order_product_price = order_product_price;
	}

//	@ManyToOne
//	@JoinColumn(name = "order_id",insertable = false, updatable = false)
//	private OrderInfoVO orderInfoVO;
//	
//	@ManyToOne
//	@JoinColumn(name = "product_id",insertable = false, updatable = false)
//	private ProductVO productVO;
}
