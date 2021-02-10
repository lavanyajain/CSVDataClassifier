package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import model.TransactionModal;

public class CSVFileReader {

    public List<TransactionModal> readCSV(String fileName) throws IOException {
        List transactions = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("target/classes/" + fileName));
        String line = null;
        br.readLine();
        while (true) {
            if (!((line = br.readLine()) != null && !line.isEmpty())) break;
            String[] fields = line.split(",");
            TransactionModal transaction = new TransactionModal(fields[0], tryParseDates(fields[1]), fields[2], tryParseInteger(fields[3]), tryParseFloat(fields[4]), tryParseFloat(fields[5]), tryParseFloat(fields[6]), tryParseFloat(fields[7]), tryParseFloat(fields[8]));
            transactions.add(transaction);
        }
        br.close();
        return transactions;
    }

    private Date tryParseDates(String dateString) {
        List<String> formatStrings = Arrays.asList("dd-mm-yyyy", "d/m/yy");
        for (String formatString : formatStrings) {
            try {
                return new SimpleDateFormat(formatString).parse(dateString);
            }
            catch (ParseException e) {}
        }
        return null;
    }

    private Integer tryParseInteger(String stringInt) {
        try {
            return Integer.parseInt(stringInt.replaceAll(",","").replaceAll("\"",""));
        } catch (Exception e){}
        return 0;
    }

    private Float tryParseFloat(String stringFloat) {
        try {
            return Float.parseFloat(stringFloat.replaceAll(",","").replaceAll("\"",""));
        } catch (Exception e) {}
        return 0.00f;
    }
}
