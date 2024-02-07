import java.util.List;

public class RSI {

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
                351.950012, 358.660004, 357.429993, 370.470001, 369.670013, 374.489990, 367.459991, 368.369995, 376.130005, 383.450012, 381.779999, 385.200012, 390.700012, 393.179993, 394.140015, 401.019989, 400.059998, 390.140015, 394.779999, 474.989990, 459.410004, 454.720001
        );

        int period = 14; // RSI period

        double rsi = calculateRSI(prices, period);
        System.out.println("RSI: " + rsi);
    }
}
// Above 70 overbought
// Below 30 oversold