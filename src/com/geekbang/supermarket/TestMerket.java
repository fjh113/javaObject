package com.geekbang.supermarket;

import com.geekbang.person.Customer;

import java.util.Scanner;

public class TestMerket {
    public static void main(String[] args) {
        //创建一个小超市类
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket();
        // 给超市的名字、地址、停车位赋值
        littleSuperMarket.superMarketName = "有家超市";
        littleSuperMarket.address = "世纪大道666号";
        littleSuperMarket.parkingCount = 200;
        // 给超市200种商品
        littleSuperMarket.merchandises = new Merchandise[200];
        //统计用的数组
        littleSuperMarket.merchandiseSold = new int[littleSuperMarket.merchandises.length];

        //为了使用方便，创建一个商品数组引用，和littlersuperMarket.merchandises指向同一个数组对象
        Merchandise[] all = littleSuperMarket.merchandises;
        //遍历并给200种商品赋值
        for (int i = 0; i < all.length; i++) {
            Merchandise m = new Merchandise();
            m.count = 200;
            m.name = "商品" + i;
            m.id = "ID" + i;
            m.purchasePrice = Math.random() * 200;
            m.soldPrice = (1 + Math.random()) * 200;
            // 用创建的商品，给商品数组的第i个引用赋值，all和小超市的商品数组引用指向的是同一个数组
            all[i] = m;

        }
        Scanner scanner = new Scanner(System.in);
        // 调用LitteSuperMerker中的方法，输出利润最高的商品
        // >>TODO 返回值可以直接使用，不必赋值给一个变量在使用
        //littleSuperMarket.getBiggestProfitMerchandise().desrcibe();

        /*
        活动
            >> TODO  第二件商品半价
         */
        System.out.println("超市开门了");

        boolean open = true;


        while (open) {
            System.out.println("本店店名叫做" + littleSuperMarket.superMarketName);
            System.out.println("本店地址在" + littleSuperMarket.address);
            System.out.println("共有停车位" + littleSuperMarket.parkingCount + "个");
            System.out.println("今日营业额为" + littleSuperMarket.incomingSum);
            System.out.println("共有商品" + littleSuperMarket.merchandises.length + "种");
            int a = 1;
            System.out.print("是否开启第二件半价活动：");
            int discounts = scanner.nextInt();
            Customer customer = new Customer();
            customer.name = "顾客编号" + ((int) (Math.random() * 10000));
            customer.money = (1 + Math.random() * 1000);
            customer.isDrivingCar = Math.random() > 0.5;

            //判断客户是否开车开车
            if (customer.isDrivingCar) {
                if (littleSuperMarket.parkingCount > 0) {
                    System.out.println("欢迎" + customer.name + "驾车而来，本店已经为你安排了车位，停车免费");
                    littleSuperMarket.parkingCount--;
                } else {
                    System.out.println("不好意思，本店停车位已满，欢迎下次光临");
                    continue;
                }
            } else {
                System.out.println("欢迎" + customer.name + "光临本店");

            }
            double totalCost = 0;

            if (discounts==a){
                while (true) {
                    System.out.println("今日超市大优惠，所有商品第二件半件，选择需要购买的商品索引");
                    int index = scanner.nextInt();
                    if (index < 0) {
                        break;
                    }
                    if (index > all.length) {
                        System.out.println("本店没有该商品");
                        continue;
                    }
                    Merchandise m = all[index];
                    System.out.println("需要购买几个");
                    int numToBuy = scanner.nextInt();
                    if (index > m.count) {
                        System.out.println("库存不足，请选择正确的购买数量");
                        continue;
                    }

                    System.out.println("您选购的商品为" + m.name + "单价是" + m.soldPrice + "请问你需要购买几个");
                    //全价商品个数
                    int fullPriceCount = numToBuy / 2 + numToBuy % 2;
                    //半价商品个数
                    int halfPriceCount = numToBuy - fullPriceCount;
                    //总价
                     totalCost = fullPriceCount * m.soldPrice + (halfPriceCount * m.soldPrice / 2);
                    m.count -= numToBuy;


                }

            }else {
                while (true) {
                    System.out.println("本店提供了" + all.length + "种商品，欢迎选购，请输入商品编号");
                    int index = scanner.nextInt();
                    //取消购买，跳出循环
                    if (index < 0) {
                        break;
                    }
                    //判断输入超出边界
                    if (index >= all.length) {
                        System.out.println("本店没有这种商品，请输入编号在" + (all.length - 1) + "之内的商品编号");
                        continue;
                    }

                    Merchandise m = all[index];

                    System.out.println("您选购的商品为" + m.name + "单价是" + m.soldPrice + "请问你需要购买几个");
                    int numToBuy = scanner.nextInt();
                    //限制购买数量不能未负数
                    if (numToBuy <= 0) {
                        System.out.println("请输入在需要购买的正确数量");
                        continue;
                    }
                    //库存
                    if (numToBuy > m.count) {
                        System.out.println("库存不足，请输入正确的购买数量");
                        continue;
                    }

                    //余额不足
                    if (numToBuy * m.purchasePrice + totalCost > customer.money) {
                        System.out.println("你的余额不足");
                        continue;
                    }

                    //总共消费多少钱
                    totalCost += numToBuy * m.soldPrice;

                    //库存 = 总库存-消费库存
                    m.count -= numToBuy;
                    littleSuperMarket.merchandiseSold[index] += numToBuy;//用户购买了多少该商品
                }

            }



            customer.money -= totalCost;//客户余额；

            //用户离开，车位增加
            if (customer.isDrivingCar) {
                littleSuperMarket.parkingCount++;
            }
            System.out.println("用户" + customer.name + "共消费了" + totalCost);

            littleSuperMarket.incomingSum += totalCost;//销售金额

            //是否关门
            System.out.println("是否继续营业");
            open = scanner.nextBoolean();
        }
        System.out.println("超市关门了");
        System.out.println("超市今天的营业额" + littleSuperMarket.incomingSum);

        for (int i = 0; i < littleSuperMarket.merchandiseSold.length; i++) {
            Merchandise m = all[i];
            int numSold = littleSuperMarket.merchandiseSold[i];
            if (numSold > 0) {
                double incomming = m.soldPrice * numSold;
                double netIncomming = (m.soldPrice - m.purchasePrice) * numSold;//净利润
                System.out.println(m.name + "售出了" + numSold + "个，销售额为" + incomming + "净利润为" + netIncomming);

            }
        }
    }
}
