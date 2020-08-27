package ticketbooking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConfirmationServer {
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket ss = new ServerSocket(12349) ;
        
        CheckPayment ch = new CheckPayment() ;
        
        while(true)
        {
            Socket socket = ss.accept() ;
            
            NetworkConnection nc = new NetworkConnection(socket) ;
            
            new ConfirmationThread(nc , ch) ;
            
        }
                
        
    }
    
    
}
