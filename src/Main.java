import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Order order = new Order();
            Apple apple = new Apple();
            Strawberry strawberry = new Strawberry();
            Mango mango = new Mango();

            System.out.println("请输入用户名");
            Person person = new Person(scanner.next());
            System.out.println("请设置苹果的价格（元/斤）");
            apple.setPrice(scanner.next());
            System.out.println("请设置苹果的折扣（1-10）");
            apple.setDiscount(scanner.next());
            System.out.println("请设置草莓的价格（元/斤）");
            strawberry.setPrice(scanner.next());
            System.out.println("请设置草莓的折扣（1-10）");
            strawberry.setDiscount(scanner.next());
            System.out.println("请设置芒果的价格（元/斤）");
            mango.setPrice(scanner.next());
            System.out.println("请设置芒果的折扣（1-10）");
            mango.setDiscount(scanner.next());
            while (true) {
                System.out.println("请选择要购买的商品");
                System.out.println("1：苹果");
                System.out.println("2：草莓");
                System.out.println("3：芒果");
                System.out.println("4：结算");
                System.out.println("5：退出");
                String sc = scanner.next();
                while (!Objects.equals(sc, "1") && !Objects.equals(sc, "2") && !Objects.equals(sc, "3") && !Objects.equals(sc, "4") && !Objects.equals(sc, "5")) {
                    System.out.println("选择出现异常请重新选择");
                    System.out.println("1：苹果");
                    System.out.println("2：草莓");
                    System.out.println("3：芒果");
                    System.out.println("4：结算");
                    System.out.println("5：退出");
                    sc = scanner.next();
                }
                if (sc.equals("4")) {
                    person.settle(order);
                    break;
                }
                if (sc.equals("5")) {
                    break;
                }
                System.out.println("请输入购买数量（斤）");
                String pounds = scanner.next();
                if (sc.equals(apple.getId().toString())) {
                    person.buy(order, apple, pounds);
                }
                if (sc.equals(strawberry.getId().toString())) {
                    person.buy(order, strawberry, pounds);
                }
                if (sc.equals(mango.getId().toString())) {
                    person.buy(order, mango, pounds);
                }
            }

            System.out.println("是否继续使用购物系统");
            System.out.println("1：继续使用");
            System.out.println("2：结束使用");
            String next = scanner.next();
            while (!Objects.equals(next, "1") && !Objects.equals(next, "2")) {
                System.out.println("操作系统出现意料之外的异常，请重新选择是否继续使用购物系统");
                System.out.println("1：继续使用");
                System.out.println("2：结束使用");
                next = scanner.next();
            }
            if (next.equals("2")) {
                break;
            }
        }
    }

    /**
     * 商品
     */
    public static class Goods {
        /**
         * 商品id
         */
        private Integer id;
        /**
         * 商品名称
         */
        private String name;
        /**
         * 折扣
         */
        private BigDecimal discount;
        /**
         * 价格（元/数量）
         */
        private BigDecimal price;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = new BigDecimal(discount);
        }

        public BigDecimal getPrice() {
            return this.price;
        }

        public void setPrice(String price) {
            this.price = new BigDecimal(price);
        }
    }

    /**
     * 水果
     */
    public static class Fruit extends Goods {

    }

    public static class Mango extends Fruit {
        public Mango() {
            this.setId(3);
            this.setName("芒果");
        }

        public Mango(String price, String discount) {
            this.setId(3);
            this.setName("芒果");
            this.setPrice(price);
            this.setDiscount(discount);
        }
    }

    public static class Apple extends Fruit {
        public Apple() {
            this.setId(1);
            this.setName("苹果");
        }

        public Apple(String price, String discount) {
            this.setId(1);
            this.setName("苹果");
            this.setPrice(price);
            this.setDiscount(discount);
        }
    }

    public static class Strawberry extends Fruit {
        public Strawberry() {
            this.setId(2);
            this.setName("草莓");
        }

        public Strawberry(String price, String discount) {
            this.setId(2);
            this.setName("草莓");
            this.setPrice(price);
            this.setDiscount(discount);
        }
    }

    /**
     * 订单
     */
    public static class Order {
        /**
         * 订单明细
         */
        List<OrderDetail> orderDetails;
        /**
         * 优惠前总金额
         */
        private BigDecimal beforeDiscountPrice;
        /**
         * 优惠后总金额
         */
        private BigDecimal afterDiscountPrice;
        /**
         * 支付时间
         */
        private LocalDateTime payTime;

        public Order() {
            this.orderDetails = new ArrayList<>();
        }

        public List<OrderDetail> getOrderDetails() {
            return orderDetails;
        }

        public void setOrderDetails(List<OrderDetail> orderDetails) {
            this.orderDetails = orderDetails;
        }

        public BigDecimal getBeforeDiscountPrice() {
            return beforeDiscountPrice;
        }

        public void setBeforeDiscountPrice(BigDecimal beforeDiscountPrice) {
            this.beforeDiscountPrice = beforeDiscountPrice;
        }

        public BigDecimal getAfterDiscountPrice() {
            return afterDiscountPrice;
        }

        public void setAfterDiscountPrice(BigDecimal afterDiscountPrice) {
            this.afterDiscountPrice = afterDiscountPrice;
        }

        public LocalDateTime getPayTime() {
            return payTime;
        }

        public void setPayTime(LocalDateTime payTime) {
            this.payTime = payTime;
        }
    }

    /**
     * 订单明细
     */
    public static class OrderDetail {
        /**
         * 商品名称
         */
        private String name;
        /**
         * 商品数量
         */
        private BigDecimal nums;
        /**
         * 折扣
         */
        private BigDecimal discount;
        /**
         * 优惠前价格
         */
        private BigDecimal beforeDiscountPrice;
        /**
         * 优惠后价格
         */
        private BigDecimal afterDiscountPrice;

        public OrderDetail(String name, String price, String discount, String pounds) {
            this.name = name;
            this.nums = new BigDecimal(pounds);
            this.discount = new BigDecimal(discount);
            this.beforeDiscountPrice = new BigDecimal(price).multiply(new BigDecimal(pounds));
            this.afterDiscountPrice = new BigDecimal(price).multiply(new BigDecimal(discount)).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(pounds));
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getNums() {
            return nums;
        }

        public void setNums(BigDecimal nums) {
            this.nums = nums;
        }

        public BigDecimal getBeforeDiscountPrice() {
            return beforeDiscountPrice;
        }

        public void setBeforeDiscountPrice(BigDecimal beforeDiscountPrice) {
            this.beforeDiscountPrice = beforeDiscountPrice;
        }

        public BigDecimal getAfterDiscountPrice() {
            return afterDiscountPrice;
        }

        public void setAfterDiscountPrice(BigDecimal afterDiscountPrice) {
            this.afterDiscountPrice = afterDiscountPrice;
        }

        public BigDecimal getDiscount() {
            return discount;
        }

        public void setDiscount(BigDecimal discount) {
            this.discount = discount;
        }
    }

    /**
     * 用户
     */
    public static class Person {
        private String name;
        public void buy(Order order, Fruit fruit, String pounds) throws NoSuchFieldException {

            order.getOrderDetails().add(new OrderDetail(fruit.getName(), fruit.getPrice().toString(), fruit.getDiscount().toString(), pounds));
        }

        public void settle(Order order) {
            if (order.getOrderDetails().size() == 0) {
                System.out.println("欢迎下次光临！");
                return ;
            }
            order.setBeforeDiscountPrice(order.getOrderDetails().stream().map(OrderDetail::getBeforeDiscountPrice).reduce(BigDecimal::add).get());
            order.setAfterDiscountPrice(order.getOrderDetails().stream().map(OrderDetail::getAfterDiscountPrice).reduce(BigDecimal::add).get());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("尊贵的会员用户：").append(this.name).append("，您好").append("\n");
            stringBuilder.append("  您本次购物明细如下").append("\n");
            stringBuilder.append("----------------------------------------------------\n");
            stringBuilder.append(padRight("名称", 8)).append(padRight("数量", 8)).append(padRight("折扣", 8)).append(padRight("优惠前金额", 8)).append(padRight("优惠后金额", 8)).append("\n");
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                stringBuilder.append(padRight(orderDetail.getName(), 8))
                        .append(padRight(orderDetail.getNums().toString(), 9))
                        .append(padRight(orderDetail.getDiscount() + "折", 9))
                        .append(padRight(orderDetail.getBeforeDiscountPrice().toString(), 12))
                        .append(padRight(orderDetail.getAfterDiscountPrice().toString(), 9))
                        .append("\n");
            }
            stringBuilder.append("----------------------------------------------------\n");
            stringBuilder.append("本次消费共").append(order.getAfterDiscountPrice()).append("元，").append("共优惠").append(order.getBeforeDiscountPrice().subtract(order.getAfterDiscountPrice())).append("元，欢迎下次光临！").append("\n");
            stringBuilder.append("支付时间：").append(LocalDateTime.now());
            System.out.println(stringBuilder);

        }

        public Person(String name) {
            this.name = name;
        }

    }

    public static String padRight(String str, int length) {
        if (str.length() >= length) {
            return str; // 若原字符串长度超过目标长度，则直接返回原字符串
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(str); // 添加原字符串内容到StringBuilder

            while (sb.length() < length) {
                sb.append(" "); // 追加足够数量的空格
            }

            return sb.toString(); // 返回包含了空格填充后的新字符串
        }
    }
}