package com.example.fyp_v2.Class;

public class Receipt {

    String receiptID;
    String receiptDes;
    String date;
    String Uri;
    String total;
    String dateArr[];

    public Receipt() {
    }

    public Receipt(String receiptID, String receiptDes, String date, String total, String Uri) {
        this.receiptID = receiptID;
        this.receiptDes = receiptDes;
        this.date = date;
        this.total = total;
        this.Uri = Uri;
    }


    public String getReceiptID() {
        return receiptID;
    }

    public String getReceiptDes() {
        return receiptDes;
    }

    public String getDate() {
        return date;
    }

    public String getTotal() {
        return total;
    }

    public String getUri() {
        return Uri;
    }

    public String[] splitDate(String date){
        dateArr = new String[]{};

        dateArr = date.split("/");

        return dateArr;
    }

    public String getYear(){
        splitDate(date);
        return dateArr[2];
    }

    public String getMonth(){
        splitDate(date);
        return dateArr[1];
    }

    public String getDay(){
        splitDate(date);
        return dateArr[0];
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public void setReceiptDes(String receiptDes) {
        this.receiptDes = receiptDes;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setUri(String uri) {
        Uri = uri;
    }
}
