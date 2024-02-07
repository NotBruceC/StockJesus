import java.util.ArrayList;
import java.util.List;

public class MACDIndicator {

    public static void calculateMACD(List<Double> prices, int shortPeriod, int longPeriod, int signalPeriod) {
        if (shortPeriod >= longPeriod || signalPeriod <= 0 || prices.size() < longPeriod) {
            throw new IllegalArgumentException("Invalid periods or insufficient data.");
        }

        // Calculate short-term EMA
        List<Double> shortEMA = calculateEMA(prices, shortPeriod);

        // Calculate long-term EMA
        List<Double> longEMA = calculateEMA(prices, longPeriod);

        // Calculate MACD line
        List<Double> macdLine = new ArrayList<>();
        for (int i = 0; i < longEMA.size(); i++) {
            macdLine.add(shortEMA.get(i) - longEMA.get(i));
        }

        // Calculate signal line (EMA of MACD line)
        List<Double> signalLine = calculateEMA(macdLine, signalPeriod);

        // Calculate histogram (MACD line - signal line)
        List<Double> histogram = new ArrayList<>();
        for (int i = 0; i < macdLine.size(); i++) {
            histogram.add(macdLine.get(i) - signalLine.get(i));
        }

        System.out.println("MACD Line: " + macdLine.get(macdLine.size() - 1));
        System.out.println("Signal Line: " + signalLine.get(signalLine.size() - 1));
        System.out.println("Histogram: " + histogram.get(histogram.size() - 1));
    }

    private static List<Double> calculateEMA(List<Double> prices, int period) {
        double smoothingFactor = 2.0 / (period + 1);
        List<Double> ema = new ArrayList<>();
        ema.add(prices.get(0));

        for (int i = 1; i < prices.size(); i++) {
            double value = (prices.get(i) - ema.get(i - 1)) * smoothingFactor + ema.get(i - 1);
            ema.add(value);
        }

        return ema;
    }

    public static void main(String[] args) {
        List<Double> prices = List.of(
                168.389999, 169.399994, 170.300003, 170.100006, 172.080002, 171.330002, 171.410004, 172.270004, 172.020004, 170.660004, 171.020004, 170.300003, 169.050003, 167.990005, 167.089996, 167.419998, 170.309998, 170.110001, 168.990005, 170.500000, 172.940002, 172.279999, 172.729996, 176.270004, 174.360001, 173.729996, 174.729996, 174.500000, 175.100006
        );

        int shortPeriod = 12; // Short-term EMA period
        int longPeriod = 26; // Long-term EMA period
        int signalPeriod = 9; // Signal line period

        calculateMACD(prices, shortPeriod, longPeriod, signalPeriod);
    }
}
