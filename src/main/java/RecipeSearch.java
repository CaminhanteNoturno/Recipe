import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeSearch {
    private final Scanner scanner;

    public RecipeSearch(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {

        System.out.print("File to read: ");
        String file = scanner.nextLine();
        System.out.println();

        ArrayList<Recipe> recipes = new ArrayList<>();

        try (Scanner fScanner = new Scanner(Paths.get(file))) {
            while (fScanner.hasNextLine()) {
                String name = fScanner.nextLine();
                if (name.trim().isEmpty()) continue;

                int cookingTime = Integer.parseInt(fScanner.nextLine());

                ArrayList<String> ingredients = new ArrayList<>();
                while (fScanner.hasNextLine()) {
                    String line = fScanner.nextLine();
                    if (line.trim().isEmpty()) break;
                    ingredients.add(line);
                }

                recipes.add(new Recipe(name, cookingTime, ingredients));
            }
        } catch (
                IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.println("Commands:");
        System.out.println("list - lists the recipes");
        System.out.println("stop - stops the program");
        System.out.println("find name - searches recipes by name");
        System.out.println("find cooking time - searches recipes by cooking time");
        System.out.println("find ingredient - searches recipes by ingredient");

        while (true) {
            System.out.println();
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("stop")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println();
                System.out.println("Recipes: ");
                for (Recipe r : recipes) {
                    System.out.println(r);
                }
            } else if (command.equalsIgnoreCase("find name")) {
                System.out.print("Searched word: ");
                String word = scanner.nextLine();

                System.out.println();
                System.out.println("Recipes: ");

                for (Recipe recipe : recipes) {
                    if (recipe.getName().toLowerCase().contains(word.toLowerCase())) {
                        System.out.println(recipe);
                    }
                }

            } else if (command.equalsIgnoreCase("find cooking time")) {
                System.out.print("Max cooking time: ");
                int maxCookingTime = Integer.parseInt(scanner.nextLine());

                System.out.println();
                System.out.println("Recipes: ");

                for (Recipe recipe : recipes) {
                    if (recipe.getCookingTime() <= maxCookingTime) {
                        System.out.println(recipe);
                    }
                }
            } else if (command.equalsIgnoreCase("find ingredient")) {
                System.out.print("Ingredient: ");
                String ingredient = scanner.nextLine();

                System.out.println();
                System.out.println("Recipes: ");

                for (Recipe recipe : recipes) {
                    if (recipe.getIngredients().stream().anyMatch(i -> i.equalsIgnoreCase(ingredient))) {
                        System.out.println(recipe);
                    }
                }
            } else {
                System.out.println("Unknown command");
            }
        }
    }
}

