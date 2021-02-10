package api;

import model.MatchStatus;
import model.TransactionModal;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Classifer {
    CSVFileReader reader = new CSVFileReader();

    static final Integer matchThreshold = 1;
    static final Integer matchDateThreshold = 1;

    public List<MatchStatus> classify() throws IOException {
        List<TransactionModal> buyer = reader.readCSV("Buyer.csv");
        List<TransactionModal> seller = reader.readCSV("Supplier.csv");
        List<MatchStatus> matchStatus = new ArrayList<>();
        Integer maxScore, matchScore;
        Integer bestBuyerScoreIndex = 0, bestSellerScoreIndex = 0;
        for(int i=0; i<buyer.size(); i++) {
            maxScore = 0;
            if(!buyer.get(i).isStatus()) {
                for (int j = 0; j < seller.size(); j++) {
                    if(!seller.get(j).isStatus()) {
                        matchScore = getMatchStatus(buyer.get(i), seller.get(j));
                    if (matchScore >= maxScore) {
                        maxScore = matchScore;
                        bestBuyerScoreIndex = i;
                        bestSellerScoreIndex = j;
                        }
                    }
                }
            }
            if(maxScore == 0) {
                matchStatus.add(new MatchStatus("In seller", seller.get(bestSellerScoreIndex), null, maxScore));
                matchStatus.add(new MatchStatus("In Buyer", null, buyer.get(bestBuyerScoreIndex), maxScore));
                seller.get(bestSellerScoreIndex).setStatus(true);
                buyer.get(bestSellerScoreIndex).setStatus(true);
            }
            else if(maxScore == 8) {
                matchStatus.add(new MatchStatus("Full Match", seller.get(bestSellerScoreIndex), buyer.get(bestBuyerScoreIndex), maxScore));
                seller.get(bestSellerScoreIndex).setStatus(true);
                buyer.get(bestBuyerScoreIndex).setStatus(true);
            } else if(maxScore > 0) {
                matchStatus.add(new MatchStatus("Partial", seller.get(bestSellerScoreIndex), buyer.get(bestBuyerScoreIndex), maxScore));
            }
        }

        for(TransactionModal buyerEntry : buyer)
            if(!buyerEntry.isStatus())
                matchStatus.add(new MatchStatus("In Buyer", buyerEntry, null, 0));
        for(TransactionModal sellerEntry : seller)
            if(!sellerEntry.isStatus())
                matchStatus.add(new MatchStatus("In seller", null, sellerEntry, 0));
        Collections.sort(matchStatus, (o1, o2) -> {
            if(o1.getMatchScore() > o2.getMatchScore())
                return -1;
            if(o1.getMatchScore() < o2.getMatchScore())
                return 1;
            return 0;
        });
        return matchStatus;
    }

    private static Integer getMatchStatus(TransactionModal buyer, TransactionModal seller) {
        if(buyer.equals(seller))
            return 6;
        int matchScore = 0;
      if(seller.getBillNum().contentEquals(buyer.getBillNum()))
          matchScore++;
      if(seller.getBillNum().contentEquals(buyer.getBillNum()))
          matchScore++;
      if(seller.getGstRate() >= buyer.getGstRate() - matchThreshold && seller.getGstRate() <= buyer.getGstRate() +  matchThreshold)
          matchScore++;
      if(seller.getCGST() >= buyer.getCGST() - matchThreshold && seller.getCGST() <= buyer.getCGST() +  matchThreshold)
          matchScore++;
      if(seller.getIGST() >= buyer.getIGST() - matchThreshold && seller.getIGST() <= buyer.getIGST() +  matchThreshold)
          matchScore++;
      if(seller.getSGST() >= buyer.getSGST() - matchThreshold && seller.getSGST() <= buyer.getSGST() +  matchThreshold)
            matchScore++;
      if(seller.getTaxableValue() >= buyer.getTaxableValue() - matchThreshold && seller.getTaxableValue() <= buyer.getTaxableValue() +  matchThreshold)
            matchScore++;
      long diff = TimeUnit.DAYS.convert((buyer.getDate().getTime() - seller.getDate().getTime()), TimeUnit.MILLISECONDS);
      if(diff <= Math.abs(matchDateThreshold))
          matchScore++;
      return matchScore;
    }
}
