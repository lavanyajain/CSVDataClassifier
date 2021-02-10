package model;

import java.util.Objects;

public class MatchStatus {
    private String status;
    private TransactionModal seller;
    private TransactionModal buyer;
    private Integer matchScore;

    public MatchStatus(String status, TransactionModal seller, TransactionModal buyer, Integer matchScore) {
        this.status = status;
        this.seller = seller;
        this.buyer = buyer;
        this.matchScore = matchScore;
    }

    public Integer getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(Integer matchScore) {
        this.matchScore = matchScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TransactionModal getSeller() {
        return seller;
    }

    public void setSeller(TransactionModal seller) {
        this.seller = seller;
    }

    public TransactionModal getBuyer() {
        return buyer;
    }

    public void setBuyer(TransactionModal buyer) {
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        String display = "";
        if(seller == null)
            display = display + "null";
        else
            display = seller.toString();
        display = display + " * ";
        if(buyer == null)
            display = display + "null";
        else
            display = display + buyer.toString();
        return display;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchStatus that = (MatchStatus) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(seller, that.seller) &&
                Objects.equals(buyer, that.buyer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, seller, buyer);
    }
}
