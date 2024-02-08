import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Double> prices = List.of(
                354.829987, 357.829987, 358.320007, 353.959991, 346.290009, 344.470001, 347.119995, 351.950012, 358.660004, 357.429993, 370.470001, 369.670013, 374.489990, 367.459991, 368.369995, 376.130005, 383.450012, 381.779999, 385.200012, 390.700012, 393.179993, 394.140015, 401.019989, 400.059998, 390.140015, 394.779999, 474.989990, 459.410004, 454.720001
        );

        int shortPeriod = 12; // Short-term EMA period
        int longPeriod = 26; // Long-term EMA period
        int signalPeriod = 9; // Signal line period

        MACDIndicator.calculateMACD(prices, shortPeriod, longPeriod, signalPeriod);

        List<Double> historicalData = List.of(
                354.829987, 357.829987, 358.320007, 353.959991, 346.290009, 344.470001, 347.119995, 351.950012, 358.660004, 357.429993, 370.470001, 369.670013, 374.489990, 367.459991, 368.369995, 376.130005, 383.450012, 381.779999, 385.200012, 390.700012, 393.179993, 394.140015, 401.019989, 400.059998, 390.140015, 394.779999, 474.989990, 459.410004, 454.720001
        );


        int period = 14; // RSI period

        double rsi = RSI.calculateRSI(prices, period);
        System.out.println("RSI: " + rsi);



        StochasticOscillator.calculateStochasticOscillator(prices, period);

        int windowSize = 3; // Number of data points to consider for the moving average
        double thresholdPercentage = 0.05; // Percentage factor to adjust the threshold

        for (int i = windowSize; i <= historicalData.size(); i++) {
            List<Double> subList = historicalData.subList(i - windowSize, i);
            double sum = 0;
            for (double data : subList) {
                sum += data;
            }
            double movingAverage = sum / windowSize;
            double currentValue = historicalData.get(i - 1);
            double threshold = movingAverage * (1 + thresholdPercentage);

            boolean isGoodStock = MovingAverage.isStockGood(currentValue, movingAverage, threshold);
            System.out.println("Is the stock good? " + isGoodStock);
            System.out.println();
        }
    }
}