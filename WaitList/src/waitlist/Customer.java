/*
class: Customer
description: Processing customer info
variables: String guestName, private int numInParty, waitTime, totalWait
*          Queue queue, 
methods: set/getGuestName, set/getNumInParty, getWaitTime, 
*        getAvgWait, getTotalWait, toString
 */
package waitlist;
 
import java.util.LinkedList; 
import java.util.Queue;

class Customer { 
    private String guestName;
    private int numInParty;
    private int waitTime;
    private int totalWait;
    private int currentCapacity;
    private int avgWait; 
    Queue<Customer> queue = new LinkedList<Customer>();
    
    public Customer (){
         
    }
    
    //Construct customer with specified name, party size, wait time, and total wait
    public Customer (String guestName, int numInParty, int waitTime, int totalWait,
            int currentCapacity, int avgWait) {
        this.guestName = guestName; 
        this.numInParty = numInParty;
        this.waitTime = waitTime;
        this.totalWait = totalWait; 
        this.currentCapacity = currentCapacity;
        this.avgWait = avgWait; 
    }
    
    // returning guestName
    public String getGuestName () {
        return guestName;   
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName; 
    }
     
    // setting numInParty
    public void setNumInParty (int numInParty) {
        this.numInParty = numInParty;
    }
    // returning numInParty
    public int getNumInParty () {
        return numInParty; 
    }
    // returning currentCapacity
    public int getCurrentCapacity () {
        return this.currentCapacity; 
    }
    // method for returning waittime
    public int getWaitTime () {
        return waitTime; 
    }
    // method for returning total wait time
    public int getTotalWait () {
        return totalWait; 
    }
    // method for returning avg wait
    public int getAvgWait () {
        return this.getTotalWait() / this.getCurrentCapacity(); 
    }
    // custom toString method for printing to textarea
    @Override
    public String toString () {
        String result = "";
        result = "Guest Name: " + this.getGuestName() + 
                "\nParty Size: "  + this.getNumInParty() + 
                "\nWait Time: " + this.getWaitTime() + 
                "\nTotal Wait Time: " + this.getTotalWait() +
                "\nAverage Wait Time: " + this.getAvgWait()
                +"\n";  
        return result;
    }        
    
} // end class customer 
