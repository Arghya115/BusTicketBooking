/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arg_007
 */
class CheckPayment{
    
    synchronized boolean  check(String pass , String transactionId) throws IOException
    {
        Scanner sc = new Scanner(new File("transactionList.txt")) ;
        
        String trans[] = new String[10000] , pending[] = new String[10000] ;
        int i = 0 , fl1=-1 , fl2=-1 , l1 , l2 , paid = -1 , shouldPay = 0 ;
        while( sc.hasNext() )
        {
            trans[i] = sc.next() ;
            String s[] = new String[2] ;
            s =  trans[i].split(":") ;
            if(s[0].equals(transactionId))
            {
                fl1 = i ;
                paid = Integer.parseInt(s[1]) ;
            }
            i++ ;
        }
        l1 = i ;
        
        sc = new Scanner(new File("pendingList.txt")) ;
        i = 0 ;
        while(sc.hasNext())
        {
            pending[i] = sc.next() ;
            String s[] = new String[3] ;
            s = pending[i].split(":") ;
            if( s[0].equals(pass) )
            {
                fl2 = i ;
                shouldPay = Integer.parseInt(s[1]) ;
            }
            i++ ;
        }
        l2 = i ;
        System.out.println("shouldPay: "+shouldPay+" paid:"+paid );
        if( (shouldPay<=paid) && (paid!=-1) && (shouldPay!=0) )
        {
            Writer wr ;
            wr = new FileWriter("transactionList.txt") ;
            
            i = 0 ;
            while(i<l1 )
            {
                if( i==fl1 )
                {
                    i++ ;
                    continue ;
                }
                if( i>1 || (i==1 && fl1!=0) ) wr.write(" ") ;
                wr.write(trans[i]);
                i++ ;
            }
            wr.close();
            
            wr = new FileWriter("pendingList.txt") ;
            
            i = 0 ;
            while(i<l2 )
            {
                if( i==fl2 )
                {
                    i++ ;
                    continue ;
                }
                if( i>1 || (i==1 && fl2!=0) ) wr.write(" ") ;
                wr.write(pending[i]);
                i++ ;
            }
            wr.close();
            int []seats = new int[45] ;
            String str ;
            str = pass.substring(0,12) ;
            sc = new Scanner( new File(str+".txt") ) ;
            i = 0 ;
            while(sc.hasNextInt())
            {
                seats[i] = sc.nextInt() ;
                i++ ;
            }
            int pin = Integer.parseInt( pass.substring( 12 , pass.length() ) ) ;
            l1 = i ;
            
            wr = new FileWriter( pass.substring(0,12) + ".txt" ) ;
            
            for(i=0 ; i<l1 ; i++)
            {
                if(i!=0) wr.write(" ") ;
                if(seats[i]==-pin) wr.write( Integer.toString(-seats[i]) );
                else wr.write( Integer.toString(seats[i]) ) ;
            }
            wr.close() ;
        }
        if((shouldPay<=paid) && (paid!=-1) && (shouldPay!=0)) return true ;
        else return false ;
    }
    
}

public class ConfirmationThread implements Runnable {
    
    NetworkConnection nc ;
    CheckPayment ch ;
    
    public ConfirmationThread(NetworkConnection nccc , CheckPayment chhh)
    {
        nc = nccc ; ch = chhh ;
        Thread t = new Thread(this) ;
        t.start() ;
        System.out.println("Thread Sarted");
    }

    @Override
    public void run() {
        
       while(true)
       {
            data2 d = new data2() ;
        Object ob ;
        try {
            ob = nc.read() ;
            d = (data2)ob ;
            System.out.println(d.pass + " " + d.transactionId);
            boolean flag  = ch.check(d.pass, d.transactionId) ;
            d.flag = flag ;
           nc.write( d.clone() );
           if(flag==true) break ;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConfirmationThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ConfirmationThread.class.getName()).log(Level.SEVERE, null, ex);
        }

       }
        
        
        
    }
    
}
