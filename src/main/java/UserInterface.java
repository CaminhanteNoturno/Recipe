import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RecipeSearch search = new RecipeSearch(scanner);
        search.start();
        scanner.close();
    }
}