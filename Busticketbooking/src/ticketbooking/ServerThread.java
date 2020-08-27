/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Checker{
    
    synchronized int check( String fname , int[]choice ) throws FileNotFoundException, IOException
    {
        
        System.out.println("Inside check..................................");
        
        int[] availableSeats ;
        
        availableSeats = getAvailableSeats(fname) ;
        
        int cnt = 0 ;
        
        for(int i=0 ; i<40 ; i++) System.out.print(availableSeats[i]);
        System.out.println("");
        
        for(int i=0 ; i<40 ; i++) System.out.print(choice[i]);
        System.out.println("");
        
        
        for(int i= 0 ; i<availableSeats.length ; i++)
        {
            if( availableSeats[i]!=0 && choice[i] !=0  ) return -1 ;
            if( choice[i]!=0 ) cnt++ ;
        }
        
        Writer wr ;
        
        wr = new FileWriter(fname+".txt") ;
        int pin ;
        while(true)
        {
            pin = (((new Random()).nextInt())%10000 + 10000)%10000 + 1 ;
            boolean fl = false ;
            for(int i=0 ; i<availableSeats.length ; i++){
                if(availableSeats[i]==pin || availableSeats[i]==(-pin) ) fl = true ;
            }
            if(fl==false) break ;
        }
        
        
        for(int i=0 ; i<availableSeats.length ; i++)
        {
            if(i!=0) wr.write(" ") ;
            if( availableSeats[i]!=0 ) wr.write( Integer.toString(availableSeats[i]) ) ;
            else if(choice[i]!=0) wr.write( Integer.toString(-pin) ) ;
            else wr.write("0") ;
            
        }
        wr.close() ;
        return pin ;
    }
    
    
    int[] getAvailableSeats(String str) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File(str+".txt"));
        
        int []availableSeats = new int[40] ;
        
        int i = 0 ;
        
        while( scanner.hasNextInt() )
        {
            availableSeats[i] = scanner.nextInt() ;
            i++ ;
        }
        return availableSeats ;
    }

    
}


public class ServerThread implements Runnable {
    
    
    NetworkConnection nc ;
    
    String fname ;
    
    Checker ch ;
    
    ServerThread( Socket sock , Checker chhh ) throws IOException
    {
        nc = new NetworkConnection(sock) ;
        ch = chhh ;
        Thread t = new Thread(this) ;
        
        t.start() ;
    }
    
    

    @Override
    public void run() {
        
        int[] availableSeats ;
        int[] choice ;
        
        while(true)
        {
            try {
                Object ob = nc.read() ;
                
                data d = new data() ;
                
                d = (data)ob ;
                
                fname = d.msg ;
                
                File file1  = new File(fname+".txt") ;
                
                if(!file1.exists())
                {
                    nc.sock.close() ;
                    break ;
                }
                
                System.out.println("this is the fname : "+ fname);
                
                try {
                    availableSeats = ch.getAvailableSeats(fname) ;
                    
                    d.ara = availableSeats ;
                    
                    try {
                        nc.write( d.clone() ) ; //sending available tickets
                        
                        ob = nc.read() ; // getting clients choice
                        
                        d = (data)ob ;
                        
                        choice = d.ara ;
                        
                        for(int i=0 ; i<d.ara.length ; i++) System.out.print(d.ara[i]);
                        System.out.println(" random " + d.cost);
                        
                        int pin = ch.check( fname , choice ) ;
                        
                        if( pin != -1 ){
                            
                            int cnt = 0 ;
                            for(int i=0 ; i<choice.length ; i++) cnt += choice[i] ;
                            int priceOfSeat ;
                            if( fname.charAt( 11 ) =='1' )
                            {
                                priceOfSeat = 800 ;
                            }
                            else priceOfSeat = 500 ;
                            int cost = cnt*priceOfSeat ;
                            d.pass = fname+(Integer.toString(pin)) ;
                            nc.write(d.clone()) ;
                            System.out.println("This is the Cost " + cost) ;
                            
                            long date = System.currentTimeMillis() ;
                            String str = fname +Integer.toString(pin)+ ":" +Integer.toString(cost)+ ":" + Long.toString(date) ;
                            Writer wr ;
                            wr = new FileWriter("PendingList.txt",true) ;
                            File file = new File("PendingList.txt") ;
                            if(file.length()!=0) wr.write(" "+str);
                            else wr.write(str);
                            wr.close() ;
                             //    nc.sock.close() ;
                            break ;
                        } 
                        
                        d.cost = -1 ;
                        nc.write( d.clone() ) ;
                        
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
         //   System.out.println("loop ending") ;
        }
        
    }
    
    
}
