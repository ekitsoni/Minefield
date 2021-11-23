package ergasia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application; 
import static javafx.application.Application.launch;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage; 

public class Login extends Application implements Runnable{
      
    @Override 
    public void start(Stage stage) {      
      
        Text text1 = new Text("Username");       
        Text text2 = new Text("Password"); 
      
        TextField textField1 = new TextField();       
        TextField textField2 = new TextField();  
       
        Button button1 = new Button("Login"); 
        Button button2 = new Button("Clear");  
        Button button3 = new Button("New User");
      
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        gridPane.setVgap(5);
        gridPane.setHgap(5);    
        gridPane.setAlignment(Pos.CENTER); 
        gridPane.add(text1, 0, 0); 
        gridPane.add(textField1, 1, 0); 
        gridPane.add(text2, 0, 1);       
        gridPane.add(textField2, 1, 1); 
        gridPane.add(button1, 0, 2); 
        gridPane.add(button2, 1, 2); 
        gridPane.add(button3, 2, 0);
        Text text = new Text();
        gridPane.add(text, 1, 3);
        
        button1.setOnMouseClicked((MouseEvent event) -> {
            String username = textField1.getText();
            String password = textField2.getText();
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader("C:\\Users\\eleni\\Documents\\Μηχανικών Πληροφορικής\\ΜΑΕ\\Εργασία 1\\Users.txt"));
                String st;
                
                boolean flag=false;
                while ((st = br.readLine()) != null) {
                    String[] col=st.split(" ");
                    if(username.equals(col[0])) {
                        flag=true;
                        if(password.equals(col[1]))
                            text.setText("Wrong Password!");
                    }
                }
                if(!flag){
                    text.setText("The user does not exists!");
                }
                br.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
      
        button2.setOnMouseClicked((MouseEvent event) -> {
            textField1.setText("");
            textField2.setText("");
        });
        
        button3.setOnMouseClicked((MouseEvent event) -> {
            String username = textField1.getText();
            String password = textField2.getText();
            if(!"".equals(username)){
                String space = " ";
                String new_add = username + space + password;
                File file = new File("C:\\Users\\eleni\\Documents\\Μηχανικών Πληροφορικής\\ΜΑΕ\\Εργασία 1\\Users.txt");
                try {
                    FileWriter fw = new FileWriter(file,true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.newLine();
                    bw.write(new_add);
                    bw.newLine();
                    bw.close();
                    textField1.setText("");
                    textField2.setText("");
                    text.setText("You are in!");
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                text.setText("Try Again!");
                
            }
        });
        
        gridPane.setStyle("-fx-background-color: #fdffe5;");
        Scene scene = new Scene(gridPane,500,500);  
        stage.setTitle("Login"); 
        stage.setScene(scene); 
        stage.show(); 
    } 
    
    @Override
    public void run() {
        launch();
    }
}