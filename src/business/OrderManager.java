package business;

import data.DishesBase;
import entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OrderManager {

    private DishesBase db = DishesBase.getInstance();
    private ArrayList<Customer> customers = new ArrayList<>();
    private HashMap<Dish, Integer> dishes = new HashMap<>();


    public DishesBase getDb() {
        return db;
    }

    public HashMap<Dish, Integer> getDishes() {
        return dishes;
    }

    public void placeOrder(Customer customer) {
        customers.add(customer);
        for (Map.Entry<Dish, Integer> pair : customer.getOrderedDishes().entrySet()) {
            Dish selectedDish = pair.getKey();
            if (!dishes.containsKey(selectedDish)) {
                dishes.put(selectedDish, pair.getValue());
            } else {
                dishes.put(selectedDish, dishes.get(selectedDish) + pair.getValue());
            }
        }
    }

    public void closeOrders() {
        int totalCheck = 0;

        System.out.println("\n" + "На кухню:");
        System.out.println("+------------------------------------------------------------------+");
        System.out.printf("| %-33s | %s | %s|\n", "Блюдо", "количество", "стоимость (руб.)");
        System.out.println("|------------------------------------------------------------------|");
        for (Map.Entry<Dish, Integer> pair : dishes.entrySet()) {
            int cost = pair.getValue() * pair.getKey().getPrice();
            System.out.printf("| %-33s | %10d | %15d |\n", pair.getKey().getName(), pair.getValue(), cost);
            totalCheck += cost;
        }
        System.out.println("+------------------------------------------------------------------+");
        System.out.println("Общая сумма заказа: " + totalCheck + "\n");

        dishes.clear();

        System.out.println("+---------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-30s | %-38s | %s |\n", "Имя сотрудника", "Блюдо", "Стоимость заказа (руб.)");
        System.out.println("|---------------------------------------------------------------------------------------------------|");
        for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext(); ) {
            iterator.next().printOrder();
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            iterator.remove();
        }
    }


}
