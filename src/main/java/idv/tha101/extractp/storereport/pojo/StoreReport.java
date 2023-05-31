package idv.tha101.extractp.storereport.pojo;

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
public class StoreReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeReportId;
    private Integer memberId;
    private String storeTax;
    private String storeReportContent;
    private Timestamp storeReportCreatedTime;
    private String storeReportStatus;
    private Timestamp storeReportLastModifiedTime;
    private String storeReportLastModifiedMember;


}
