package ui.food;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.food.Food;

public abstract class FoodUI {

    protected Food food;
    protected Stage window;
    protected Scene scene;
    protected String purposeString;

    // current values for display
    String currentName;
    int currentWeight;
    double currentCost;
    int currentCalories;
    int currentCarbs;
    int currentFats;
    int currentProteins;
    String currentValue;

    // header labels
    Label nameLabel;
    Label weightLabel;
    Label costLabel;
    Label caloriesLabel;
    Label carbsLabel;
    Label fatsLabel;
    Label proteinLabel;
    Label valueLabel;

    // labels with current values
    Label nameCL;
    Label weightCL;
    Label costCL;
    Label caloriesCL;
    Label carbsCL;
    Label fatsCL;
    Label proteinCL;
    Label valueCL;

    GridPane grid;


    public void initFood() {

        currentName = food.getName();
        currentWeight = (int) food.getWeight();
        currentCost = food.getCost();
        currentCalories = (int) food.getCalories();
        currentCarbs = (int) food.getCarbs();
        currentFats = (int) food.getFats();
        currentProteins = (int) food.getProteins();
        currentValue = food.value();
    }

    public void initLabels() {

        nameLabel = new Label("Name");
        weightLabel = new Label("Weight");
        caloriesLabel = new Label("Calories");
        costLabel = new Label("Cost");
        carbsLabel = new Label("Carbs");
        fatsLabel = new Label("Fats");
        proteinLabel = new Label("Protein");
        valueLabel = new Label("Economic\n Value: ");

        nameCL = new Label(currentName);
        weightCL = new Label(Integer.toString(currentWeight));
        caloriesCL = new Label(Integer.toString(currentCalories));
        costCL = new Label(Double.toString(currentCost));
        carbsCL = new Label(Integer.toString(currentCarbs));
        fatsCL = new Label(Integer.toString(currentFats));
        proteinCL = new Label(Integer.toString(currentProteins));
        valueCL = new Label(currentValue);
    }

    public void initScene() {
        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        initGridConstraints();

        grid.getChildren().addAll(nameLabel,weightLabel,costLabel,caloriesLabel,
                carbsLabel,fatsLabel,proteinLabel,valueLabel);
        grid.getChildren().addAll(nameCL, weightCL, costCL, caloriesCL, carbsCL, fatsCL, proteinCL, valueCL);
    }

    public void initGridConstraints() {

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

    public void initWindow(int width, int height) {
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
