package data;

import entity.Dish;

import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DishesBase {

    private static DishesBase db;
    private HashMap<Integer, Dish> dishes = new HashMap<>();

    private DishesBase(HashMap<Integer, Dish> dishes) {
        this.dishes = dishes;
    }

    public Dish getDish(Integer id) {
            return dishes.get(id);
    }

    public HashMap<Integer, Dish> getDishes() {
        return dishes;
    }

    public static DishesBase getInstance() {
        if (db == null) {
            HashMap<Integer, Dish> dishesTemp = new HashMap<>();

            try {
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                Document document = documentBuilder.parse("res/menu.xml");

                NodeList dishes = document.getElementsByTagName("item");
                for (int i = 0; i < dishes.getLength(); i++) {
                    Node dish = dishes.item(i);
                    NodeList dishProps = dish.getChildNodes();
                    int id = Integer.parseInt(dishProps.item(1).getTextContent());
                    String name = dishProps.item(3).getTextContent();
                    int weight = Integer.parseInt(dishProps.item(5).getTextContent());
                    int price = Integer.parseInt(dishProps.item(7).getTextContent());
                    dishesTemp.put(id, new Dish(name, weight, price));
                }

                db = new DishesBase(dishesTemp);
            }
            catch (ParserConfigurationException e) {
                System.out.println(e);
            }
            catch (SAXException e) {
                System.out.println(e);
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
        return db;
    }
}
