package com.geekbang.supermarket;

public class Merchandise {
    public String name; // 商品名称
    public String id; // 商品id
    public int count; //商品数量
    public double soldPrice; //售价
    public double purchasePrice; //进价


    /*
        方法定义
            让对象有自己的行为
         */
    // >>TODO 访问修饰符
    // >>TODO 返回值类型： 无需返回值则用void表示，void是Java中的关键字
    // >>TODO 方法名：任意合法的标识符都可以
    // >>TODO 方法体：方法的代码
    // >> TODO 方法内定义的变量为 局部变量  方法外的为成员变量
    //无返回值方法
    public void desrcibe() {
        double netIncome = soldPrice - purchasePrice;
        System.out.println("商品名字叫做" + name + ",id是" + id
                + "商品的售价为" + soldPrice + "商品的进价为" + purchasePrice +
                "销售一件商品的毛利润" + netIncome);
    }


    // >> TODO 在方法中定义返回值
    // >> TODO Java中一个方法只能定义一种返回值，如果不需要返回值则用void表示
    // >> TODO 如果定义了返回值 则必须使用 return 语句返回方法的返回值， return是Java种的关键字
    // >> TODO 可以认定，返回值必须要能够用来给返回值类型的变量赋值
    // 带有返回值的方法
    public double calculateProfit() {
        double profit = soldPrice - purchasePrice;
        // >> TODO 这个return 是代码块中的return，是return所在代码块中的最后一个语句
        if (profit <= 0) {
            return 0;
        }
        return profit;
        // >> TODO 一个方法中可以有多个返回值
    }

    // >> TODO 返回值如果是基本类型，则要类型完全相同，或者符合类型自动转换规则
    public double getCurrentCount() {
        return count;
    }

    // >> TODO 如果不符合规则，可以使用强制类型转换
    public int getIntSoldPrice() {
        return (int) soldPrice;
    }

    /*
    带有参数的方法
    // >> TODO 参数是定义在方法名字后面的括号里的
    // >> TODO 参数定义的规范和变量一样，都是类型名字加标识符，这里的标识符我们叫做参数名
    // >> TODO 方法体内的代码可以使用参数
    // >> TODO 参数的值在调用的时候需要给出，有的资料叫做实参（实际参数）
    //    TODO 对应的，方法定义这里的参数，叫做形参（形式参数）
     */
    public double buy(int countToBuy) {
        if (count < countToBuy) {
            System.out.println("库存不够");
            return -1;
        }
        System.out.println("商品的单价为" + purchasePrice);
        int fullPriceCount = countToBuy / 2 + countToBuy % 2;
        int halfPriceCount = countToBuy - fullPriceCount;
        double totalCost = purchasePrice * soldPrice + (halfPriceCount * soldPrice / 2);
        count -= countToBuy;
        return totalCost;
    }

    // >> TODO 一个方法可以有多个参数，多个参数之间用逗号隔开
    public double buyAndPrintLeft(int countBuy, boolean printLeft) {
        if (count < countBuy) {
            System.out.println("商品库存不足");
            if (printLeft) {
                System.out.println("商品剩余库存" + count);
            }
            return -1;
        }
        System.out.println("商品的单价为" + soldPrice);
        int fullPriceCount = countBuy / 2 + countBuy % 2;
        int halfPrcieCOunt = countBuy - fullPriceCount;
        double totalCost = fullPriceCount * soldPrice + (halfPrcieCOunt * soldPrice / 2);
        count -= countBuy;
        if (printLeft) {
            System.out.println("商品剩余库存为" + printLeft);

        }
        return totalCost;

    }

    // >> TODO 参数可以是任何类型   包括自定义类型，甚至是自己的类型都没问题
    public boolean totalValueBiggerThen(Merchandise merchandise) {
        return count * purchasePrice > merchandise.purchasePrice * merchandise.count;
    }

    // >> TODO 参数可以是任何类型，包括自定义类型
    public boolean isTheBiggerTotalValueOne(LittleSuperMarket littleSuperMarket) {
        double totalValue = count * soldPrice;
        for (int i = 0; i < littleSuperMarket.merchandises.length; i++) {
            Merchandise m = littleSuperMarket.merchandises[i];
            double newTotalValue = m.count * m.soldPrice;
            if (totalValue < newTotalValue) {
                // 执行到return的时候，方法直接结束，不管是不是在循环种，在第几层循环中
                return false;
            }
        }
        return true;
    }
}

