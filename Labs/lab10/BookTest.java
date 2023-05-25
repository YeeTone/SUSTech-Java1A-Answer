package labs.lab10;

import java.util.EnumSet;

public class BookTest {
    public static void main(String[] args) {
        System.out.println("All books:");
        for (Book book : Book.values()) {
            System.out.printf("%-10s", book);
            System.out.printf("%-30s", book.getTitle());
            System.out.printf("%s\n", book.getYear());
        }
        System.out.println("\nDisplaying a range of enum constants:");
        for (Book book : EnumSet.range(Book.JHTP, Book.CPPHTP)) {
            System.out.printf("%-10s", book);
            System.out.printf("%-30s", book.getTitle());
            System.out.printf("%s\n", book.getYear());
        }
    }
}
