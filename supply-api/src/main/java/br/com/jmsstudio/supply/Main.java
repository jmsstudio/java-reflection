package br.com.jmsstudio.supply;

import br.com.jmsstudio.processor.Processor;
import br.com.jmsstudio.supply.dao.ProductDao;
import br.com.jmsstudio.supply.dao.ProductDaoMock;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*
         * Possible cases:
         * /controller/method
         * /controller/method?param1=value1&param2=value2
         */

        try (Scanner s = new Scanner(System.in)) {
            System.out.print("Type the url or type <enter> to exit: ");
            String url = s.nextLine();

            final Processor processor = new Processor("br.com.jmsstudio.supply.controller");
            processor.register(ProductDao.class, ProductDaoMock.class);

            while (!url.equals("exit") && url.trim().length() > 0) {
                Object response = processor.execute(url);

                System.out.println("Response: " + response);

                System.out.print("Type the url or type <enter> to exit: ");
                url = s.nextLine();
            }
        }

    }
}
