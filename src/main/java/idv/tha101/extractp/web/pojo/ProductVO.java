package idv.tha101.extractp.web.pojo;

import java.sql.Timestamp;
import java.util.List;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Accessors(chain = true, prefix = "product")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "PRODUCT")
public class ProductVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_image")
	private byte[] productImage;
	
	@Column(name = "product_spec")
	private String productSpec;
	
	@Column(name = "product_description")
	private String productDescription;
	
	@Column(name = "product_price")
	private Integer productPrice;
	
	@Column(name = "product_stock")
	private Integer productStock;
	
	@Column(name = "product_sold_count", insertable = false)
	private Integer productSoldCount;
	
	@Column(name = "product_status", insertable = false)
	private String productStatus;
	
	@Column(name = "product_created_date")
	@CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp productCreatedDate;
	
	@Column(name = "product_last_modified_date")
	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp productLastModifiedDate;
	
	@Column(name = "product_created_by")
	@CreatedBy
	private Integer productCreatedBy;
	
	@Column(name = "product_last_modified_by")
	@LastModifiedBy
	private Integer productLastModifiedBy;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	@ManyToOne
	@JoinColumn(name = "category_id",
	insertable = false, updatable = false)
	private ProductCategoryVO productCategory;
	
	@OneToMany
	@JoinColumn(name = "product_id",
	referencedColumnName = "product_id")
	private List<OrderDetailVO> orderDetails;
}