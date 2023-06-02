package idv.tha101.extractp.ad.pojo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adId;
    private Integer memberId;
    private Date adStartDate;
    private Date adEndDate;
    private Integer adSpend;
    private String adField;
    private String adTitle;
    private String adDeliverStatus;
    private String adReviewStatus;
    private byte[] adImage;
    private String adAdminDescription;
    private String adName;
    @Column(insertable = false)
    private Timestamp adCreatedTime;
    @Column(insertable = false)
    private Timestamp adLastModifiedTime;
    private String adLastModifiedMember;
    private String adUrl;
}
