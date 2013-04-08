package cepbox;

import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameMappingStrategy;
import cepbox.listener.OrderEventListener;
import cepbox.model.OrderEvent;
import com.espertech.esper.client.*;

import java.io.StringReader;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        System.out.println( "Hello World!" );

        Configuration config = new Configuration();
        config.addEventTypeAutoName(OrderEvent.class.getPackage().getName());
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);

        String epl = "select avg(price) from OrderEvent.win:time(1 sec)";
        //String epl = "select avg(price) from OrderEvent.win:keepall()";
        EPStatement statement = epService.getEPAdministrator().createEPL(epl);

        OrderEventListener listener = new OrderEventListener();
        statement.addListener(listener);

        CsvToBean<OrderEvent> bean = new CsvToBean<OrderEvent>();

        //Define strategy
        HeaderColumnNameMappingStrategy<OrderEvent> strategy =
                new HeaderColumnNameMappingStrategy<OrderEvent>();
        strategy.setType(OrderEvent.class);

        //Parse the CSV
        String csv = "symbol, price\n" +
                "IBM, 10\n" +
                "IBM, 12\n";
        List<OrderEvent> orders = bean.parse(strategy, new StringReader(csv));
        System.out.println(orders);

        int price = 10;
        while (true) {
            OrderEvent event = new OrderEvent("IBM", ++price);
            epService.getEPRuntime().sendEvent(event);
            Thread.sleep(5000);
        }
    }
}
