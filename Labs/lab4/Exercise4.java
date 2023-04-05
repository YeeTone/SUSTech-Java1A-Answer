public class Exercise4 {
    public static void main(String[] args) {
        int year = Integer.parseInt(args[0]);
        int month = Integer.parseInt(args[1]);
        String monthName = "";
        int days = 0;
        boolean isLeapYear = false;
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            isLeapYear = true;
        } else {
            isLeapYear = false;
        }
        switch (month) {
            /* fill in every cases below */
            case 1 -> {
                days = 31;
                monthName = "January";
            }
            case 2 -> {
                if (isLeapYear) {
                    days = 29;
                } else {
                    days = 28;
                }
                monthName = "February";
            }
            case 3 -> {
                days = 31;
                monthName = "March";
            }
            case 4 -> {
                days = 30;
                monthName = "April";
            }
            case 5 -> {
                days = 31;
                monthName = "May";
            }
            case 6 -> {
                days = 30;
                monthName = "June";
            }
            case 7 -> {
                days = 31;
                monthName = "July";
            }
            case 8 -> {
                days = 31;
                monthName = "August";
            }
            case 9 -> {
                days = 30;
                monthName = "September";
            }
            case 10 -> {
                days = 31;
                monthName = "October";
            }
            case 11 -> {
                days = 30;
                monthName = "November";
            }
            case 12 -> {
                days = 31;
                monthName = "December";
            }
            default -> {
                System.out.println("error!!!");
            }
        }
        System.out.printf("%s of %d has %d days.\n", monthName, year, days);
    }
}