

// PLEASE HAVE SPEAKERS ON TO ENJOY THE FULL EXPERIENCE

package prime_numbers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class exitAll extends Prime_numbers{

    private Stage primary_stage;                                                    
    
//                                   CONSTRUCTOR
    public exitAll(Stage primaryStage){                                                         //pass stage through constructor
        primary_stage=primaryStage;                                                             //define primary_stage
    }
    
    //                                DISPLAY NEW STAGE
    public void displayNewStage(){
        Button yes_btn=new Button("Yes");                                                       //define yes button
        Button no_btn=new Button("No");                                                         //define no button
        Label exit_label=new Label("Are you sure you want to exit?");                           //define exit label
        
        Stage exitStage=new Stage();
        
//                                YES BUTTON LAMBDA EVENT
        yes_btn.setOnAction(event ->{
            exitStage.close();                                                                  //close second stage
            primary_stage.close();                                                              //close primary stage
        });
        
//                                NO BUTTON LAMBDA EVENT
        no_btn.setOnAction(event ->{
            exitStage.close();                                                                  //close second stage
        });
        
        HBox hbox=new HBox(yes_btn, no_btn);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        VBox vbox=new VBox(exit_label, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        Scene clearScene=new Scene(vbox, 450, 150 );
        clearScene.getStylesheets().add("run-stage.css");
        exitStage.setTitle("Exit Program");
        exitStage.setScene(clearScene);
        exitStage.show();
    }
}
