package org.hbrs.ia.solutions.test.builder;

import org.hbrs.ia.solutions.model.SocialPerformanceRecord;

public class PerformanceRecordBuilder {

    private static PerformanceRecordBuilder builder = null;

    private  SocialPerformanceRecord record;

    public SocialPerformanceRecord done(){
        return record;
    }

    public static PerformanceRecordBuilder please(){
        if(builder == null){
            builder = new PerformanceRecordBuilder();
        }
        return builder;
    }

    public PerformanceRecordBuilder createDefault() {
        record = new SocialPerformanceRecord();

        // Set the Template of a Performance Record
        record.setYear("2019");
        record.setLeadershipSOLL("5");
        record.setLeadershipIST("4");
        record.setOpennessSOLL("3");
        record.setOpennessIST("3");

        return this;
    }

    public PerformanceRecordBuilder withYear(String year) {
        record.setYear(year);
        return this;
    }

}
