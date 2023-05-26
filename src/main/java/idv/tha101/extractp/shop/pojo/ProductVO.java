package idv.tha101.extractp.shop.pojo;

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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer product_id;
	@Column
	private Integer category_id;
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	@Column
	private String product_name;
	@Column
	private byte[] product_image;
	@Column
	private String product_spec;
	@Column
	private String product_description;
	@Column
	private Integer product_price;
	@Column
	private Integer product_stock;
	@Column(insertable = false)
	private Integer product_sold_count;
	@Column(insertable = false)
	private String product_status;
	
	@Column
	@CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp product_created_date;
	
	@Column
	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp product_last_modified_date;
	
	@Column
	@CreatedBy
	private Integer product_created_by;
	
	@Column
	@LastModifiedBy
	private Integer product_last_modified_by;
	
//	@ManyToOne
//	@JoinColumn(name = "category_id",
//	insertable = false, updatable = false)
//	private ProductCategory productCategory;
//	
//	@OneToMany
//	@JoinColumn(name = "product_id",
//	referencedColumnName = "product_id")
//	private List<OrderDetail> orderDetails;
}