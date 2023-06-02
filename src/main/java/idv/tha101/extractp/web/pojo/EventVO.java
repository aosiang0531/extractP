package idv.tha101.extractp.web.pojo;

import java.sql.Date;
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

@Data // 用於自動生成類別的常用方法，例如 getter、setter、equals、hashCode 和 toString。
@NoArgsConstructor // 自動生成無參數的預設建構子。
@AllArgsConstructor // 自動生成包含所有屬性的全參數建構子。
@Builder // 自動為該類別生成一個內部的建造者類別（Builder Class），該建造者類別提供了一個流暢的介面來建構該類別的物件
@Accessors(chain = true, prefix = "event_") // 生成 getter 和 setter 方法時設置鏈式調用（chain call）和屬性前綴（prefix）
@Entity
@EntityListeners(AuditingEntityListener.class) // 表示它將使用 Spring Data JPA 的審計功能，以自動填充創建日期、最後修改日期和使用者等相關屬性。
@DynamicInsert
@DynamicUpdate // @DynamicInsert 和 @DynamicUpdate 註解表示在執行 INSERT 和 UPDATE
				// 操作時，只生成非空值的欄位，忽略具有默認值或為空的屬性。
@Table(name = "event") // 設定了該實體對應的資料庫表格名稱為 "class"。
public class EventVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // @Id 和 @GeneratedValue表示它是主鍵並且將由資料庫自動生成
	private Integer event_id;
	
	@Column(name = "member_id")
	private Integer event_member_id;
	
	private String event_name;

	private String event_description;

	private Date event_date;

	private String event_time;

	private String event_period_of_time;

	private String event_location;

	private Integer event_member_count;

	private Integer event_member_max;

	private Integer event_member_min;

	private Date event_start_date;

	private Date event_end_date;

	private String event_image;

	private String event_status = "報名中";

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp event_created_time;

	@LastModifiedDate // @CreatedDate 和 @LastModifiedDate 註解標註了相應的屬性，用於表示創建日期和最後修改日期。
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp event_last_modified_time;

	@LastModifiedBy // @CreatedBy 和 @LastModifiedBy 註解標註了相應的屬性，用於表示創建者和最後修改者。
	private String event_last_modified_member;
}
