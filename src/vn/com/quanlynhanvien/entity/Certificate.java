package vn.com.quanlynhanvien.entity;

import java.util.Date;

public class Certificate {
    private String certificatedID;
    private String certificateName;
    private String certificateRank;
    private Date certificatedDate;

    public Certificate(String certificatedID, String certificateName, String certificateRank, Date certificatedDate) {
        this.certificatedID = certificatedID;
        this.certificateName = certificateName;
        this.certificateRank = certificateRank;
        this.certificatedDate = certificatedDate;
    }

    public String getCertificatedID() {
        return certificatedID;
    }

    public void showCertificateInfo() {
        System.out.println("Certificate ID: " + certificatedID);
        System.out.println("Certificate Name: " + certificateName);
        System.out.println("Certificate Rank: " + certificateRank);
        System.out.println("Certificate Date: " + certificatedDate);
    }
}
