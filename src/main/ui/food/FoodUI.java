package ui.food;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Food;

public abstract class FoodUI {

    static Food food;
    static Stage window;
    static Scene scene;
    static String purposeString;

    // current values for display
    static String cName;
    static int cWeight;
    static double cCost;
    static int cCalories;
    static int cCarbs;
    static int cFats;
    static int cProteins;
    static String cValue;

    // header labels
    static Label nameLabel;
    static Label weightLabel;
    static Label costLabel;
    static Label caloriesLabel;
    static Label carbsLabel;
    static Label fatsLabel;
    static Label proteinLabel;
    static Label valueLabel;

    // labels with current values
    static Label nameCL;
    static Label weightCL;
    static Label costCL;
    static Label caloriesCL;
    static Label carbsCL;
    static Label fatsCL;
    static Label proteinCL;
    static Label valueCL;

    static GridPane grid;


    public static void initFood() {

        cName = food.getName();
        cWeight = (int) food.getWeight();
        cCost = food.getCost();
        cCalories = (int) food.getCalories();
        cCarbs = (int) food.getCarbs();
        cFats = (int) food.getFats();
        cProteins = (int) food.getProteins();
        cValue = food.value();
    }

    public static void initLabels() {

        nameLabel = new Label("Name");
        weightLabel = new Label("Weight");
        caloriesLabel = new Label("Calories");
        costLabel = new Label("Cost");
        carbsLabel = new Label("Carbs");
        fatsLabel = new Label("Fats");
        proteinLabel = new Label("Protein");
        valueLabel = new Label("Economic\n Value: ");

        nameCL = new Label(cName);
        weightCL = new Label(Integer.toString(cWeight));
        caloriesCL = new Label(Integer.toString(cCalories));
        costCL = new Label(Double.toString(cCost));
        carbsCL = new Label(Integer.toString(cCarbs));
        fatsCL = new Label(Integer.toString(cFats));
        proteinCL = new Label(Integer.toString(cProteins));
        valueCL = new Label(cValue);
    }

    public static void initScene() {
        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        initGridConstraints();

        grid.getChildren().addAll(nameLabel,weightLabel,costLabel,caloriesLabel,
                carbsLabel,fatsLabel,proteinLabel,valueLabel);
        grid.getChildren().addAll(nameCL, weightCL, costCL, caloriesCL, carbsCL, fatsCL, proteinCL, valueCL);
    }

    public static void initGridConstraints() {

        GridPane.setConstraints(nameLabel, 0,1);
        GridPane.setConstraints(weightLabel, 0,2);
        GridPane.setConstraints(costLabel, 0,3);
        GridPane.setConstraints(caloriesLabel, 0,4);
        GridPane.setConstraints(carbsLabel, 0,5);
        GridPane.setConstraints(fatsLabel, 0,6);
        GridPane.setConstraints(proteinLabel, 0, 7);
        GridPane.setConstraints(valueLabel, 0, 8);

        GridPane.setConstraints(nameCL,1,1);
        GridPane.setConstraints(weightCL,1,2);
        GridPane.setConstraints(costCL,1,3);
        GridPane.setConstraints(caloriesCL,1,4);
        GridPane.setConstraints(carbsCL,1,5);
        GridPane.setConstraints(fatsCL,1,6);
        GridPane.setConstraints(proteinCL,1,7);
        GridPane.setConstraints(valueCL,1,8);
    }

    public static void initWindow(int width, int height) {
        // initializing window
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        if (food != null) {
            window.setTitle(food.getName() + " " + purposeString);
        } else {
            window.setTitle(purposeString);
        }
        window.setWidth(width);
        window.setHeight(height);
        window.setScene(scene);
    }

}
