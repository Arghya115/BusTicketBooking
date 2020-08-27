/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class StatusServer {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException {
        
        ServerSocket ss = new ServerSocket(12347) ;
        
        while(true)
        {
            Socket socket = ss.accept() ;
            
            NetworkConnection nc = new NetworkConnection(socket) ;
            
            Object ob = nc.read() ;
            
            data d = (data)ob ;
            
            String str = d.msg ;
            
            if( d.msg.length() < 12 ){
                d.msg = "not booked" ;
                nc.write(d.clone()) ;
                break ;
            }
            
            File file = new File(d.msg.substring(0, 12) + ".txt") ;
            
            if(!file.exists())
            {
                d.msg = "not booked" ;
                nc.write(d.clone()) ;
                break ;
            }
            
            Scanner sc = new Scanner( file ) ;
            
            System.out.println( d.msg.substring(0, 12) + " " + d.msg.substring(12,d.msg.length()) );
            
            int pin = Integer.parseInt(d.msg.substring(12,d.msg.length())) ;
            
            int fl = -1 ;
            while( sc.hasNextInt() )
            {
                int x = sc.nextInt() ;
                System.out.println(x  + " ");
                if( x==-pin ) fl = 0 ;
                if(x==pin) fl = 1 ;
            }
            if(fl==-1) d.msg = "not booked" ;
            if(fl==0) d.msg = "pending" ;
            if(fl==1) d.msg = "booked" ;
            
            nc.write( d.clone() );
            break ;
        }
        
    }
    
}
