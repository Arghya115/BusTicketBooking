
package ticketbooking;

import java.io.Serializable;

public class data2 implements Serializable , Cloneable {

    public String transactionId , pass ; 
    public boolean flag ;
    
    public Object clone()throws CloneNotSupportedException{  
        return super.clone();  
    }
    
}
