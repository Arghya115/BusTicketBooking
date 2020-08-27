
package ticketbooking;

import java.io.Serializable;

public class data implements Serializable , Cloneable {
    
    public String msg , pass ;
    
    public int[] ara ; 
    public int cost ;
    
    public Object clone()throws CloneNotSupportedException{  
        return super.clone();  
    }
    
}
