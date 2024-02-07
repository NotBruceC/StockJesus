import java.util.List;

public class StochasticOscillator {

    public static void calculateStochasticOscillator(List<Double> prices, int period) {
        if (prices.size() < period) {
            throw new IllegalArgumentException("Insufficient data for the given period.");
        }

        double highestHigh = findHighestHigh(prices, period);
        double lowestLow = findLowestLow(prices, period);

        double currentPrice = prices.get(prices.size() - 1);
        double k = ((currentPrice - lowestLow) / (highestHigh - lowestLow)) * 100;

        double d = calculateSMA(prices.subList(prices.size() - period, prices.size()), 3);

        System.out.println("%K: " + k);
        System.out.println("%D: " + d);
    }

    private static double findHighestHigh(List<Double> prices, int period) {
        double highestHigh = Double.MIN_VALUE;
        for (int i = prices.size() - period; i < prices.size(); i++) {
            double price = prices.get(i);
            if (price > highestHigh) {
                highestHigh = price;
            }
        }
        return highestHigh;
    }

    private static double findLowestLow(List<Double> prices, int period) {
        double lowestLow = Double.MAX_VALUE;
        for (int i = prices.size() - period; i < prices.size(); i++) {
            double price = prices.get(i);
            if (price < lowestLow) {
                lowestLow = price;
            }
        }
        return lowestLow;
    }

    private static double calculateSMA(List<Double> values, int period) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / period;
    }

    public static void main(String[] args) {
        List<Double> prices = List.of(
                354.829987, 357.829987, 358.320007, 353.959991, 346.290009, 344.470001, 347.119995, 351.950012, 358.660004, 357.429993, 370.470001, 369.670013, 374.489990, 367.459991, 368.369995, 376.130005, 383.450012, 381.779999, 385.200012, 390.700012, 393.179993, 394.140015, 401.019989, 400.059998, 390.140015, 394.779999, 474.989990, 459.410004, 454.720001
        );

        int period = 14;

        calculateStochasticOscillator(prices, period);
    }
}