package loader;

import business.CustomerService;

public class Loader {

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        customerService.run();
    }

}
