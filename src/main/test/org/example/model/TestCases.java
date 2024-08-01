package org.example.model;




import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class TestCases {

    @Test
    public void testCheckoutInvalidDiscount(){
        assertThrows(CheckoutException.class,()->{
            Store.checkout("JAKR",5,101, LocalDate.parse("09/03/2015", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        });
    }
    @Test
    public void testCheckout(){
        //Ecluding independence day charge for LADW
        CheckoutData data=Store.checkout("LADW",3,10, LocalDate.parse("07/02/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        assertEquals(data.getChargeableDays(),2);

        //2 weekend days(No charges), 1 independence day(aharges apply)
        data=Store.checkout("CHNS",5,25, LocalDate.parse("07/02/2015", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        assertEquals(data.getDiscountAmount(),1.12);

        //exclude weekend and labourday
        data=Store.checkout("JAKD",6,0, LocalDate.parse("09/03/2015", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        assertEquals(data.getChargeableDays(),3);

        //exlcude weekend & independence day
        data=Store.checkout("JAKR",9,0, LocalDate.parse("07/02/2015", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        assertEquals(data.getChargeableDays(),6);

        //exlcude weekend & independence day
        data=Store.checkout("JAKR",4,50, LocalDate.parse("07/02/2015", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        assertEquals(data.getChargeableDays(),1);
    }

}
