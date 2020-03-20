package ui.food;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import javafx.scene.control.Label;
import sun.security.krb5.internal.APRep;
import ui.ConfirmBox;

public class EditFoodUI extends FoodUI {

    // miscellaneous
    static boolean exit;


    // text fields
    static TextField nameField;
    static TextField weightField;
    static TextField costField;
    static TextField caloriesField;
    static TextField carbsField;
    static TextField fatsField;
    static TextField proteinField;
    static Button saveButton;


    public static void displayEditMenu(Food foodGiven) {

        food = foodGiven;
        purposeString = "Editor";
        initFood();
        initTextFields();
        initLabels();
        initButtons();
        initSetOnCloseRequest();
        initSceneForEditing();
        setGridConstraintsForEditing();
        initWindow(350,400);
        window.showAndWait();
    }

    public static void initTextFields() {

        nameField = new TextField(food.getName());
        weightField = new TextField(Integer.toString(cWeight));
        costField = new TextField(Double.toString(cCost));
        caloriesField = new TextField(Integer.toString(cCalories));
        carbsField = new TextField(Integer.toString(cCarbs));
        fatsField = new TextField(Integer.toString(cFats));
        proteinField = new TextField(Integer.toString(cProteins));
    }

    public static void initButtons() {
        saveButton = new Button("Save");
        saveButton.setOnAction(e -> save());
    }

    public static void initSetOnCloseRequest() {

        // what to do if user chooses to exit
        window.setOnCloseRequest(e -> {
            if (!checkForSimilarity()) {
                e.consume();
                exit = ConfirmBox.display("Unsaved Changes", "Unsaved changes, do you wish to exit?");
                if (exit) {
                    System.out.println("Changes for " + cName + " have been discarded");
                    window.close();
                }
            }
        });
    }

    public static void initSceneForEditing() {

        initScene();
        grid.getChildren().addAll(nameField, weightField, costField);
        grid.getChildren().addAll(caloriesField, carbsField, fatsField, proteinField, saveButton);
        grid.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(grid);
    }

    public static void setGridConstraintsForEditing() {

        GridPane.setConstraints(nameField,2,1);
        GridPane.setConstraints(weightField,2,2);
        GridPane.setConstraints(costField,2,3);
        GridPane.setConstraints(caloriesField,2,4);
        GridPane.setConstraints(carbsField,2,5);
        GridPane.setConstraints(fatsField,2,6);
        GridPane.setConstraints(proteinField,2,7);
        GridPane.setConstraints(saveButton,2,8);
    }

    public static void save() {
        food.editName(nameField.getText());
        food.editWeight(Double.parseDouble(weightField.getText()));
        food.editCost(Double.parseDouble(costField.getText()));
        food.editCalories(Double.parseDouble(caloriesField.getText()));
        food.editCarbs(Double.parseDouble(carbsField.getText()));
        food.editFats(Double.parseDouble(fatsField.getText()));
        food.editProteins(Double.parseDouble(proteinField.getText()));

        nameCL.setText(nameField.getText());
        weightCL.setText(weightField.getText());
        costCL.setText(costField.getText());
        caloriesCL.setText(caloriesField.getText());
        carbsCL.setText(carbsField.getText());
        fatsCL.setText(fatsField.getText());
        proteinCL.setText(proteinField.getText());
    }

    public static boolean checkForSimilarity() {
        return (nameField.getText().equals(cName)
                && (int) Double.parseDouble(weightField.getText()) == Integer.parseInt(weightCL.getText())
                && Double.parseDouble(costField.getText()) == Double.parseDouble(costCL.getText())
                && (int)  Double.parseDouble(caloriesField.getText()) == Integer.parseInt(caloriesCL.getText())
                && (int)  Double.parseDouble(carbsField.getText()) == Integer.parseInt(carbsCL.getText())
                && (int)  Double.parseDouble(fatsField.getText()) == Integer.parseInt(fatsCL.getText())
                && (int)  Double.parseDouble(proteinField.getText()) == Integer.parseInt(proteinCL.getText()));
    }

}


