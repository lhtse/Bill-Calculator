package Bill_Calculator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;


public class Main extends Application {
    //Main Method
    public static void main(String[] args) {
        launch(args);
    }

    //Creating labels for display
    private Label fName = new Label();
    private Label subtotal = new Label();
    private Label tNum = new Label();
    private Label tax = new Label();
    private Label total = new Label();
    private Button clear = new Button("Clear");
    private TextField fn = new TextField();
    private TextField tn = new TextField();
    private ListView<String> list= new ListView<>();


    //Declaring parallel arrays for menu items
    //Arrays for beverages
    private double[] bevP = {1.95, 1.50, 1.25, 2.95, 2.50, 1.50};
    private String[] b = {"Soda $1.95", "Tea $1.50", "Coffee $1.25", "Mineral Water $2.95",
            "Juice $2.50", "Milk $1.50"};

    //Arrays for appetizers
    private double[] appP = {5.95, 6.95, 8.95, 8.95, 10.95, 12.95, 6.95};
    private String[] a = {"Buffalo Wings $5.95", "Buffalo Fingers $6.95", "Potato Skins $8.95",
            "Nachos $ 8.95", "Mushroom Caps $10.95", "Shrimp Cocktail $12.95", "Chips and Salsa $6.95"};

    //Arrays for main courses
    private double[] mcP = {15.95, 13.95, 13.95, 11.95, 19.95, 20.95, 18.95, 13.95, 14.95};
    private String[] mc = {"Seafood Alfredo $15.95", "Chicken Alfredo $13.95", "Chicken Picatta $13.95",
            "Turkey Club $11.95", "Lobster Pie $19.95", "Prime Rib $20.95", "Shrimp Scampi $18.95",
            "Turkey Dinner $13.95", "Stuffed Chicken $14.95"};

    //Arrays for desserts
    private double[] dP = {5.95, 3.95, 5.95, 4.95, 5.95};
    private String[] d = {"Apple Pie $5.95", "Sundae $3.95", "Carrot Cake $5.95", "Mud Pie $4.95", "Apple Crisp $5.95"};

    //Creating Observerable lists
    ObservableList<String> bevs = FXCollections.observableArrayList(b);
    ObservableList<String> apps = FXCollections.observableArrayList(a);
    ObservableList<String> mCourse = FXCollections.observableArrayList(mc);
    ObservableList<String> dess = FXCollections.observableArrayList(d);

    //Creating comboboxes
    ComboBox<String> beverages = new ComboBox<>();
    ComboBox<String>  appetizers = new ComboBox<>();
    ComboBox<String> mainCourses = new ComboBox<>();
    ComboBox<String> desserts = new ComboBox<>();

    //Setting primary stage to display
    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane root = new StackPane();

        //Setting logo icon
        primaryStage.getIcons().add(new Image("file:logo.jpg"));
        primaryStage.setTitle("Food Hub");

        //Creating pane for scene
        GridPane pane1 = createPane1();

        //Creating ArrayList
        ArrayList<Double> billHolder = new ArrayList<Double>();

        //Adding items to comboboxes
        beverages.getItems().addAll(bevs);
        appetizers.getItems().addAll(apps);
        mainCourses.getItems().addAll(mCourse);
        desserts.getItems().addAll(dess);

        //Action handling
        handlerWaiterInfo();
        handleComboBoxes(billHolder);
        handleListView(billHolder);
        handleButton(billHolder);


        //Adding all panes and setting up stage for display
        root.getChildren().addAll(pane1);
        primaryStage.setScene(new Scene(root, 350, 900));
        primaryStage.show();

    }

    /*****************************************************************************************************************
    * Definition of createPane1 method. Returns GridPane object to caller method. Used to set up the pane for the    *
    * stage.                                                                                                         *
    * ****************************************************************************************************************/

    private GridPane createPane1() {
        //Pane 1
        GridPane pane1 = new GridPane();
        pane1.setAlignment(Pos.TOP_LEFT);
        pane1.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane1.setHgap(8.5);
        pane1.setVgap(8.5);

        //Adding listview preferences
        list.setPrefSize(200, 400);
        list.setOrientation(Orientation.VERTICAL);
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Place nodes in pane
        pane1.add(new Label("Waiter Info"), 1, 0);
        pane1.add(new Label("Name:"), 0, 1);
        pane1.add(fn, 1, 1);
        pane1.add(new Label("Table No:"), 0, 2);
        pane1.add(tn, 1, 2);
        pane1.add(new Label("Add Menu Items"), 1, 4);
        pane1.add(new Label("Beverages:"), 0, 5);
        pane1.add(beverages, 1, 5);
        pane1.add(new Label("Appetizers:"), 0, 6);
        pane1.add(appetizers, 1, 6);
        pane1.add(new Label ("Main Course:"), 0, 7);
        pane1.add(mainCourses, 1, 7);
        pane1.add(new Label("Desserts: "), 0, 8);
        pane1.add(desserts, 1, 8);
        pane1.add(new Label("Billing"), 0, 9);
        pane1.add(new Label("Waiter Info:"), 0, 10);
        pane1.add(fName, 1, 10);
        pane1.add(new Label("Table No.:"), 0, 11);
        pane1.add(tNum, 1, 11);
        pane1.add(new Label("Subtotal:"), 0, 12);
        pane1.add(subtotal, 1, 12);
        pane1.add(new Label("Tax:"), 0, 13);
        pane1.add(tax, 1, 13);
        pane1.add(new Label("Total:"), 0, 14);
        pane1.add(total, 1, 14);
        pane1.add(clear, 1, 15);
        pane1.add(list, 1, 16);

        //Return pane to caller method
        return pane1;

    }

    /*****************************************************************************************************************
     * Definition of handlerWaiterInfo method. Handles the info entered by user in application.                      *
     * ****************************************************************************************************************/

    private void handlerWaiterInfo() throws IllegalArgumentException{
        //Course of action if enter key is pressed after user enters first name
        fn.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                //Validation of input in waiter name textfield
                try {
                    while (fn.getText().trim().isEmpty())
                        throw new IllegalArgumentException();

                    fName.setText(fn.getText());
                    tn.requestFocus();

                } catch (IllegalArgumentException exception) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Invalid Input. You must enter your name.");
                    alert.showAndWait();

                }
            }
        });
        //Course of action if textfield is clicked on
        fn.setOnMouseClicked( e -> {
            fn.requestFocus();
        });

        //Course of action if textfield is clicked on
        tn.setOnMouseClicked( e -> {
            tn.requestFocus();
        });
        //Course of action if enter key is pressed after user enters table number in textfield
        tn.setOnKeyPressed(e-> {
            if (e.getCode() == KeyCode.ENTER) {
                //Validate user input
                try {
                    while (tn.getText().trim().isEmpty() || Integer.parseInt(tn.getText()) < 1 ||
                            Integer.parseInt(tn.getText()) > 10)
                        throw new IllegalArgumentException();

                    tNum.setText(tn.getText());
                    fName.setText(fn.getText());

                } catch (IllegalArgumentException exception) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Invalid Input. You must enter a valid table number.");
                    alert.showAndWait();
                }

            }

        });
    }

    /******************************************************************************************************************
     * Definition of handlerComboBoxes method. Accepts ArrayList reference as argument.                               *
     * Handles the selections made by users in comboboxes.                                                            *
     * ****************************************************************************************************************/

    private void handleComboBoxes(ArrayList<Double> billHolder) {
        //Adding listener for the comboboxes
        beverages.setOnAction(e -> {
            //Adding item price into array list
            int index = bevs.indexOf(beverages.getValue());
            billHolder.add(bevP[index]);

            //Displaying selected item on listview
            list.getItems().add(beverages.getSelectionModel().getSelectedItem());

            //Adding price of item to subtotal
            calcBill(billHolder);
        });
        //Adding listener for the comboboxes
        appetizers.setOnAction(e -> {
            //Adding item price into array list
            int index = apps.indexOf(appetizers.getValue());
            billHolder.add(appP[index]);

            //Displaying selected item on listview
            list.getItems().add(appetizers.getSelectionModel().getSelectedItem());

            //Adding price of item to subtotal
            calcBill(billHolder);
        });

        mainCourses.setOnAction(e -> {
            //Adding listener for the comboboxes
            int index = mCourse.indexOf(mainCourses.getValue());
            billHolder.add(mcP[index]);

            //Displaying selected item on listview
            list.getItems().add(mainCourses.getSelectionModel().getSelectedItem());

            //Adding price of item to subtotal
            calcBill(billHolder);
        });

        desserts.setOnAction(e -> {
            //Adding listener for the comboboxes
            int index = dess.indexOf(desserts.getValue());
            billHolder.add(dP[index]);

            //Displaying selected item on listview
            list.getItems().add(desserts.getSelectionModel().getSelectedItem());

            //Adding price of item to subtotal
            calcBill(billHolder);
        });

    }

    /******************************************************************************************************************
     * Definition of handleListView method. Accepts ArrayList reference as argument.                                  *
     * Handles the selections made by users in listview.                                                              *
     * ****************************************************************************************************************/

    private void handleListView(ArrayList<Double> billHolder) {
        //Adding listener for the lists
        list.setOnMouseClicked( e -> {
            //Removing selected item from listview
            list.getItems().remove(list.getSelectionModel().getSelectedIndex());

            //Removing selected item from ArrayList
            billHolder.remove(list.getSelectionModel().getSelectedIndex() + 1);

            //Update subtotal
            calcBill(billHolder);
        });
    }

    /******************************************************************************************************************
     * Definition of handlerButton method. Accepts ArrayList reference as argument.                                   *
     * Handles actions when user clicks on clear button.                                                              *
     * ****************************************************************************************************************/

    private void handleButton(ArrayList<Double> billHolder) {

        //Adding listener for clear button
        clear.setOnMouseClicked( e -> {
            //Clear items from listview
            list.getItems().clear();

            //Clear items from ArrayList
            billHolder.clear();

            //Updating subtotals
            calcBill(billHolder);
        });

    }

    /******************************************************************************************************************
     * Definition of calcBill method. Accepts ArrayList reference as argument.                                        *
     * Calculates the subtotals for the bill.                                                                         *
     * ****************************************************************************************************************/

    private void calcBill(ArrayList<Double> bh) {
        //Calculating subtotal
        double bill = 0.00;
        for(int i = 0; i < bh.size(); i++) {

            bill += bh.get(i);
        }
        //Displaying the subtotal
        String newTotal = String.format("$%.2f", bill);
        subtotal.setText(newTotal);

        //Calculating the taxes owed
        double t = 0.06 * bill;

        //Displaying the taxes
        String taxes = String.format("$%.2f", t);
        tax.setText(taxes);

        //Displaying the final total for bill
        double tot = t + bill;
        String tots = String.format("$%.2f", tot);
        total.setText(tots);
    }


}
