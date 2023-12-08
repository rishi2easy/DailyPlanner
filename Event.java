
/**
 * Creates an object event to find
 * location, description, and location
 * @author Rishabh Patel
 */
public class Event {

    /** Instance for name of event */
    private String name;

    /** Instance for day of event */
    private Date dayOf;

    /** Instance for time of event*/
    private int time;

    /** Instance for description of event */
    private String description;

    /** Instance for location of event */
    private String location;

    /** Max time allowed for an event */
    public static final int MAX_TIME = 2359;

    /** Max hour allowed for an event */
    public static final int MAX_HOUR = 23;

    /** Min hour allowed for an event */
    public static final int MIN_HOUR = 0;

    /** Max minute allowed for an event */
    public static final int MAX_MINUTE = 59;

    /** Min minute allowed for an event */
    public static final int MIN_MINUTE = 0;

    /** Start of string */
    public static final int START_STRING = 0;

    /** End hour */
    public static final int END_HOUR = 2;

    /** End minute */
    public static final int END_MINUTE = 4;

    /**
     * Creates event object with day, time,
     * description, and location
     * @param dayOf day of event
     * @param time time of event
     * @param description description of event
     * @param location location of event
     */
    public Event(Date dayOf, int time, String description, String location){
        this.dayOf = dayOf;
        this.time = time;
        this.description = description;
        this.location = location;
    }

    /** Intializes dayof, time, description, and location */
    public Event() {
        dayOf = new Date();
        time = 0;
        description = "";
        location = "";
    }

    /**
     * toString to format the result of the event
     * @return result the formatted string
     */
    public String toString() {
        String result = dayOf.toString() + " " + time(time) + 
                        "\n" + description + " at " + location;
        return result;
    }
    
    /** 
     * Obtains the name
     * @return name of event
     */
    public String getName() {
        return name;
    }

    /**
     * Obtains the dayOf
     * @return day of event
     */
    public Date getDayOf() {
        return dayOf;
    }

    /**
     * Obtains the time
     * @return time of event
     */
    public int getTime() {
        return time;
    }

    /**
     * Obtains the description
     * @return description of event
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtains the location
     * @return location of event
     */
    public String getLocation() {
        return location;
    }

    /**
     * Formats time
     * @param time time of event
     * @return formatted time as a string
     * @throws IllegalArgumentException if time is invalid
     * @throws IllegalArgumentException if formatted hour is invalid
     * @throws IllegalArgumentException if formatted minute is invalid
     */
    public String time(int time) {
        if (time > MAX_TIME) {
            throw new IllegalArgumentException("Invalid time");
        }

        String result = "";
        String formattedTime = Integer.toString(time);
        String hour = formattedTime.substring(START_STRING, END_HOUR);
        String minute = formattedTime.substring(END_HOUR, END_MINUTE);

        if (Integer.parseInt(hour) > MAX_HOUR || Integer.parseInt(hour) < MIN_HOUR) {
            throw new IllegalArgumentException("Invalid Hour");
        }
        if (Integer.parseInt(minute) > MAX_MINUTE || Integer.parseInt(minute) < MIN_MINUTE) {
            throw new IllegalArgumentException("Invalid Minute");
        }
        
        result = hour + ":" + minute;
        if (time < 0) {
            result = "All Day";
        }
        return result;
    }
}   