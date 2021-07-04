package com.geekbang.supermarket;

public class LittleSuperMarket {
    public String superMarketName; //超市名称
    public String address; //超市地址
    public int parkingCount; //车位数量
    public double incomingSum; //收入总额
    public Merchandise[] merchandises; //商品数组
    public int[] merchandiseSold; //销售总数


    // >> TODO 返回值类型可以是类名，这个时候实际返回的值就是这个类的引用
    // 计算超市里 利润最高的商品
    public Merchandise getBiggestProfitMerchandise() {
        Merchandise curr = null;
        for (int i = 0; i < merchandises.length; i++) {
            Merchandise m = merchandises[i];
            if (curr == null) {
                curr = m;
                continue;
            }
            // 调用商品定义的方法，不同的实例调用相同的方法，虽然代码相同，但是每个实例内部数据不同，所以返回值也不同
            double currProfit = curr.calculateProfit();
            double newProfit = m.calculateProfit();
            if (currProfit < newProfit) {
                curr = m;
            }
        }
        return curr;
    }
    public Merchandise getBiggerstProfitMerchandise2(){
        Merchandise curr = null;
        for (int i= 0;i<merchandises.length;i++){
            Merchandise m = merchandises[i];
            if (curr==null){
                curr=m;
            }else {
                if (curr.calculateProfit()<m.calculateProfit()){
                    curr=m;
                }
            }
        }
        return curr;
    }
}


