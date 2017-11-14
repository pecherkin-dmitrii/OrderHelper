package entity;

import java.util.HashMap;
import java.util.Map;

public class Customer {

    private String name;
    private HashMap<Dish, Integer> orderedDishes;

    public Customer(String name, HashMap<Dish, Integer> orderedDishes) {
        this.name = name;
        this.orderedDishes = orderedDishes;
    }

    public String getName() {
        return name;
    }

    public HashMap<Dish, Integer> getOrderedDishes() {
        return orderedDishes;
    }

    public void printOrder() {
        int totalCheck = 0;

        if (this.orderedDishes.size() > 0) {
            for (Map.Entry<Dish, Integer> pair : this.orderedDishes.entrySet()) {
                if (pair.getValue() == 1) {
                    totalCheck += pair.getKey().getPrice();
                    System.out.printf("| %-30s | %-38s | %23d |\n", getName(), pair.getKey().getName(), totalCheck);
                } else {
                    totalCheck += pair.getKey().getPrice() * pair.getValue();
                    System.out.printf("| %-30s | %-38s | %23d |\n", getName(), pair.getKey().getName() + " - " + pair.getValue(), totalCheck);
                }
            }
        }
    }
}
