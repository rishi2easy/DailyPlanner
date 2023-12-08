
/**
 * Date creates a date object and makes
 * sure its a valid date
 * @author Rishabh Patel
 */
public class Date {

    /** The highest date allowed */
    public static final int MAX_DATE = 31;

    /** Lowest date allowed */
    public static final int MIN_DATE = 1;
    
    /** Highest date allowed for february*/
    public static final int MAX_DATE_FEB = 29;

    /** Highest date allowed for April, June, Sept, or Nov */
    public static final int MAX_DATE_AP_JU_SE_NO = 30;
    
    /** Minimum month allowed */
    public static final int MIN_MONTH = 1;
    
    /** Maximum month allowed */
    public static final int MAX_MONTH = 12;
    
    /** Minimum year allowed */
    public static final int MIN_YEAR = 1;
    
    /** int to signify january */
    public static final int JANUARY = 1;
    
    /** int to signify february */
    public static final int FEBRUARY = 2;
    
    /** int to signify march */
    public static final int MARCH = 3;
    
    /** int to signify april */
    public static final int APRIL = 4;
    
    /** int to signify may */
    public static final int MAY = 5;
    
    /** int to signify june */
    public static final int JUNE = 6;
    
    /** int to signify july */
    public static final int JULY = 7;
    
    /** int to signify august */
    public static final int AUGUST = 8;
    
    /** int to signify september */
    public static final int SEPTEMBER = 9;
    
    /** int to signify october */
    public static final int OCTOBER = 10;
    
    /** int to signify november */
    public static final int NOVEMBER = 11;
    
    /** int to signify december */
    public static final int DECEMBER = 12;
    
    /** instance for month */
    private int month;

    /** instance for day */
    private int day;

    /** instance for year */
    private int year;

    /** Mimimum year for not adding zeros */
    public static final int MIN_YEAR_ZEROS = 1000;

    /**
     * Checks if day, month, and year are valid
     * then establishes month, year, and day
     * @param month the month
     * @param day the day
     * @param year the year
     * @throws IllegalArgumentException if day is too small
     * @throws IllegalArgumentException if month is too small
     * @throws IllegalArgumentException if month is too large
     * @throws IllegalArgumentException if year is invalid
     * @throws IllegalArgumentException if day is greater than max day for given month
     * @throws IllegalArgumentException if day is greater than max day for february
     * @throws IllegalArgumentException if day is greater than max for all other months
     */
    public Date(int month, int day, int year) {
        
        if (day < MIN_DATE) {
            throw new IllegalArgumentException("Invalid day");
        } // if
        
        if (month < MIN_MONTH) {
            throw new IllegalArgumentException("Invalid month");
        } // if

        if (month > MAX_MONTH) {
            throw new IllegalArgumentException("Invalid month");
        }
        
        if (year < MIN_YEAR) {
            throw new IllegalArgumentException("Invalid year");
        } // if

        
        if ((month == JANUARY || month == MARCH || month == MAY || 
             month == JULY || month == AUGUST || month == OCTOBER || 
             month == DECEMBER) && day > MAX_DATE) {
            throw new IllegalArgumentException("Invalid day");
        } // if
        
        if ((month == FEBRUARY) && day > MAX_DATE_FEB) {
            throw new IllegalArgumentException("Invalid day");
        } // if
        
        if ((month == APRIL || month == JUNE || month == SEPTEMBER ||
             month == NOVEMBER) && day > MAX_DATE_AP_JU_SE_NO) {
            throw new IllegalArgumentException("Invalid day");
        } // if
        
        this.month = month;
        this.day = day;
        this.year = year;
    } // Date(month, day, year)

    /**
     * Makes a date with no parameters, blank constructor
     */
    public Date() {
        month = 0;
        day = 0;
        year = 0;
    } // Date()

  /**
   * Obtains the month
   * @return month gets the month
   */
    public int getMonth() {
        return month;
    } // getMonth
    
  /**
   * Obtains the day
   * @return day gets the day
   */
    public int getDay() {
        return day;
    } // getDay
    
  /**
   * Obtains the year
   * @return year gets the year
   */
    public int getYear() {
        return year;
    } // getYear
    
    /**
     * Obtains a formatted string for a date
     * @return the formatted string
     */
    public String toString() {
        String monthString = "" + month;
        String dayString = "" + day;
        String yearString = "" + year;
        
        if (month < 10) {
            monthString = "0" + month;
        } // if
        
        if (day < 10) {
            dayString = "0" + day;
        } // if
        

        if (year < MIN_YEAR_ZEROS) {
            yearString = "0" + year;
        } // else if
        else if (year < 100) {
            yearString = "00" + year;
        } // else if
        else if (year < 10) {
            yearString = "000" + year;
        }

        //Return
        return (monthString + "/" + dayString + "/" + yearString);
        
    } // toString
} // Date