package com.dct.nextgen.constants;

public interface CustomerDemandConstants {

    interface Status {
        String PENDING = "PENDING";
        String ADVISED = "ADVISED";
        String REFUSED = "REFUSED";
        String DELETED = "DELETED";
        String PATTERN = "^(PENDING|ADVISED|REFUSED|DELETED)$";
    }
}
