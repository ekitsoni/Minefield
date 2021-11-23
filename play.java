package ergasia;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class play extends Application{
    @Override
    public void start(Stage stage) {  
        Button button1 = new Button("Login"); 
        Button button2 = new Button("Play as guest"); 
        
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200); 
        gridPane.add(button1, 0, 0);
        gridPane.add(button2, 1, 0);
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("Easy","Normal","Hard");
        comboBox.setValue("Easy");
        gridPane.add(comboBox, 1, 1);
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        gridPane.setVgap(5); 
        gridPane.setHgap(5); 
        gridPane.setAlignment(Pos.CENTER); 
        
        button1.setOnMouseClicked((MouseEvent event) -> {
            stage.close();
            new Login().start(new Stage());
        });
        
        button2.setOnMouseClicked((MouseEvent event) -> {
            stage.close();
            if("Easy".equals(comboBox.getValue())){
                new level1().start(new Stage());
            } else if ("Normal".equals(comboBox.getValue())){
                new level2().start(new Stage());
            } else if ("Hard".equals(comboBox.getValue())){
                new level3().start(new Stage());
            }
        });
        
        gridPane.setStyle("-fx-background-color: #ebffe5;");
        Scene scene = new Scene(gridPane);
        stage.setTitle("Welcome"); 
        stage.setScene(scene); 
        stage.show();
    }
    
    public static void main(String args[]){
        launch(args);
    } 

    public void run() {}
}