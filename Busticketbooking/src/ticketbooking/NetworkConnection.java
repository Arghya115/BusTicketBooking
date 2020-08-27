package ticketbooking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class NetworkConnection {
    
    Socket sock ;
    ObjectInputStream ois ;
    ObjectOutputStream oos ;
    
    NetworkConnection( String IP , int port  ) throws IOException
    {
        sock = new Socket( IP , port ) ;
        oos = new ObjectOutputStream(sock.getOutputStream()) ;
        ois = new ObjectInputStream(sock.getInputStream()) ;
    }
    
    NetworkConnection( Socket sockkk  ) throws IOException
    {
        sock = sockkk ;
        oos = new ObjectOutputStream(sock.getOutputStream()) ;
        ois = new ObjectInputStream(sock.getInputStream()) ;
    }
    
    Object read(  ) throws IOException, ClassNotFoundException{ return ois.readObject() ; }
    void write( Object ob  ) throws IOException{ oos.writeObject(ob); }
    
}
