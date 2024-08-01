package org.example.model;

import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.*;

public class Store {
    private static Map<String,Tool> availableTools=new LinkedHashMap<>();
    private static Map<String,ToolCharge> toolCharges=new HashMap<>();
    static {

        //load data into maps on class load

        //Prepare and Add Tools
        Tool t1=new Tool();
        t1.setToolCode("CHNS");
        t1.setToolType("Chainsaw");
        t1.setToolBrand("Stihl");
        availableTools.put(t1.getToolCode(),t1);

        Tool t2=new Tool();
        t2.setToolCode("LADW");
        t2.setToolType("Ladder");
        t2.setToolBrand("Werner");
        availableTools.put(t2.getToolCode(),t2);

        Tool t3=new Tool();
        t3.setToolCode("JAKD");
        t3.setToolType("Jackhammer");
        t3.setToolBrand("Dewalt");
        availableTools.put(t3.getToolCode(),t3);

        Tool t4=new Tool();
        t4.setToolCode("JAKR");
        t4.setToolType("Jackhammer");
        t4.setToolBrand("Ridgid");
        availableTools.put(t4.getToolCode(),t4);




        //Prepare and add ToolCharges
        ToolCharge tc1=new ToolCharge();
        tc1.setToolType("Ladder");
        tc1.setDailyCharge(1.99);
        tc1.setWeekdayCharge("Yes");
        tc1.setWeekendCharge("Yes");
        tc1.setHolidayCharge("No");
        toolCharges.put(tc1.getToolType(),tc1);

        ToolCharge tc2=new ToolCharge();
        tc2.setToolType("Chainsaw");
        tc2.setDailyCharge(1.49);
        tc2.setWeekdayCharge("Yes");
        tc2.setWeekendCharge("No");
        tc2.setHolidayCharge("Yes");
        toolCharges.put(tc2.getToolType(),tc2);

        ToolCharge tc3=new ToolCharge();
        tc3.setToolType("Jackhammer");
        tc3.setDailyCharge(2.99);
        tc3.setWeekdayCharge("Yes");
        tc3.setWeekendCharge("No");
        tc3.setHolidayCharge("No");
        toolCharges.put(tc3.getToolType(),tc3);
    }
    public static Map<String,Tool> getTools(){
        return availableTools;
    }

    public static Map<String,ToolCharge> getToolsCharge(){
        return toolCharges;
    }

    //Method to check is weekday
    private static boolean isWeekDay(LocalDate date){
        DayOfWeek dayOfWeek= date.getDayOfWeek();
        //WeekDay
        if(dayOfWeek==DayOfWeek.MONDAY || dayOfWeek==DayOfWeek.TUESDAY ||dayOfWeek==DayOfWeek.WEDNESDAY|| dayOfWeek==DayOfWeek.THURSDAY || dayOfWeek==DayOfWeek.FRIDAY){
            return true;
        }
        return false;
    }

    //method to check is weekend
    private static boolean isWeekendDay(LocalDate date){
        DayOfWeek dayOfWeek= date.getDayOfWeek();
        
        if(dayOfWeek==DayOfWeek.SATURDAY || dayOfWeek==DayOfWeek.SUNDAY){
            return true;
        }
        return false;
    }

    //Method to check is holiday
    private static boolean isHoliday(LocalDate date){

        LocalDate labourDay=LocalDate.now().withYear(date.getYear()).withMonth(9).withDayOfMonth(1);//setting to sep 1st based on checkout date & year
        if(labourDay.getDayOfWeek()!=DayOfWeek.MONDAY){
            //setting to next Monday
            labourDay= labourDay.plusDays(7).with(ChronoField.DAY_OF_WEEK,DayOfWeek.MONDAY.getValue());
        }



        LocalDate independenceDay=LocalDate.now().withYear(date.getYear()).withMonth(7).withDayOfMonth(4);//preparing independence day based on checkout date
        if(independenceDay.getDayOfWeek()==DayOfWeek.SATURDAY){
            //If falls on saturday consider holiday as prior day
            independenceDay =independenceDay.minusDays(1);
        }else if(independenceDay.getDayOfWeek()==DayOfWeek.SUNDAY){
            //If falls on saturday consider holiday as next day
            independenceDay= independenceDay.plusDays(1);
        }

        DayOfWeek dayOfWeek= date.getDayOfWeek();
        if(date.equals(labourDay) || date.equals(independenceDay)){
            return true;
        }

        return false;
    }
    public static CheckoutData checkout(String toolCode, int rentalDayCount,double discountPercent,LocalDate checkoutDate){
       //invalid rental days
        if(rentalDayCount<1){
            throw new CheckoutException("Rental Day count is 1 or greater");
        }
        //invalid discount percent
        if(discountPercent<0.0 || discountPercent>100.0){
            throw new CheckoutException("Discount percent should be in the range 0-100");
        }

        //invalid tool
        if(!availableTools.containsKey(toolCode)){
            throw new CheckoutException("Tool with the code '"+toolCode+"' is not available.");
        }
        Tool tool=availableTools.get(toolCode);
       ToolCharge tc= toolCharges.get(tool.getToolType());
       // LocalDate checkoutDate=LocalDate.now();
        LocalDate dueDate=LocalDate.from(checkoutDate).plusDays(rentalDayCount-1);

        LocalDate startDate=LocalDate.from(checkoutDate);
        LocalDate endDate=LocalDate.from(dueDate);
        int chargeableDays=0;
        //iterate loop between checkout  and due days
        while(!startDate.isAfter(endDate)){

            //check for holiday
            if(isHoliday(startDate)){
                if(tc.getHolidayCharge().equals("Yes")){//consider as chargeable day if charge is applicable
                    chargeableDays++;
                }
            }
            else if(isWeekDay(startDate)){ //check for week day
                if(tc.getWeekdayCharge().equals("Yes")){ //consider as chargeable day if charge is applicable
                    chargeableDays++;
                }
            } else if(isWeekendDay(startDate)){ //check for weekend
                if(tc.getWeekendCharge().equals("Yes")){ //consider as chargeable day if charge is applicable
                    chargeableDays++;
                }
            }
           startDate= startDate.plusDays(1);
        }

        Double preDiscountCharge=Math.round( tc.getDailyCharge()*chargeableDays *100.0)/100.0; ////amount with out discount with 2 decimals
        Double discountAmpunt=Math.round((preDiscountCharge*discountPercent/100.00)*100.0)/100.0; // discount amount with 2 decimals
        Double postDiscountMount=preDiscountCharge - discountAmpunt;// final charge

        //preparing data model
        CheckoutData data=new CheckoutData();
        data.setToolCode(tool.getToolCode());;
        data.setToolType(tool.getToolType());
        data.setToolBrand(tool.getToolBrand());
        data.setRentalDays(rentalDayCount);
        data.setCheckoutDate(checkoutDate);
        data.setDueDate(dueDate);
        data.setDailyRentalCharge(tc.getDailyCharge());
        data.setRentalDays(rentalDayCount);
        data.setChargeableDays(chargeableDays);
        data.setPreDiscountChharge(preDiscountCharge);
        data.setDiscountPercent(discountPercent);
        data.setDiscountAmount(discountAmpunt);
        data.setFinalCharge(postDiscountMount);
        return data;


    }

    /**
     * method to print agreement
     * @param data
     */
    public static void printData(CheckoutData data){
        NumberFormat currencyFormat= NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat percentFormat=NumberFormat.getPercentInstance(Locale.US);
        System.out.println("Tool code          :"+data.getToolCode());
        System.out.println("Tool type          :"+data.getToolType());
        System.out.println("Tool brand         :"+data.getToolBrand());
        System.out.println("Rental days        :"+data.getRentalDays());
        System.out.println("Check out date     :"+data.getCheckoutDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        System.out.println("Due date           :"+data.getDueDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        System.out.println("Daily rental charge:"+currencyFormat.format(data.getDailyRentalCharge() ));
        System.out.println("Charge days        :"+data.getChargeableDays());
        System.out.println("Pre-discount charge:"+currencyFormat.format(data.getPreDiscountChharge()) );
        System.out.println("Discount percent   :"+percentFormat.format(data.getDiscountPercent()/100.0));
        System.out.println("Discount amount    :"+currencyFormat.format(data.getDiscountAmount()) );
        System.out.println("Final Charge       :"+currencyFormat.format(data.getFinalCharge()) );
    }

}
