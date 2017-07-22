
//The cost of a stock on each day is given in an array, find the max profit that you can make by buying and selling in those days. 
//For example, if the given array is {100, 180, 260, 310, 40, 535, 695}, the maximum profit can be earned by buying on day 0, selling on day 3. Again, buy on day 4 and sell on day 6. 
//If the given array of prices is sorted in decreasing order, then profit cannot be earned at all.

public int maxProfit (int [] stockPrices) {
    //The given array of prices can't be null or be sold on the same day. If it did, return 0
    if (stockPrices == null || stockprices.length < 2) {
        return 0;
    }
    int totalProfit = 0;
    for (int i = 1; i < stockPrices.length; i++) {
        // If a sale on day I+1 day is profitable add it to the totalProfit, or else move on
        if (stockPrices [i] - stockPrices [i-1] > 0) {
            totalProfit += stockPrices [i] - stockPrices [i-1];
        }
    }
    return totalProfit;
}

