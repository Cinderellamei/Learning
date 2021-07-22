package shezhao;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 */
public class MaxProfit {
    /**
     * 如果你最多只允许完成一笔交易(即买入和卖出一支股票)，设计一个算法来计算你所能获取的最大利润
     * @param price
     * @return
     */
    public static int countMaxProfit(int [] price) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for(int i = 0;i<price.length;i++) {
            if(maxProfit != minPrice && price[i]-minPrice>maxProfit) {
                maxProfit = price[i]-minPrice;
            }
            if(price[i]<minPrice) {
                minPrice = price[i];
            }
        }
        return maxProfit;
    }

    /**
     * 股票可以买卖多次, 但是你必须要在出售股票之前把持有的股票卖掉
     * @param price
     * @return
     */
    public static int coutMaxProfit1(int [] price) {
        int maxProfit = 0;
        for(int i = 0;i<price.length;i++) {
            if(i>0 && price[i]-price[i-1]>0) {
                maxProfit+=price[i]-price[i-1];
            }
        }
        return maxProfit;
    }


    public static void main(String [] args) {

    }
}
