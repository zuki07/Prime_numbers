

// PLEASE HAVE SPEAKERS ON TO ENJOY THE FULL EXPERIENCE

/*TABLE OF CONTENTS
1. SETUP RUN SOUND
2. SETUP DONE SOUND
3. CONSTRUCTOR
4. DISPLAY NEW STAGE
5. YES BUTTON LAMBDA EVENT
6. NO BUTTON LAMBA EVENT
7. PRINT SET METHOD
*/

package prime_numbers;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class runProgram extends Prime_numbers {
    
    private long sleep_value=15;
    
    private Label result_label=new Label();
    private Label composite_label=new Label();
    private Label compute_label=new Label();
    
    private ScrollPane composite_scroll=new ScrollPane();
    
    private List<Integer> prime_list=new ArrayList<>();
    private List<Integer> composite_list=new ArrayList<>();
    
    private ProgressBar p_bar=new ProgressBar();
    
//                                1. SETUP RUN SOUND
    File run_sound=new File("run_sound.wav");
    Media run_media=new Media(run_sound.toURI().toString());
    MediaPlayer run_player=new MediaPlayer(run_media);

//                                2. SETUP DONE SOUND
    File done_sound=new File("done_sound.wav");
    Media done_media=new Media(done_sound.toURI().toString());
    MediaPlayer done_player=new MediaPlayer(done_media);
    
//                               3. CONSTRUCTOR
    public runProgram(Label prime, Label comput_label, ScrollPane composite, ProgressBar pb){
        compute_label=comput_label;
        result_label=prime;                                                                 //define label
        result_label.setMaxWidth(450);                                                      //set label max width
        composite_scroll=composite;                                                         //define scroll pane
        composite_scroll.setPrefSize(480,result_label.getHeight());                         //set width, height of scroll pane
        composite_label.setMaxWidth(450);                                                   //set label max width
        p_bar=pb;                                                                           //define progress bar
    }
    
//                                4. DISPLAY NEW STAGE
    public void displayNewStage(){
        Button yes_btn=new Button("Yes");                                                       //define yes button 
        Button no_btn=new Button("No");                                                         //define no button
        Label exit_all_label=new Label("Are you sure you want to run the program?");            //define exit all label
        
        Stage runStage=new Stage();                                                             //initiate new stage
        
//                                5. YES BUTTON LAMBDA EVENT
        yes_btn.setOnAction((ActionEvent event) ->{
            
            compute_label.setVisible(true);                                                     //display label
            compute_label.setText("Computing...");                                              //set text of label
            p_bar.setVisible(true);                                                             //display progress bar
            run_player.play();                                                                  //start run player sound
            
            Task<Void> sleeper=new Task<Void>() {                                               //initiate new Task for sleep time
                @Override
                protected Void call() throws Exception {
                    try{
                             //start finding prime numbers to fill Prime List to 100
                        int number=2;                                                           //define number to start at 2
                        while (prime_list.size()<100){                                          //list size less than 100, do the following
                            int total=0;                                                        //define total as 0
                            for (int test_number=1; test_number<=number; test_number++){        //step through each test number up to number
                                if (number%test_number==0){                                     //if modulus of number and test number is 0, do the following
                                    if (total<3){                                               //if total is less than 3, do the following
                                    total++;                                                    //add 1 to total
                                    }
                                    else{                                                       //no need to go any further with this number(number is not prime)
                                        break;
                                    }
                                }
                            }
                            if (total==2){                                                      //if total is eaqual to number/1 and number/number, do the following
                                prime_list.add(number);                                         //add number to the List
                            }
                            else{
                                composite_list.add(number);                                     //add number to composite list(not prime)
                            }
                            number++;                                                           //move to next number by 1
                            Thread.sleep(sleep_value);                                          //sleep for 15 milli seconds(slow down program to better display progress bar)
                            double bar=prime_list.size();                                       //define bar as List size
                            bar=bar/100;                                                        //divide by 100 for progress bar 
                            p_bar.setProgress(bar);                                             //set value of progress bar 
                        }
                    }
                    catch (InterruptedException e){
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded((WorkerStateEvent event1) -> {                               //if sleeper succeeded, do the following
                
                compute_label.setText("Finished");                                              //set text of label
                result_label.setVisible(true);                                                  //show label
                result_label.setText("The first 100 prime numbers:\n"+printList(prime_list));   //use printList method to display numbers
                composite_label.setVisible(true);                                               //show label
                composite_label.setText("The composite numbers:\n"+printList(composite_list));  //use printList method to display numbers
                composite_scroll.setVisible(true);                                              //show scroll pane
                composite_scroll.setContent(composite_label);                                   //set contents of scroll pane to label node
                run_player.stop();                                                              //stop run player sound
                done_player.play();                                                             //play done player sound
            });
            new Thread(sleeper).start();                                                        //initiate new thread and pass in sleeper to start
            runStage.close();                                                                   //close second stage
        });
        
//                                6. NO BUTTON LAMBA EVENT
        no_btn.setOnAction(event ->{
            runStage.close();                                                                   //close second stage
            prime_list.clear();                                                                 //clear List
            composite_list.clear();                                                             //clear List
            result_label.setText("");                                                           //empty label
            result_label.setVisible(false);                                                     //hide label
            composite_label.setVisible(false);                                                  //hide label
            compute_label.setVisible(false);                                                    //hide label
            composite_scroll.setContent(composite_label);                                       //empty scroll pane
            composite_scroll.setVisible(false);                                                 //hide scroll pane
            p_bar.setProgress(0);                                                               //reset progress bar
            p_bar.setVisible(false);                                                            //hide progress bar
        });
        
        HBox hbox=new HBox(yes_btn, no_btn);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        VBox vbox=new VBox(exit_all_label, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        Scene runScene=new Scene(vbox, 450, 150 );
        runScene.getStylesheets().add("run-stage.css");
        runStage.setTitle("Run Program");
        runStage.setScene(runScene);
        runStage.show();
    }
    
 //                                    7. PRINT SET METHOD
    public String printList(List<Integer> list){
        String str="";                                                              //define str as string and empty
        Iterator<Integer> it=list.iterator();                                       //initiate iterator
        for (int index=0; index<list.size()-1; index++){                            //step through List till the second to last element 
            if (it.hasNext()){
                str=str+it.next()+", ";                                             //if iterator has next, add comma and space to the end
            }
        }
        str=str+list.get(list.size()-1);                                            //add last item by itself
        return str;
    }

}
