package model;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionModal {
    private String GSTIN;
    private Date date;
    private String BillNum;
    private Integer gstRate;
    private Float taxableValue;
    private boolean status;

    public TransactionModal(String GSTIN, Date date, String billNum, Integer gstRate, Float taxableValue, Float IGST, Float CGST, Float SGST, Float total) {
        this.GSTIN = GSTIN;
        this.date = date;
        BillNum = billNum;
        this.gstRate = gstRate;
        this.taxableValue = taxableValue;
        this.IGST = IGST;
        this.CGST = CGST;
        this.SGST = SGST;
        Total = total;
        this.status = false;
    }

    private Float IGST;
    private Float CGST;
    private Float SGST;
    private Float Total;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBillNum() {
        return BillNum;
    }

    public void setBillNum(String billNum) {
        BillNum = billNum;
    }

    public Integer getGstRate() {
        return gstRate;
    }

    public void setGstRate(Integer gstRate) {
        this.gstRate = gstRate;
    }

    public Float getTaxableValue() {
        return taxableValue;
    }

    public void setTaxableValue(Float taxableValue) {
        this.taxableValue = taxableValue;
    }

    public Float getIGST() {
        return IGST;
    }

    public void setIGST(Float IGST) {
        this.IGST = IGST;
    }

    public Float getCGST() {
        return CGST;
    }

    public void setCGST(Float CGST) {
        this.CGST = CGST;
    }

    public Float getSGST() {
        return SGST;
    }

    public void setSGST(Float SGST) {
        this.SGST = SGST;
    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float total) {
        Total = total;
    }

    @Override
    public String toString() {
        return GSTIN + ", " + date + ", " + BillNum + ", " + gstRate + ", " + taxableValue + ", " + IGST + ", " + CGST + ", " + SGST + ", " + Total;
    }
}
