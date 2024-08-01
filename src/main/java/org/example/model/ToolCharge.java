package org.example.model;

public class ToolCharge {
    private String toolType;
    private Double dailyCharge;
    private String weekdayCharge;
    private String weekendCharge;
    private String holidayCharge;

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public Double getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(Double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public String getWeekdayCharge() {
        return weekdayCharge;
    }

    public void setWeekdayCharge(String weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public String getWeekendCharge() {
        return weekendCharge;
    }

    public void setWeekendCharge(String weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public String getHolidayCharge() {
        return holidayCharge;
    }

    public void setHolidayCharge(String holidayCharge) {
        this.holidayCharge = holidayCharge;
    }

    @Override
    public String toString() {
        return "{" +
                "toolType='" + toolType + '\'' +
                ", dailyCharge=" + dailyCharge +
                ", weekdayCharge='" + weekdayCharge + '\'' +
                ", weekendCharge='" + weekendCharge + '\'' +
                ", holidayCharge='" + holidayCharge + '\'' +
                '}';
    }
}
