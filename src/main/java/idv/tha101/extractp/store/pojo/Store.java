package idv.tha101.extractp.store.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer memberId;
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
