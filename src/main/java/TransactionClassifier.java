import api.Classifer;
import model.MatchStatus;

import java.io.IOException;
import java.util.List;

public class TransactionClassifier {
    public static void main(String args[]) throws IOException {
        Classifer classifer = new Classifer();
        List<MatchStatus> classifiedData = classifer.classify();
        for(MatchStatus status : classifiedData)
            System.out.println(status.toString());
    }
}
