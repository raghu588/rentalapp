import org.example.model.CheckoutData;
import org.example.model.Store;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class App {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println(Store.getTools().values());
        System.out.println(Store.getToolsCharge().values());
         CheckoutData data= Store.checkout("LADW",5,10,LocalDate.now().withMonth(7).withDayOfMonth(1));
         Store.printData(data);
         System.out.println("============================");

        data=Store.checkout("JAKR",4,50, LocalDate.parse("07/02/2015", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        Store.printData(data);

    }
}
