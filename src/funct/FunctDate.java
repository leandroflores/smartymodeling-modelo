package funct;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Class of Functions <b>FunctDate</b>.</p>
 * <p>Class responsible for operations involving <b>Dates</b> and <b>Hours</b>.</p>
 * @author Leandro
 * @since  14/01/2019
 * @see    java.util.Date
 * @see    java.sql.Time
 */
public class FunctDate {
    private final String[] MONTH_NAMES = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", 
                                                 "MAY", "JUNE", "JULY", "AUGUST", 
                                                 "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
    private final String[] DAY_NAMES   = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY",
                                                 "THURSDAY", "FRIDAY", "SATURDAY"};
    
    /**
     * Method responsible for returning a System Current Date.
     * @return System Current Date.
     */
    public Date getCurrentDate() {
        return new Date();
    }
    
    /**
     * Method responsible for returning the System Current Formatted Date.
     * @return System Current Formatted Date.
     */
    public String getCurrentFormattedDate() {
        return this.getFormattedDate(this.getCurrentDate());
    }
    
    /**
     * Method responsible for returning the System Current US Formatted Date.
     * @return System Current US Formatted Date.
     */
    public String getCurrentUSFormattedDate() {
        return this.getFormattedUSDate(this.getCurrentDate());
    }
    
    /**
     * Method responsible for returning a System Current Time.
     * @return System Current Time.
     */
    public Time getCurrentTime() {
        return new Time(this.getCurrentDate().getTime());
    }
    
    /**
     * Method responsible for create a new Date in "dd/MM/yyyy" format.
     * @param  string String in "dd/MM/yyyy" format.
     * @return New Date.
     */
    public Date createDate(String string) {
        try {
            return new Date(new SimpleDateFormat("dd/MM/yyyy").parse(string).getTime());
        } catch (ParseException exception) {}
        return null;
    }
    
    /**
     * Method responsible for create a new Date in "dd/MM/yy" format.
     * @param  string String in "dd/MM/yy" format.
     * @return New Date.
     */
    public Date createDate2(String string) {
        try {
            return new Date(new SimpleDateFormat("dd/MM/yy").parse(string).getTime());
        } catch (ParseException exception) {}
        return null;
    }
    
    /**
     * Method responsible for create a new Time object.
     * @param  hours  Hour (0 - 23).
     * @param  minutes Minute (0 - 59).
     * @param  seconds Second (0 - 59).
     * @return New Time.
     */
    public Time createTime(int hours, int minutes, int seconds) {
        return new Time(hours, minutes, seconds);
    }
    
    /**
     * Method responsible for returning a String with a Date in "dd/MM/yyyy" format.
     * @param  date Date.
     * @return String with a Date formatted.
     */
    public String getFormattedDate(Date date) {
        if (date != null)
            return new SimpleDateFormat("dd/MM/yyyy").format(date);
        return "";
    }
    
    /**
     * Method responsible for returning a String with a Date in "yyyy-MM-dd" format.
     * @param  date Date.
     * @return String with a US Date formatted.
     */
    public String getFormattedUSDate(Date date) {
        if (date != null)
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        return "";
    }
    
    /**
     * Method responsible for returning a String by Format and Date.
     * @param  format Format.
     * @param  date Date.
     * @return String with a Date in a Format.
     */
    public String getFormattedDate(String format, Date date) {
        if (date != null)
            return new SimpleDateFormat(format).format(date);
        return "";
    }
    
    /**
     * Method responsible for returning a String with a Date in "dd/MM/yy" format.
     * @param  date  Date.
     * @return String with a Date formatted.
     */
    public String getSampleFormattedDate(Date date) {
        if (date != null)
            return new SimpleDateFormat("dd/MM/yy").format(date);
        return "";
    }
    
    /**
     * Method responsible for returning the Year of Date.
     * @param  date Date.
     * @return Year of Date.
     */
    public String getYear(Date date) {
        if (date != null)
            return new SimpleDateFormat("yyyy").format(date);
        return "";
    }
    
    /**
     * Method responsible for returning the Month of a Date.
     * @param  date Date.
     * @return Month of Date.
     */
    public String getMonth(Date date) {
        if (date != null)
            return new SimpleDateFormat("MM").format(date);
        return "";
    }
    
    /**
     * Method responsible for returning the written Date.
     * @param  date Date.
     * @return String with written Date.
     */
    public String getDateName(Date date) {
        String string  = this.getDayName(date.getDay()) + ", ";
               string += this.getMonthName(date.getMonth()) + " ";
               string += date.getDate() + " ";
               string += date.getYear();
        return string;
    }
    
    /**
     * Method responsible for returning the written Day Name.
     * @param  day Week Day Index (0 - 6)
     * @return Written Day Name.
     */
    public String getDayName(int day) {
        if (day >= 0 && day < 7)
            return DAY_NAMES[day];
        return "";
    }
    
    /**
     * Method responsible for returning the Month Name.
     * @param  mes Index Month (0 - 11)
     * @return Month Name.
     */
    public String getMonthName(int mes) {
        if (mes >= 0 && mes <= 11)
            return MONTH_NAMES[mes];
        return "";
    }
    
    /**
     * Method responsible for returning Current Information.
     * @return Current Information.
     */
    public String getCurrentInformation() {
        return this.getFormattedDate(new Date()).replace("/", "-") 
             + "-"
             + this.getCurrentTime().toString().replace(":", "-");
    }
}