package org.hbrs.ia.solutions.test;

import org.hbrs.ia.solutions.main.Main;
import org.hbrs.ia.solutions.model.*;
import org.hbrs.ia.solutions.model.SalesMan;
import org.hbrs.ia.solutions.model.SocialPerformanceRecord;
import org.hbrs.ia.solutions.model.control.ManagePersonal;
import org.hbrs.ia.solutions.model.control.ManagePersonalImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.hbrs.ia.solutions.test.builder.PerformanceRecordBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesManManagementTest {

    private ManagePersonal managePersonal;
    private Main main;

    @BeforeEach
    void setUp() {
        main = new Main();
        main.initDB();
        managePersonal = main.getManagePersonal();
    }

    @AfterEach
    void tearDown() {
        // Deletion of database (ok, a bit cumbersome ;-) No cast would be finer. Anyway: delete the test rubish
        ((ManagePersonalImpl) managePersonal).deleteCollection();

        // Close connection to database.
        main.closeConection();;
    }

    @Test
    public void insertAndReadSalesMan() {
        // Create and insert a SalesMan record
        SalesMan salesManSOLL = new SalesMan("Stefan" , "Mayer" , 123);
        managePersonal.createSalesMan(salesManSOLL);

        // Read the same
        SalesMan salesManIST = managePersonal.readSalesMan(123);
        assertEquals( salesManIST , salesManSOLL );
    }

    @Test
    public void insertAndDeleteManySalesMen() {
        // Create and insert a SalesMan record
        SalesMan salesManSOLL = new SalesMan("Stefan" , "Mayer" , 123);
        managePersonal.createSalesMan(salesManSOLL);

        // Create and insert a SalesMan record
        SalesMan salesManSOLL2 = new SalesMan("Torben" , "Minter" , 333);
        managePersonal.createSalesMan(salesManSOLL2);

        // Read the same, test of size only
        List<SalesMan> salesManList = managePersonal.readAllSalesMen();
        assertEquals( 2 , salesManList.size() );

        // Object Comparison
        List comparionList = new ArrayList();
        comparionList.add(salesManSOLL);
        comparionList.add(salesManSOLL2);
        assertArrayEquals( comparionList.toArray() , salesManList.toArray() );

        // Deletion of a saleman:
        managePersonal.deleteSalesMan(salesManSOLL2);
        salesManList = managePersonal.readAllSalesMen();
        assertEquals( 1 , salesManList.size() );
    }

    @Test
    public void insertAndDeleteRecordIntoSalesMenwithPerformanceRecords() {
        // Create and insert a SalesMan record
        SalesMan salesManSOLL = new SalesMan("Stefan" , "Mayer" , 123);
        managePersonal.createSalesMan(salesManSOLL);

        // Read the same
        SalesMan salesManIST = managePersonal.readSalesMan(123);
        assertEquals( salesManIST , salesManSOLL );

        SocialPerformanceRecord record1 =
            PerformanceRecordBuilder.please().createDefault().withYear("2021").done();

        SocialPerformanceRecord record2 =
                PerformanceRecordBuilder.please().createDefault().withYear("2017").done();

        // Create the final SalesMan with two performance records
        managePersonal.addSocialPerformanceRecord( record1 , salesManIST );
        managePersonal.addSocialPerformanceRecord( record2 , salesManIST );

        // Reading the Performance Record:
        List<SocialPerformanceRecord> list_after_read = managePersonal.readSocialPerformanceRecord( salesManIST );
        assertEquals(2 , list_after_read.size() );

        // Comparing the Lists (a bit of tricky...)
        List list_before_create = new ArrayList();
        list_before_create.add( record1 );
        list_before_create.add( record2 );
        assertArrayEquals(list_before_create.toArray(), list_after_read.toArray() , "Comparison of Arrays");

        // Now deleting a record from a salesman:
        managePersonal.deleteSocialPerformanceRecord( record1 , salesManIST );
        list_after_read = managePersonal.readSocialPerformanceRecord( salesManIST );
        assertEquals(1 , list_after_read.size() );

        // Final Object comparison
        List list_before_create2 = new ArrayList();
        list_before_create2.add( record2 ); // kick out record1!
        assertArrayEquals(list_before_create2.toArray(), list_after_read.toArray() , "Comparison of Arrays");
    }

    @Test
    public void updateSalesman() {
        // Create
        SalesMan salesMan = new SalesMan("Stefan" , "Mayer" , 123);
        managePersonal.createSalesMan(salesMan);

        // Update
        SalesMan salesManNew = new SalesMan("Stefan" , "Mayer-Schmidt" , 123);
        managePersonal.updateSalesMan(salesMan , salesManNew);

        // Read and compare
        SalesMan salesManUpdated = managePersonal.readSalesMan(123);
        assertEquals( salesManNew , salesManUpdated );
    }

    @Test
    public void updateLastNameOfSalesman() {
        // Create
        SalesMan salesMan = new SalesMan("Stefan", "Mayer", 123);
        managePersonal.createSalesMan(salesMan);

        // Cast necessary to fetch the internal update-Method
        ((ManagePersonalImpl) managePersonal).updateLastNameOfSalesMan(salesMan, "Mayer-Schmidt");

        // Read and compare
        SalesMan salesManUpdated = managePersonal.readSalesMan(123);
        SalesMan salesManExpected = new SalesMan("Stefan", "Mayer-Schmidt", 123);
        assertEquals(salesManExpected, salesManUpdated);
    }

}
