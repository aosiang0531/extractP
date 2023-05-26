package idv.tha101.extractp.shop.pojo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Accessors(chain = true, prefix = "order_detail_")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "ORDER_DETAIL")
public class OrderDetailVO{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer order_detail_id;
	@Column
	private Integer order_id;
	@Column
	private Integer product_id;

	@Column
	private Integer order_detail_quantity;
	@Column
	private Double order_product_price;
	
//	@ManyToOne
//	@JoinColumn(name = "product_id",
//	insertable = false, updatable = false)
//	private Product product;
	
//	@ManyToOne
//	@JoinColumn(name = "order_id",
//	insertable = false, updatable = false)
//	private Order order;
	
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Double getOrder_product_price() {
		return order_product_price;
	}
	public void setOrder_product_price(Double order_product_price) {
		this.order_product_price = order_product_price;
	}
	
	
	
	
	
	
}
