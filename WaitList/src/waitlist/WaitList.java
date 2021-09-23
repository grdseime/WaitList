/*
Course: CS - 282
Project: Program #1 WaitList
Name: Grant Seime
Date: 04/29/2019
Description: App for adding users to a restuarant wait list and displaying
             the list. Prints FIFO.  
 */
package waitlist;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import javafx.application.Application; 
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;  
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
 
/**
 *
 * @author Grant Seime
 */
public class WaitList extends Application {
    // textfields, textarea, and two buttons for user inputs
    // wait time calculated for btAdd
    private TextField tfGuestName = new TextField(); 
    private TextField tfNumInParty = new TextField();
    private TextField tfWaitTime = new TextField("0");
    private TextField tfTotalWait = new TextField("0");
    private TextField tfAvgWaitTime = new TextField("0"); 
    private Button btAdd = new Button("Add to list");
    private Button btShowQueue = new Button("Show Queue");
    private TextArea taShowQueue = new TextArea("Display Queue Here");
    private int waitTime = (int)(Math.random() * 61);
    private int currentCapacity = 0;    
    private int avgWait = 0;
    private int numOfParties = 0; 
            Customer customer;
            Queue<String> queue = new LinkedList<String>();
     
    @Override 
    public void start(Stage primaryStage) {
        //Create UI w/ gridpane
        GridPane gridPane= new GridPane();
        try { 
        gridPane.setHgap(5); 
        gridPane.setVgap(5); 
        gridPane.add(new Label("Guest Name:"), 0, 0);
        gridPane.add(tfGuestName, 1, 0); 
        gridPane.add(new Label("Number in Party:"), 0, 1);
        gridPane.add(tfNumInParty, 1, 1); 
        gridPane.add(new Label("Wait Time:"), 0, 2);
        gridPane.add(tfWaitTime, 1, 2); 
        gridPane.add(new Label("Total Wait Time:"), 0, 3);
        gridPane.add(tfTotalWait, 1, 3); 
        gridPane.add(tfAvgWaitTime, 1, 4); 
        gridPane.add(new Label("Average Wait Time: "), 0, 4);
        gridPane.add(btAdd, 1, 5);
        gridPane.add(btShowQueue, 1, 6);         
        gridPane.add(new Label("Seating Info:"), 0, 7);
        gridPane.add(taShowQueue, 1, 7);
         
        //UI properties
        gridPane.setAlignment(Pos.CENTER); 
        tfGuestName.setAlignment(Pos.BOTTOM_LEFT);
        tfNumInParty.setAlignment(Pos.BOTTOM_LEFT);
        tfWaitTime.setAlignment(Pos.BOTTOM_LEFT);
        tfTotalWait.setAlignment(Pos.BOTTOM_LEFT);
        tfWaitTime.setEditable(false);
        tfTotalWait.setEditable(false);
        taShowQueue.setEditable(false);
        tfWaitTime.setText(String.valueOf(waitTime));

        //Process events
        // btAdd action
        
        btAdd.setOnAction(e -> { 
        if (!(tfGuestName.getText() == null) && (Integer.parseInt(tfNumInParty.getText()) >= 0)) {
        // create variables for processing user input
        String guestName = tfGuestName.getText();                   // guest name
        int numInParty = Integer.parseInt(tfNumInParty.getText());  // party size
        int totalWait = Integer.parseInt(tfTotalWait.getText()) + waitTime; // total wait time
            numOfParties++; 
        currentCapacity = Integer.parseInt(tfNumInParty.getText()) + currentCapacity; // current capacity
        avgWait = totalWait/currentCapacity; // avg wait
       
        // customer object for passing user input. Note: add wait times back in
        customer = new Customer(guestName, 
                numInParty,
                waitTime,
                totalWait,
                numOfParties,
                avgWait);    
        queue.offer(customer.toString());
        
        // repopulate form for next input
        tfTotalWait.setText(String.valueOf(totalWait));
        waitTime = (int)(Math.random() * 61);
        tfWaitTime.setText(String.valueOf(waitTime));
        tfAvgWaitTime.setText(String.valueOf(customer.getAvgWait()));
        tfGuestName.setText("");
        tfNumInParty.setText(""); 
        } else {
            taShowQueue.setText("No user added. Field may be  missing. "
                    + "Please try again.");
            System.out.println("No user added. Field may be  missing. "
                    + "Please try again.");
        } 
        }); // end btAdd action 
        } catch(RuntimeException ex) {
            taShowQueue.setText("Error inputing customer info. "
                    + "Please try again.");
        } catch(Exception ex) {
            taShowQueue.setText("Error inputing customer info. "
                    + "Please try again.");
        } 
        // btShowQueue Action
        btShowQueue.setOnAction( e-> {
            // Flesh out custom customer toString method 
            String result = "";
            while (queue.size()>0)
                result += queue.poll() + "\n";
            taShowQueue.setText(result);
            System.out.println(avgWait);
        }); // end btShowQueue action

        Scene scene = new Scene(gridPane, 600, 600);
        
        primaryStage.setTitle("Wait List"); 
        primaryStage.setScene(scene);
        primaryStage.show();
    } // end start

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }  
} // end WaitList
