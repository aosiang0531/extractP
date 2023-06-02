package idv.tha101.extractp.web.pojo;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true, prefix = "product_")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "PRODUCT")
public class ProductVO {
	
	@Transient
	private boolean product_successful;
	@Transient
	private String product_message;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer product_id;
	
	@Column(name = "category_id")
	private Integer category_id;
	
	@Column(name = "product_name")
	private String product_name;
	
	@Column(name = "product_image")
	private byte[] product_image;
	
	@Column(name = "product_spec")
	private String product_spec;
	
	@Column(name = "product_description")
	private String product_description;
	
	@Column(name = "product_price")
	private Integer product_price;
	
	@Column(name = "product_stock")
	private Integer product_stock;
	
	@Column(name = "product_sold_count")//, insertable = false
	private Integer product_sold_count;
	
	@Column(name = "product_status")//, insertable = false
//	private String product_status;
	private String productStatus;
	
	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	
	
	
	@Column(name = "product_created_date")
	@CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp product_created_date;
	
	@Column(name = "product_last_modified_date")
	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp product_last_modified_date;
	
	@Column(name = "product_created_by")
	@CreatedBy
	private String product_created_by;
	
	@Column(name = "product_last_modified_by")
	@LastModifiedBy
	private String product_last_modified_by;
	
	public Integer getCategory_id() {
		return category_id;
	}
	
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}


	
//	@ManyToOne
//	@JoinColumn(name = "category_id", insertable = false, updatable = false)
//	private ProductCategoryVO productCategoryVO;
//	
//	@OneToMany
//	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
//	private List<OrderDetailVO> orderDetailVOs;
}
