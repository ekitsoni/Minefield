package ergasia;

import java.util.Random;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class level1 extends Application{
    
    int numC=10;
    int numR=10;
    int numB=10;
    String[][] table=new String[numC][numR];
    GridPane grid = new GridPane();
    
    StackPane newNode(BooleanProperty makeNode) {
    
        StackPane node = new StackPane();

        Button btn = new Button();
        btn.setPrefSize(1000, 1000);
        btn.setStyle("-fx-font: 22 arial;-fx-base:dcdcf2;");
        node.getChildren().add(btn);
        
        btn.setOnMouseClicked((MouseEvent mouseEvent) -> {
            String text = btn.getText();
            if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
                if ("F".equals(text)) {
                    btn.setText("");
                }else{
                    btn.setText("F");
                }
            } else if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (!"F".equals(text)){
                    int x=GridPane.getColumnIndex(node);
                    int y=GridPane.getRowIndex(node);
                    if("B".equals(table[x][y]))
                        for(int i=0 ; i<numC ; i++)
                            for(int j=0 ; j<numR ; j++)
                                if("B".equals(table[i][j])){
                                    String a=table[i][j];
                                    Label lb=new Label(a);
                                    if("B".equals(table[i][j]))
                                        lb.setStyle("-fx-font: 22 arial; -fx-base:#dcdcdc;");
                                    
                                    lb.setMaxWidth(Double.MAX_VALUE);
                                    lb.setMaxHeight(Double.MAX_VALUE);
                                    lb.setAlignment(Pos.CENTER);
                                    grid.add(lb, i, j);
                                }
                    btn.setVisible(false);
                }
            }
        });
        return node;
    }

    GridPane makeGrid(BooleanProperty[][] makeG) {

        numC = makeG.length ;
        numR = makeG[0].length ;
        int i,j,c,r;
        Random rand = new Random(System.currentTimeMillis());
        
        for( i=0; i<numB; i++){
            do{
                c=rand.nextInt(numC);
                r=rand.nextInt(numR);
            } while ("B".equals(table[c][r]));
            table[c][r]="B";
        }  
        
        for( i=0 ; i<numC ; i++)
            for( j=0 ; j<numR ; j++){
                int sum=0;
                if (i-1>=0 && j-1>=0 && "B".equals(table[i-1][j-1])) 
                    sum++;
                if (j-1>=0 && "B".equals(table[i][j-1])) 
                    sum++;
                if (i+1<numC && j-1>=0 && "B".equals(table[i+1][j-1])) 
                    sum++;
                if (i+1<numC && "B".equals(table[i+1][j])) 
                    sum++;
                if (i+1<numC && j+1<numR && "B".equals(table[i+1][j+1])) 
                    sum++;
                if (j+1<numR && "B".equals(table[i][j+1])) 
                    sum++;
                if (i-1>=0 && j+1<numR && "B".equals(table[i-1][j+1])) 
                    sum++;
                if (i-1>=0 && "B".equals(table[i-1][j])) 
                    sum++;
                if(!"B".equals(table[i][j]))
                    table[i][j]=Integer.toString(sum);
            }
        
        for( i=0 ; i<numC ; i++)
            for( j=0 ; j<numR ; j++)
                if(!"0".equals(table[i][j])){
                    String a=table[i][j];
                    Label lb=new Label(a);
                    if("B".equals(table[i][j]))
                        lb.setStyle("-fx-font: 22 arial; -fx-base:#dcdcdc;");
                   
                    lb.setMaxWidth(Double.MAX_VALUE);
                    lb.setMaxHeight(Double.MAX_VALUE);
                    lb.setAlignment(Pos.CENTER);
                    grid.add(lb, i, j);
                }
        
        grid.setGridLinesVisible(true);

        for ( i=0 ; i<numC ; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
        
        for ( i= 0 ; i<numR ; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        
        for ( i= 0 ; i<numC ; i++) 
            for ( j= 0 ; j<numR ; j++) 
                grid.add(newNode(makeG[i][j]), i, j);
            
        return grid;
    }

    @Override
    public void start(Stage stage) {
        
        BooleanProperty[][] makeT = new BooleanProperty[numC][numR];
        
        for (int i=0 ; i<numC ; i++) {
            for (int j=0 ; j<numR ; j++) {
                makeT[i][j] = new SimpleBooleanProperty();
            }
        }
        
        grid = makeGrid(makeT);
        grid.setPadding(new Insets(9,14,9,14));
        grid.setStyle("-fx-background-color: #e5e5ff;");
        
        StackPane root = new StackPane(grid);
        Scene scene1 = new Scene(root, 500, 500);
        stage.setTitle("Minesweeper-Eazy");
        stage.setScene(scene1);
        stage.show();
    }
    
    public void run() {
        launch();
    }
}