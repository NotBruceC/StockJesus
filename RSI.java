import java.util.List;

public class RSIIndicator {

    public static double calculateRSI(List<Double> prices, int period) {
        if (period <= 1 || prices.size() < period) {
            throw new IllegalArgumentException("Invalid period or insufficient data.");
        }

        double sumGain = 0;
        double sumLoss = 0;

        // Calculate average gain and average loss
        for (int i = 1; i < period; i++) {
            double diff = prices.get(i) - prices.get(i - 1);
            if (diff > 0) {
                sumGain += diff;
            } else if (diff < 0) {
                sumLoss -= diff;
            }
        }
        double avgGain = sumGain / period;
        double avgLoss = sumLoss / period;

        // Calculate RSI
        for (int i = period; i < prices.size(); i++) {
            double diff = prices.get(i) - prices.get(i - 1);
            if (diff > 0) {
                avgGain = (avgGain * (period - 1) + diff) / period;
                avgLoss = (avgLoss * (period - 1)) / period;
            } else if (diff < 0) {
                avgGain = (avgGain * (period - 1)) / period;
                avgLoss = (avgLoss * (period - 1) - diff) / period;
            } else {
                avgGain = (avgGain * (period - 1)) / period;
                avgLoss = (avgLoss * (period - 1)) / period;
            }
        }

        // Calculate RSI value
        double relativeStrength = avgGain / avgLoss;
        double rsi = 100 - (100 / (1 + relativeStrength));

        return rsi;
    }

    public static void main(String[] args) {
        List<Double> prices = List.of(
                172.270004, 172.020004, 170.660004, 171.020004, 170.300003, 169.050003, 167.990005,
                167.089996, 167.419998, 170.309998, 170.110001, 168.990005, 170.500000, 172.940002,
                172.279999, 172.729996, 176.270004, 174.360001, 173.729996, 174.729996, 174.500000,
                175.100006
        );

        int period = 14; // RSI period

        double rsi = calculateRSI(prices, period);
        System.out.println("RSI: " + rsi);
    }
}