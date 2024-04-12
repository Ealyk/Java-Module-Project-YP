import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console.console();
    }


}
class Product{

    private String name;
    private double price;
    Product(String name, double price){
        this.name = name;
        this.price = price;
    }
    public String getName(){
        return name;
    }
    public double getPrice() {
        return price;
    }
}
class Console{
    static int countHuman;
    static Scanner scanner = new Scanner(System.in);
    static void console() {
        System.out.println("Привет! я могу помочь рассчитать ваши расходы");
        System.out.println("Для начала мне нужно знать сколько человек в компании.");
        while(true){
            System.out.println("Введи сколько вас собралось: ");
            if(!scanner.hasNextInt()){
                System.out.println("Попробуй ввести число");
            }
            else {
                countHuman = scanner.nextInt();
                if(countHuman<1){
                    System.out.println("Попробуй пересчитать");
                }
                else if (countHuman>1){
                    System.out.println("Итак вас "+countHuman+" Далее тебе нужно ввести название товара и его стоимость\nКогда закончишь вводить товар напиши слово 'Завершить' и я посчитаю сколько необходимо заплатить каждому");
                    new Calculator(countHuman);
                    break;
                }
                else {
                    System.out.println("Похоже, что твои друзья не смогли сегодня прийти. Придётся самому за всё платить\n Возможно ты забыл про кого-то попробуй пересчитать");
                }
            }
        }


    }
    public static void setHumans(){
        System.out.println(getHuman(countHuman));
    }
    public static int getHuman(int countHuman){
        return countHuman;
    }
}
class Calculator{
    static private int humans;
    static List<Product> listProduct = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    Calculator(int humans){
        this.humans = humans;
        chooseProduct();

    }
    static void chooseProduct() {
        String name = "start";
        while (!name.equalsIgnoreCase("Завершить")){
            System.out.println("Введи название товара: ");
            name = new String(scanner.next());
            if(name.equalsIgnoreCase("Завершить")){
                break;
            }
            System.out.println("Введи цену на товар: ");
            if(!scanner.hasNextDouble()){
                System.out.println("Попробуй ввести товар заного");
            }
            else{
                double price = scanner.nextDouble();
                if (price>0){
                    listProduct.add(new Product(name, price));
                    System.out.println("Товар добавлен");
                }
                else {
                    System.out.println("Попробуй ввести товар заного");
                }
            }
        }
        scanner.close();
        System.out.println(calculate(humans));
    }
    static double calculate(int humans){
        double sum = 0;
        for(Product prices:listProduct){
            sum =sum + prices.getPrice();
        }
        return sum/humans;
    }

}
