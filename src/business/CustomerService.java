package business;

import entity.Customer;
import entity.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CustomerService {

    private OrderManager manager;
    private BufferedReader reader;
    private HashMap<Dish, Integer> dishes;

    public CustomerService() {
        manager = new OrderManager();
        reader = new BufferedReader(new InputStreamReader(System.in));
        dishes = new HashMap<>();
    }

    private int readNumber() throws IOException {
        int result = -1;
        String line;

        do {
            line = reader.readLine().trim();
            if (line.length() == 0 || !line.matches("\\d+")) {
                System.out.println("Неверный ввод!");
            } else {
                result = Integer.parseInt(line);
            }
        } while (result < 0);
        return result;
    }

    private String readName() throws IOException {
        String name = "";
        boolean incorrect;
        do {
            name = reader.readLine().trim();
            incorrect = (name.length() == 0 || !name.matches("[a-zA-Zа-яА-ЯёЁ]+[.\\s]*[a-zA-Zа-яА-ЯёЁ]+[.\\s]*"));
            if (incorrect) {
                System.out.println("Неверный ввод!");
            }
        } while (incorrect);
        return name;
    }

    private void addDish(int number){
        if (!manager.getDb().getDishes().containsKey(number)) {
            System.out.println("Блюдо отсутствует в меню!");
        } else {
            Dish selectedDish = manager.getDb().getDish(number);
            if (!dishes.containsKey(selectedDish))
                dishes.put(selectedDish, 1);
            else
                dishes.put(selectedDish, dishes.get(selectedDish) + 1);
            System.out.println("Принято!");
        }
    }

    private void openNewOrder(String name) throws IOException {
        while (true) {
            System.out.println("Введите номера блюд, либо \"0\" для закрытия заказа:");
            int dishNumber = readNumber();

            if (dishNumber == 0) {
                if (dishes.isEmpty()) {
                    System.out.println("Ни одно блюдо не добавлено!");
                } else {
                    HashMap<Dish, Integer> orderedDishes = (HashMap<Dish, Integer>) dishes.clone();
                    manager.placeOrder(new Customer(name, orderedDishes));
                    dishes.clear();
                    break;
                }
            } else {
                addDish(dishNumber);
            }
        }
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Введите имя клиента, либо \"стоп\" для отправки заказов в работу:");
                String name = readName();

                if (name.equals("стоп")) {
                    if (manager.getDishes().isEmpty()) {
                        System.out.println("Нет заказов для обработки!");
                    } else {
                        manager.closeOrders();
                    }
                    continue;
                }
                openNewOrder(name);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}