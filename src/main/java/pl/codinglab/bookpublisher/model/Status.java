package pl.codinglab.bookpublisher.model;

public enum Status {

    OPEN("Open"),
    WRITING("Writing"),
    EDITION("Edition"),
    CORRECTION("Correction"),
    PRINT("Print"),
    MARKETING("Marketing"),
    SALE("Sale");

    private String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
