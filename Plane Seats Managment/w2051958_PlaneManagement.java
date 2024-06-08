import java.util.InputMismatchException;
import java.util.Scanner;

public class w2051958_PlaneManagement {
    static Scanner input = new Scanner (System.in);
    static int[][] seatsArray = new int[4][];
    public static Ticket[][] ticketsArray = new Ticket[4][];
    public static final char[] rowArray = {'A', 'B', 'C', 'D'};
    static Person person;

    public static void main(String[] args) {
        /*
        This method initiates the program with the welcome message and the menu
         while initializing the arrays.
         */
        System.out.println ("Welcome to the Plane Management application");
        array ();
        menu ();
    }

    public static void array() {
        /*
        This method initializes the seatsArray and ticketsArray while storing
        their values and  sets the size of the arrays inside the main array.
         */
        seatsArray[0] = new int[14];
        seatsArray[1] = new int[12];
        seatsArray[2] = new int[12];
        seatsArray[3] = new int[14];

        for (int i = 0; i < seatsArray.length; i++) {
            for (int j = 0; j < seatsArray[i].length; j++) {
                seatsArray[i][j] = 0;
            }
        }
        ticketsArray[0] = new Ticket[14];
        ticketsArray[1] = new Ticket[12];
        ticketsArray[2] = new Ticket[12];
        ticketsArray[3] = new Ticket[14];
    }

    public static void menu() {
        /*
        This method prints the menu and asks for the option when it's called.
        It checks the validation of the option given by the user.
         */
        for (int i = 1; i < 50; i++) {
            System.out.print ("*");
        }
        System.out.println ();
        System.out.println ("*                     Main                      * ");
        for (int i = 1; i < 50; i++) {
            System.out.print ("*");
        }
        System.out.println ("\n   1) Buy a seat\n   2) Cancel a seat\n   3) Find first available seat\n   4) Show seating plan\n   5) Print tickets information and total sales\n   6) Search ticket\n   0) Quit");
        for (int i = 1; i < 50; i++) {
            System.out.print ("*");
        }
        System.out.println ();
        boolean checkOption = true;
        do {
            try {
                System.out.println ("Please select an option:");
                int menuOption = input.nextInt ();
                switch (menuOption) {
                    case 1:
                        System.out.println ("Buy a seat");
                        checkOption = false;
                        buy_seat ();
                        break;
                    case 2:
                        System.out.println ("Cancel a seat");
                        checkOption = false;
                        cancel_seat ();
                        break;
                    case 3:
                        System.out.println ("Find first available seat");
                        checkOption = false;
                        find_first_available ();
                        break;
                    case 4:
                        System.out.println ("Show seating plan");
                        checkOption = false;
                        show_seating_plan ();
                        break;
                    case 5:
                        System.out.println ("Print ticket information");
                        checkOption = false;
                        print_tickets_info ();
                        break;
                    case 6:
                        System.out.println ("Search seat");
                        checkOption = false;
                        search_ticket ();
                        break;
                    case 0:
                        System.out.println ("quit");
                        checkOption = false;
                        break;
                    default:
                        System.out.println ("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.println ("Invalid Input");
                input.nextLine ();
            }
        } while (checkOption);
    }

    public static Integer input_row() {
        /*
        This method asks for the row number as a character and turn it into an integer
        value by subtracting the unicode value of A from the entered character and returns
        the remaining integer value.This also checks the validation of the input.
         */
        boolean checkRow = true;
        int seatRow = 0;
        try {
            do {
                System.out.println ("Enter the row of the seat(A-D):");
                char inputRow = input.next ().toUpperCase ().charAt (0);
                seatRow = inputRow - 'A';
                if (seatRow < 0 || seatRow > 3) {
                    System.out.println ("Invalid Input");
                } else {
                    checkRow = false;
                }
            } while (checkRow);
        } catch (InputMismatchException e) {
            System.out.println ("Invalid seat number");
        }
        return seatRow;
    }

    public static Integer input_number() {
        /*
        This method asks for the seat number and returns the seat number subtracting 1
        from it to map with the array index.This also checks the validation of input.
         */
        int seatNumber=0;
        boolean checkNum = true;
        do {
            try {
                System.out.println ("Enter the number of the seat:");
                seatNumber = input.nextInt ();
                checkNum = false;
            } catch (InputMismatchException e) {
                System.out.println ("Invalid Input");
                input.next ();
            }
        } while (checkNum);
        return seatNumber - 1;
    }

    public static double price_range(int seatNum) {
        /*
        This method calculates the price of the seat reserved by the user by taking
        seat number as a parameter. The price of the ticket is assigned according to the
        range the seat number belongs.Then the price if the ticket is returned.
         */
        double price;
        if (0 <= seatNum && seatNum < 5) {
            price = 200;
        } else if (seatNum < 9) {
            price = 150;
        } else {
            price = 180;
        }
        return price;
    }

    public static void buy_seat() {
        /*
        This method first calls the methods related to row and seat number inputs, checks if the
        entered values are in the index of the array and checks the availability of the seat. If the
        seat is available it's reserved and asks for passenger's information.If seat is not available
        user will be asked to input row and seat number again to reserve another seat.
         */
        int seatRow;
        int seatNum;
        boolean checkRow = false;
        boolean checkSeat = false;
        do {
            seatRow = input_row ();
            do {
                seatNum = input_number ();
                if ((seatNum < 0) || (seatNum >= seatsArray[seatRow].length)) {
                    System.out.println ("Invalid seat number.");
                    checkSeat = true;
                } else {
                    if (seatsArray[seatRow][seatNum] == 0) {
                        seatsArray[seatRow][seatNum] = 1;
                        checkSeat = false;

                        System.out.println ("Enter your name:");
                        String name = input.next ();
                        System.out.println ("Enter your surname:");
                        String surname = input.next ();
                        String email;
                        while (true) {
                            System.out.println ("Enter your email:");
                            email = input.next ();
                            if (email.contains ("@") && email.contains (".")) {
                                break;
                            } else {
                                System.out.println ("Invalid email address");
                            }
                        }
                        System.out.println ("The seat is reserved.\n");
                        checkRow=false;
                        person = new Person (name, surname, email);
                        person.setName (name);
                        person.setSurname (surname);
                        person.setEmail (email);

                        double price = price_range (seatNum);
                        Ticket ticket = new Ticket (rowArray[seatRow], seatNum, price, person);
                        ticket.setRow (rowArray[seatRow]);
                        ticket.setSeat (seatNum + 1);
                        ticket.setPrice (price);
                        ticket.setPerson (person);
                        ticket.save ();
                        ticketsArray[seatRow][seatNum] = ticket;

                    } else {
                        System.out.println ("Seat is unavailable.");
                        checkRow = true;
                    }
                }
            } while (checkSeat);
        } while (checkRow);
        menu ();
    }

    public static void cancel_seat() {
        /*
        This method first calls the methods related to row and seat number inputs, checks if the
        entered values are in the index of the array and checks the availability of the seat. If the
        seat is already reserved then the reservation and the ticket will be cancelled.If seat entered
        is available user will be asked to input row and seat number again to cancel the reserved seat.
         */
        int seatRow;
        int seatNum;
        boolean checkRow = false;
        boolean checkSeat = false;
        do {
            seatRow = input_row ();
            do {
                seatNum = input_number ();
                if ((seatNum < 0) || (seatNum >= seatsArray[seatRow].length)) {
                    System.out.println ("Seat is Invalid");
                    checkSeat = true;
                } else {
                    if (seatsArray[seatRow][seatNum] == 1) {
                        System.out.println ("Seat is cancelled");
                        seatsArray[seatRow][seatNum] = 0;
                        if (ticketsArray[seatRow][seatNum] != null) {
                            ticketsArray[seatRow][seatNum].delete ();
                            ticketsArray[seatRow][seatNum] = null;
                        } else {
                            System.out.println ("No seats are booked");
                        }
                        checkRow=false;
                    } else {
                        System.out.println ("This seat was not reserved.");
                        checkRow = true;
                    }
                }
            } while (checkSeat);
        } while (checkRow);
        menu ();
    }

    public static void find_first_available() {
        /*
        This method checks the first element in the array with value 0 and displays the row and number of seat as
        the first available seat.If all the seats are reserved then displays that all the seats are reserved.
        */
        boolean checkAvailability = false;
        for (int i = 0; i < seatsArray.length; i++) {
            for (int j = 0; j < seatsArray[i].length; j++) {
                if (seatsArray[i][j] == 0) {
                    System.out.println ("Seat row=" + rowArray[i] + " Seat number=" + (j + 1));
                    checkAvailability = true;
                    break;
                }
            }
            if (checkAvailability) {
                break;
            }
        }
        if (!checkAvailability) {
            System.out.println ("All seats are reserved");
        }
        menu ();
    }

    public static void show_seating_plan() {
        /*
        This displays the layout of the seating arrangement and displays the seats that are reserved and available.
         */
        for (int[] seats : seatsArray) {
            for (int seat : seats) {
                if (seat == 0) {
                    System.out.print ("O");
                } else {
                    System.out.print ("X");
                }
            }
            System.out.println ();
        }
        System.out.println ();
        menu ();
    }

    public static void print_tickets_info() {
        /*
        This method prints the information of the tickets purchased and the relative information of the person.This also
        calculates the total ticket price and displays that no seats are booked if no seats are reserved.
         */
        boolean seats_booked = false;
        double total_price = 0;
        for (Ticket[] tickets : ticketsArray) {
            for (Ticket ticket : tickets) {
                if (ticket != null) {
                    System.out.println ("\nTicket :");
                    ticket.information_ticket ();
                    double ticket_price = ticket.getPrice ();
                    total_price += ticket_price;
                    seats_booked = true;
                }
            }
        }
        if (!seats_booked) {
            System.out.println ("No seats are booked.");
        } else {
            System.out.println ("Total Price=" + total_price);
        }
        menu ();
    }

    public static void search_ticket() {
        /*
        This method first calls the methods related to row and seat number inputs, checks if the
        entered values are in the index of the array and checks the availability of the seat. If the
        seat is available it's displayed if not prints the ticket information.
         */
        int seatRow = input_row ();
        boolean checkSeat = true;
        int seatNum;
        do {
            seatNum = input_number ();
            if (seatNum >= 0 && seatNum < seatsArray[seatRow].length) {
                if (seatsArray[seatRow][seatNum] == 0) {
                    System.out.println ("Seat is Available.\n");
                    checkSeat = false;
                } else {
                    System.out.println ("This seat already reserved.");
                    ticketsArray[seatRow][seatNum].information_ticket ();
                    checkSeat = false;
                }
            } else {
                System.out.println ("Invalid seat number");
            }
        } while (checkSeat);
        menu ();
    }
}
