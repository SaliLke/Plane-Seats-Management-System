import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private char row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(char row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public int getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void information_ticket() {
        System.out.println ("  Ticket information");
        System.out.println ("Row=" + row);
        System.out.println ("Seat=" + seat);
        System.out.println ("Price=" + price);
        if (person != null) {
            System.out.println ("  Person information");
            person.information ();
        } else {
            System.out.println ("No person information available.");
        }
    }

    public void save() {
        String fileName = row + "" + seat + ".txt";
        try {
            FileWriter file = new FileWriter (fileName);
            file.write ("\nTicket information");
            file.write ("\nRow:" + getRow ());
            file.write ("\nSeat Number:" + getSeat ());
            file.write ("\nPrice:" + getPrice ());
            file.write ("\nPersonal Information");
            if (person != null) {
                file.write ("\nName:" + person.getName ());
                file.write ("\nSurname:" + person.getSurname ());
                file.write ("\nemail:" + person.getEmail ());
            }
            file.close ();
        } catch (IOException e) {
            System.out.println ("Error while writing in a file");
        }
    }


    public void delete() {
        String fileName = row + "" + seat + ".txt";
        File file = new File (fileName);
        if (file.exists ()) {
            file.delete ();
        } else {
            System.out.println ("File does not exist");
        }
    }
}
