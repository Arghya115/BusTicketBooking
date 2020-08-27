package ticketbooking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket ss = new ServerSocket(12344) ;
        
        Checker ch = new Checker() ;
        
        while(true)
        {
            Socket s = ss.accept() ;
            
            System.out.println(" A new user connected.............. ");
            
            new ServerThread(s,ch) ;
            
        }
        
        
        
    }
    
    
    
}
