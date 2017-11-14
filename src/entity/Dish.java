package entity;

public class Dish {

    private String name;
    private int weight;
    private int price;

    public Dish(String name, int weight, int price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (weight != dish.weight) return false;
        if (price != dish.price) return false;
        return name.equals(dish.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + weight;
        result = 31 * result + price;
        return result;
    }
}
