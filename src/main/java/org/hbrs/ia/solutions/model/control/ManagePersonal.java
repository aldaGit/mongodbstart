package org.hbrs.ia.solutions.model.control;
import org.hbrs.ia.solutions.model.SalesMan;
import org.hbrs.ia.solutions.model.SocialPerformanceRecord;

import java.util.List;

/**
 * Code lines are commented for suppressing compile errors.
 */
public interface ManagePersonal {

    public void createSalesMan( SalesMan record );

    void deleteSalesMan(SalesMan salesMan);

    public void addSocialPerformanceRecord(SocialPerformanceRecord record , SalesMan salesMan );
    // Remark: an SocialPerformanceRecord corresponds to part B of a bonus sheet

    // newly added!
    void deleteSocialPerformanceRecord(SocialPerformanceRecord record, SalesMan salesMan);

    public SalesMan readSalesMan(int sid );

    void updateSalesMan(SalesMan salesMan, SalesMan newSalesMan);

    public List<SalesMan> readAllSalesMen();

    public List<SocialPerformanceRecord> readSocialPerformanceRecord( SalesMan salesMan );
    // Remark: How do you integrate the year?

}
