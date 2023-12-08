import java.util.*;
import java.io.*;
import java.nio.file.*;

/**
 * The file which contains main. It is responsible for 
 * looping through the options for the user, and then responding to their input
 * @author Rishabh Patel
 */

public class EventPlanner {

    
    /** A variable to hold how many args should come in */
    public static final int REQUIRED_ARGS = 2;

    /** A variable used for the maximum date */
    public static final int MAX_DATE = 31;

    /** A variable used for the maximum date */
    public static final int MIN_DATE = 1;
    
    /** A variable used for the maximum date in february */
    public static final int MAX_DATE_FEB = 29;

    /** A variable used for the maximum date in the months within the name */
    public static final int MAX_DATE_AP_JU_SE_NO = 30;
    
    /** A variable used for the minimum month */
    public static final int MIN_MONTH = 1;
    
    /** A variable used for the maximum month */
    public static final int MAX_MONTH = 12;
    
    /** A variable used for the minimm year */
    public static final int MIN_YEAR = 1;

    /** A variable used for the maximum time */
    public static final int MAX_TIME = 2359;

    /** A variable used for the minimum hour in a day. */
    public static final int MIN_HOUR = 0;

    /** A variable used for the maximum hour in a day. */
    public static final int MAX_HOUR = 23;

    /** A variable used for the minimum minute in an hour. */
    public static final int MIN_MINUTE = 0;

    /** A variable used for the maximum minute in an hour. */
    public static final int MAX_MINUTE = 59;

    /** Constant representing the month of January. */
    public static final int JANUARY = 1;

    /** Constant representing the month of February. */
    public static final int FEBRUARY = 2;

    /** Constant representing the month of March. */
    public static final int MARCH = 3;

    /** Constant representing the month of April. */
    public static final int APRIL = 4;

    /** Constant representing the month of May. */
    public static final int MAY = 5;

    /** Constant representing the month of June. */
    public static final int JUNE = 6;

    /** Constant representing the month of July. */
    public static final int JULY = 7;

    /** Constant representing the month of August. */
    public static final int AUGUST = 8;

    /** Constant representing the month of September. */
    public static final int SEPTEMBER = 9;

    /** Constant representing the month of October. */
    public static final int OCTOBER = 10;

    /** Constant representing the month of November. */
    public static final int NOVEMBER = 11;

    /** Constant representing the month of December. */
    public static final int DECEMBER = 12;

    /** Constant representing the beginning of a string */
    public static final int STRING_START = 0;

    /** Constant representing the index of the end of the year in input file */
    public static final int END_YEAR = 4;

    /** Constant representing the index of the end of the month in input file */
    public static final int END_MONTH = 6;

    /** Constant representing the index of the end of the day in input file */
    public static final int END_DAY = 8;

    /**
     * Main method which promps user in a loop for options, 
     * and based on those options aids in constructing a
     * event planner file
     * 
     * @param args arguments on command line
     */
    public static void main(String[] args) {
        
        //Checking if there are two file names
        if (args.length != REQUIRED_ARGS) {
            System.out.println("Usage: java -cp bin EventPlanner.java" +
                               " CurrentEventFile.csv NewEventFile.csv");
            System.exit(1);
        }
        
        //Making a scanner and print writer for the files
        Scanner in = null;

        //Print writer used to make output file
        PrintWriter out = null;
        
        //Setting the scanner to work in the input file
        try {
            in = new Scanner(new FileInputStream(args[0]));
        } // try
        catch (FileNotFoundException e) {
            System.out.println("Unable to access input file: " + args[0]);
            System.exit(1);
        } // catch

        //Checking if output file exists
        //Scanner for user input
        Scanner scnr = new Scanner(System.in);
        //New path
        Path path = Path.of(args[1]);
        //Asking to override existing files
        if (Files.exists(path)) {
            System.out.print(args[1] + " exists - OK to overwrite (y,n)?: ");
            //User's answer
            String answer = scnr.next();
            if (!answer.toLowerCase().startsWith("y")) {
                System.exit(1);
            } // if
        } // if
        
        //Creating a print writer to write the output file
        try {
            out = new PrintWriter(new FileOutputStream(args[1]));
        } // try
        catch (FileNotFoundException e) {
            System.out.println("Cannot create event file");
            System.exit(1);
        } // catch
        
        //Variable storing the number of lines
        int numberOfLines;
        numberOfLines = getNumberOfLines(in);
        
        //Put the scanner back at the top of the parks file
        try {
            in = new Scanner(new FileInputStream(args[0]));
        } // try
        catch (FileNotFoundException e) {
            System.out.println("Unable to access input file: " + args[0]);
            System.exit(1);
        } // catch

        //Array to store events
        Event[] events = new Event[numberOfLines];
        
        //Counter
        int counter = 0;
        
        while (in.hasNextLine()) {
            
            //The current line
            String line = in.nextLine();
            //Scanner for the current line
            Scanner lineScan = new Scanner(line);
            lineScan.useDelimiter(",");
            
            try {

                //Year on current line of file
                int fileYear;
                //Month on current line of file
                int fileMonth;
                //Day on current line of file
                int fileDay;
                //Time on current line of file
                int fileTime;
                //Description on current line of file
                String fileDescription;
                //Location on current line of file
                String fileLocation;
                //String for date on current line of file
                String fileDate = lineScan.next();
                fileYear = Integer.parseInt(fileDate.substring(STRING_START,END_YEAR));
                fileMonth = Integer.parseInt(fileDate.substring(END_YEAR, END_MONTH)); 
                fileDay = Integer.parseInt(fileDate.substring(END_MONTH, END_DAY)); 
                
                //Date on current line of file
                Date fileDateForEvent = new Date(fileMonth, fileDay, fileYear);
                
                fileTime = lineScan.nextInt();
                fileDescription = lineScan.next();
                fileLocation = lineScan.next();

                events[counter] = new Event(fileDateForEvent, fileTime, 
                                            fileDescription, fileLocation);
            } // try
                
            catch (InputMismatchException e) {
                System.out.println("Invalid input file format/content");
                System.exit(1);
            } // catch
            

            //Increase counter
            counter++;
            //Close line scanner
            lineScan.close();

        } // while

        //User's option
        String option = "";

        //Big while loop, user options
        while (!option.equalsIgnoreCase("Q")) {
        
            //Display menu
            System.out.println();
            displayMenu();
            
            // Option
            System.out.print("Option: ");
            option = scnr.next();
            
            //View events on one day
            if (option.equalsIgnoreCase("V")) {
                
                //Month the user wants
                int wantedMonth = 0;
                //Day the user wants
                int wantedDay = 0;
                //Year the user wants
                int wantedYear = 0;

                //Store and check user's MDY
                try {
                    System.out.print("Enter month(as seen in a date as a number): ");
                    wantedMonth = scnr.nextInt();
                    System.out.print("Enter day(as seen in a date as a number): ");
                    wantedDay = scnr.nextInt();
                    System.out.print("Enter year(as seen in a date as a number): ");
                    wantedYear = scnr.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("That isn't a number. Please enter integer values");
                    continue;
                }
                
                if (checkDate(wantedMonth, wantedDay, wantedYear) == false) {
                    System.out.println("Invalid date");
                    continue;
                }

                //Number representing value of the date the user wants to investigate
                int wantedId = (wantedMonth * 10) + (wantedYear * 100) + (wantedDay);

                //Output all events on given day
                System.out.println();
                for (int i = 0; i < events.length; i++) {
                    //Date of current event being checked
                    Date currentEventDate = events[i].getDayOf();
                    //Day of current event being checked
                    int currentEventDay = currentEventDate.getDay();
                    //Month of current event being checked
                    int currentEventMonth = currentEventDate.getMonth();
                    //Year of current event being checked
                    int currentEventYear = currentEventDate.getYear();
                    //"ID" of current event being checked
                    int eventId = (currentEventYear * 100 + 
                                   currentEventMonth * 10 + currentEventDay);
                    
                    //If they match, print it
                    if (eventId == wantedId) {
                        System.out.println(events[i]);
                    } // if
                } // for
            } // if

            //View events in a range of dates
            else if (option.equalsIgnoreCase("R")) {

                //Month of date which is the lower bound
                int wantedMonth = 0;
                //Day of date which is the lower bound
                int wantedDay = 0;
                //Year of date which is the lower bound
                int wantedYear = 0;
                //Month of date which is the upper bound
                int wantedMonthTwo = 0;
                //Day of date which is the upper bound
                int wantedDayTwo = 0;
                //Year of date which is the upper bound
                int wantedYearTwo = 0;

                //Gather user input and check for a valid date
                try {
                    System.out.print("Enter first month(as seen in a date as a number): ");
                    wantedMonth = scnr.nextInt();
                    System.out.print("Enter first day(as seen in a date as a number): ");
                    wantedDay = scnr.nextInt();
                    System.out.print("Enter first year(as seen in a date as a number): ");
                    wantedYear = scnr.nextInt();
                }

                catch (InputMismatchException e) {
                    System.out.println("That isn't a number. Please enter integer values");
                    continue;
                }

                if (checkDate(wantedMonth, wantedDay, wantedYear) == false) {
                    System.out.println("Invalid date");
                    continue;
                }

                //Gather user input and check for a valid date
                try {
                    System.out.print("Enter second month(as seen in a date as a number): ");
                    wantedMonthTwo = scnr.nextInt();
                    System.out.print("Enter second day(as seen in a date as a number): ");
                    wantedDayTwo = scnr.nextInt();
                    System.out.print("Enter second year(as seen in a date as a number): ");
                    wantedYearTwo = scnr.nextInt();
                }

                catch (InputMismatchException e) {
                    System.out.println("That isn't a number. Please enter integer values");
                    continue;
                }

                if (checkDate(wantedMonthTwo, wantedDayTwo, wantedYearTwo) == false) {
                    System.out.println("Invalid date");
                    continue;
                }

                //"ID" representing lower date
                int wantedId = (wantedMonth * 10) + (wantedYear * 100) + (wantedDay);
                //"ID" representing higher/second date
                int wantedIdTwo = (wantedMonthTwo * 10) + (wantedYearTwo * 100) + (wantedDayTwo);

                System.out.println();
                for (int i = 0; i < events.length; i++) {
                    
                    //Date of current event being checked
                    Date currentEventDate = events[i].getDayOf();
                    //Day of current event being checked
                    int currentEventDay = currentEventDate.getDay();
                    //Month of current event being checked
                    int currentEventMonth = currentEventDate.getMonth();
                    //Year of current event being checked
                    int currentEventYear = currentEventDate.getYear();
                    //"ID" of current event being checked

                    //If "ID" is within range, then print
                    int eventId = (currentEventYear * 100 + 
                                   currentEventMonth * 10 + currentEventDay);
                    if (eventId >= wantedId || eventId < wantedIdTwo) {

                        System.out.println(events[i]);

                    } // if
                } // for
            }
            
            else if (option.equalsIgnoreCase("A")) {
                
                //Month for event user wants to add
                int userAddMonth = 0;
                //Day for event user wants to add
                int userAddDay = 0;
                //Year for event user wants to add
                int userAddYear = 0;
                //Time for event user wants to add
                int userAddTime = 0;
                //Description for event user wants to add
                String userAddDescription = "";
                //Location for event user wants to add
                String userAddLocation = "";

                //Date, time, description, location
                System.out.println("Month of event(as seen in a date as a number): ");
                try {
                    userAddMonth = scnr.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("That isn't a number. Please enter integer values");
                    continue;
                }
                
                System.out.println("Day of event(as seen in a date as a number): ");
                try {
                    userAddDay = scnr.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("That isn't a number. Please enter integer values");
                    continue;
                }

                System.out.println("Year of event(as seen in a date as a number): ");
                try {
                    userAddYear = scnr.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("That isn't a number. Please enter integer values");
                    continue;
                }

                if (checkDate(userAddMonth, userAddDay, userAddYear) == false) {
                    System.out.println("Invalid date");
                    continue;
                }

                //Date for event user wants to add
                Date userAddDate = new Date(userAddMonth, userAddDay, userAddYear);

                System.out.println("Time of event(give a number without a colon, ex. \n" + 
                                   "13:45 -> 1345 or 9:02 -> 0902 and \nany negative number" +
                                   "for an event lasting all day): ");
                try {
                    userAddTime = scnr.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("That isn't a number. Please enter integer values");
                    continue;
                }
                
                //Error checking time
                if (userAddTime > 0) {
                    String hour = "";
                    String minute = "";

                    String formattedTime = String.valueOf(userAddTime);
                    try {
                        hour = formattedTime.substring(0, 2);
                        minute = formattedTime.substring(2, 3);
                    }
                    catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Time given in the wrong format, please try again");
                        continue;
                    }
                    if (Integer.parseInt(hour) > MAX_HOUR || Integer.parseInt(hour) < MIN_HOUR) {
                        throw new IllegalArgumentException("Invalid Hour");
                    }
                    if (Integer.parseInt(minute) > MAX_MINUTE || 
                        Integer.parseInt(minute) < MIN_MINUTE) {
                        throw new IllegalArgumentException("Invalid Minute");
                    }
                }

                //Eat from the numbers
                scnr.nextLine();

                //Continuing to gather information
                System.out.println("Event description/name: ");
                userAddDescription = scnr.nextLine();
                System.out.println("Event location: ");
                userAddLocation = scnr.nextLine();

                //Making Event[] events itself + 1 empty event
                Event[] oldEvents = new Event[events.length];
                for (int i = 0; i < events.length; i++) {
                    oldEvents[i] = events[i];
                }
                events = new Event[events.length + 1];
                for (int i = 0; i < oldEvents.length; i++) {
                    events[i] = oldEvents[i];
                } 
                
                //Adding new event to array
                events[events.length - 1] = new Event(userAddDate, userAddTime, 
                                                      userAddDescription, userAddLocation);

            } 
            
            //Quit
            else if (option.equalsIgnoreCase("Q")) {
                for (int i = 0; i < events.length; i++) {
                    out.println(events[i].toString());
                }
                scnr.close();

            }

            //Invalid option
            else if (!option.equalsIgnoreCase("Q")) {
                System.out.println("Invalid Option");
                System.exit(1);
            }

        } // while
        
        //Creating output file(close out)
        out.close();
  

    } // main
    
    /**
     * Outputs the menu of options for the user
     */
    public static void displayMenu() {

        System.out.println("Daily Event Planner - Please choose an option.");
        System.out.println();
        System.out.println("V - View single day events");
        System.out.println("R - View events in date range");
        System.out.println("A - Add event");
        System.out.println("Q - Quit");
        System.out.println();
    }
    
    /**
     * Finds the number of lines in the given input file
     * @param in the scanner for the input file
     * @return numberOfLines the number of lines
     * @throws IllegalArgumentException if scanner is null
     */
    public static int getNumberOfLines(Scanner in) {
        if (in == null) {
            throw new IllegalArgumentException("Null file");
        } // if
        int numberOfLines = 0;
        while (in.hasNextLine()) {
            in.nextLine();
            numberOfLines++;
        } // while
        return numberOfLines;
    } // getNumberOfLines

    /**
     * Check's to see if a date is possible/exists
     * @param month the month
     * @param day the day
     * @param year the year
     * @return true or false based on valid
     */
    public static boolean checkDate(int month, int day, int year) {
        
        if (day < MIN_DATE) {
            return false;
        } // if
        
        if (month < MIN_MONTH) {
            return false;
        } // if

        if (month > MAX_MONTH) {
            return false;
        }

        if (year < MIN_YEAR) {
            return false;
        } // if
        
        if ((month == JANUARY || month == MARCH || month == MAY || 
             month == JULY || month == AUGUST || month == OCTOBER || 
             month == DECEMBER) && day > MAX_DATE) {
            return false;
        } // if
        
        if ((month == FEBRUARY) && day > MAX_DATE_FEB) {
            return false;
        } // if
        
        if ((month == APRIL || month == JUNE || month == SEPTEMBER ||
             month == NOVEMBER) && day > MAX_DATE_AP_JU_SE_NO) {
            return false;
        } // if

        return true;

    }
    
}