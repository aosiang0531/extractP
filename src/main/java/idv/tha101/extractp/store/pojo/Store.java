package idv.tha101.extractp.store.pojo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeId;
    private String storeTax;
    private String memberId;
    private String storeName;
    private String storeInfo;
    private String storeAddress;
    private String storeTime;
    private String storePhone;
    private byte[] storeOrder;
    private String storeWebsite;
    @Column(insertable = false)
    private Timestamp storeCreatedTime;
    @Column(insertable = false)
    private Timestamp storeLastModifiedTime;
    private String storeLastModifiedMember;

}
