
// PLEASE HAVE SPEAKERS ON TO ENJOY THE FULL EXPERIENCE

package prime_numbers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class Prime_numbers extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
//                            SETUP BUTTONS
        Button run_btn = new Button("Run Program");                                     //define run button
        run_btn.setId("run_button");                                                    //define Id for CSS
        
        Button close_btn=new Button("Exit Program");                                    //define close button
        close_btn.setId("close_button");                                                //define Id for CSS
        
//                            SETUP LABELS
        Label prime_label=new Label();                                                  //define result label
        prime_label.setVisible(false);                                                  //hide label
        Label computing_label=new Label();
        computing_label.setVisible(false);
        computing_label.setId("computing_label");
        
//                            SETUP SCROLL PANE
        ScrollPane composite_scroll=new ScrollPane();                                   //define scroll pane
        composite_scroll.setVisible(false);                                             //hide scroll pane
        
//                             SETUP PROGRESS BAR
        ProgressBar pb=new ProgressBar(0);                                              //define progress bar
        pb.setMaxWidth(200);                                                            //set max width of progress bar
        pb.setVisible(false);                                                           //hide progress bar
        

//                            RUN BUTTON LAMBDA EVENT
        run_btn.setOnAction(event ->{
            runProgram run_program=new runProgram(prime_label, computing_label, composite_scroll, pb);    //initiate runProgram class and pass in label and progress bar to constructor
            run_program.displayNewStage();                                                                //display run_program new stage
        });
        
//                            CLOSE BUTTON LAMBDA EVENT
        close_btn.setOnAction(event ->{
            exitAll exit_all=new exitAll(primaryStage);                                 //initiate exitAll class and pass in primaryStage to constructor
            exit_all.displayNewStage();                                                 //display exit_all new stage
        });
        
        HBox hbox=new HBox(run_btn, close_btn);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(30);
        
        HBox hbox2=new HBox(prime_label, composite_scroll);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.setSpacing(20);
        
        VBox vbox=new VBox(hbox, pb, computing_label, hbox2);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        Scene scene = new Scene(vbox, 1000, 750);
        scene.getStylesheets().add("styles.css");

        primaryStage.setTitle("Prime Numbers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

}
