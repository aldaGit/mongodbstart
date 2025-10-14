package org.hbrs.ia.solutions.model.control;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.hbrs.ia.solutions.model.SalesMan;
import org.hbrs.ia.solutions.model.SocialPerformanceRecord;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Updates.*; //importing set, pull etc.

public class ManagePersonalImpl implements ManagePersonal{

    private MongoCollection<Document> salesmen;

    @Override
    public void createSalesMan(SalesMan salesMan) {
        salesmen.insertOne( salesMan.toDocument() );
    }

    @Override
    public void deleteSalesMan(SalesMan salesMan) {
        salesmen.deleteOne( eq(SalesMan.Constants.SID, salesMan.getId()));
    }

    @Override
    public void addSocialPerformanceRecord( SocialPerformanceRecord record, SalesMan salesMan ) {
        salesmen.updateOne( eq( SalesMan.Constants.SID , salesMan.getId()),
                pushEach( SocialPerformanceRecord.Constants.KEY_OF_RECORD,
                        Arrays.asList( record.toDocument() ) ) );
    }

    @Override
    public void deleteSocialPerformanceRecord(SocialPerformanceRecord record, SalesMan salesMan) {
        salesmen.updateOne( eq( SalesMan.Constants.SID , salesMan.getId()) ,
                pull( "record" ,  record.toDocument() ) );
    }

    @Override
    public SalesMan readSalesMan(int sid) {
        FindIterable<Document> findIterable = salesmen.find( eq( SalesMan.Constants.SID, sid) );
        MongoCursor<Document> cursor = findIterable.cursor();
        Document document = cursor.next();
        SalesMan salesMan = new SalesMan( document );
        return salesMan;
    }

    @Override
    public List<SalesMan> readAllSalesMen() {
        FindIterable<Document> findIterable = salesmen.find();
        MongoCursor<Document> cursor = findIterable.cursor();
        List<SalesMan> salesManList = new ArrayList<>();

        while (cursor.hasNext()) {
            Document document = cursor.next();
            SalesMan salesMan = new SalesMan( document );
            salesManList.add(salesMan);
        }

        // Alternative: ForEach-method auf FindIterable
        return salesManList;
    }


    public List<SalesMan> readAllSalesMenWithLeadershipValue( int leadershipValue ) {
        FindIterable<Document> findIterable =
                salesmen.find( gte(  SalesMan.Constants.LEADERSHIP , leadershipValue  ) );
        MongoCursor<Document> cursor = findIterable.cursor();
        List<SalesMan> salesManList = new ArrayList<>();

        while (cursor.hasNext()) {
            Document document = cursor.next();
            SalesMan salesMan = new SalesMan( document );
            salesManList.add(salesMan);
        }
        return salesManList;
    }


    @Override
    public void updateSalesMan(SalesMan salesMan, SalesMan newSalesMan) {
        salesmen.replaceOne( eq( SalesMan.Constants.SID , salesMan.getId()),
                newSalesMan.toDocument() );
    }

    // For internal test purposes, not shifted to interface
    // Good source: https://www.baeldung.com/mongodb-update-documents
    public void updateLastNameOfSalesMan(SalesMan salesMan, String lastName) {
        salesmen.updateOne( eq( SalesMan.Constants.SID , salesMan.getId()),
                set( SalesMan.Constants.LAST_NAME , lastName ) );
    }



    @Override
    public List<SocialPerformanceRecord> readSocialPerformanceRecord(SalesMan salesMan) {
        FindIterable<Document> findIterable
                = salesmen.find( eq(SalesMan.Constants.SID, salesMan.getId()));

        MongoCursor<Document> cursor = findIterable.cursor();
        Document document = cursor.next();
        List<Document> docRecord = document.getList(
                SocialPerformanceRecord.Constants.KEY_OF_RECORD , Document.class );

        List<SocialPerformanceRecord> records = new ArrayList<>();
        for ( Document doc : docRecord ) {
            SocialPerformanceRecord record = new SocialPerformanceRecord();
            record.setYear(doc.getString( SocialPerformanceRecord.Constants.YEAR ));
            record.setLeadershipSOLL(doc.getString( SocialPerformanceRecord.Constants.LEADERSHIPSOLL ));
            record.setLeadershipIST( doc.getString( SocialPerformanceRecord.Constants.LEADERSHIPIST ) );
            record.setOpennessSOLL(doc.getString( SocialPerformanceRecord.Constants.OPENNESSSOLL ));
            record.setOpennessIST( doc.getString( SocialPerformanceRecord.Constants.OPENNESSIST ) );
            records.add(record);
        }
        return records;
    }

    public void setCollection(MongoCollection<Document> salesmen) {
        this.salesmen = salesmen;
    }

    public void deleteCollection() {
        this.salesmen.drop();
    }
}
