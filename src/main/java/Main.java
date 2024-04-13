import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
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
            String isCount = scanner.next();
            try {
                countHuman = Integer.parseInt(isCount);
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
            catch (NumberFormatException ime){
                System.out.println("Попробуй ввести число");
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
        System.out.println("Если у цены товара есть дробная часть отдели её символом '.' Например: '1.45'");
        while (!name.equalsIgnoreCase("Завершить")){
            System.out.println("Введи название товара: ");
            name = new String(scanner.next());
            if(name.equalsIgnoreCase("Завершить")){
                break;
            }

            while(true){
                System.out.println("Введи цену на товар: ");
                String isPrice = scanner.next();
                try {
                    double price = Double.parseDouble(isPrice);
                    if (price > 0) {
                        listProduct.add(new Product(name, price));
                        System.out.println("Товар добавлен");
                        break;
                    }
                    else {
                        System.out.println("Кажется ты что-то перепутал. Цена не может быть отрицательной попробуй ввести её ещё раз");
                    }
                }
                catch (NumberFormatException ime){
                    System.out.println("Попробуй ввести цену на товар заного, согласно требованиям");
                }
            }
        }
        scanner.close();
        String prod;
        System.out.println("Добавленные товары: ");
        for(Product product: listProduct){
            prod = product.getName()+" - "+String.format("%.2f", product.getPrice())+Formater.formater(product.getPrice());
            System.out.println(prod);
        }
        System.out.println("Итого с каждого по: "+ String.format("%.2f",calculate(humans))+Formater.formater(calculate(humans)));
    }
    static double calculate(int humans){
        double sum = 0;
        for(Product prices:listProduct){
            sum =sum + prices.getPrice();
        }
        return sum/humans;
    }


}
class Formater{

    static String formater(double price){
        int priceRound = (int) Math.floor(price);
        if(priceRound%10==1 && priceRound/10!=1 || priceRound == 1){
            return " рубль";
        }
        else if(priceRound%10>1 && priceRound%10<5 && priceRound/10!=1 || priceRound>1 && priceRound<5){
            return " рубля";
        }
        else{
            return "рублей";
        }
    }
}