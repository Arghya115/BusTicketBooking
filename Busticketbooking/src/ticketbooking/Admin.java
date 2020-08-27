
package ticketbooking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Admin {
    
    public static void cancelBooking(String s) throws FileNotFoundException, IOException
    {
        Scanner scn = new Scanner( new File(s.substring(0,12) + ".txt") ) ;
        
        int pin = Integer.parseInt(  s.substring(12,s.length()) ) ;
        
        int []seats = new int[45] ;
        
        int i = 0 ;
        
        while(scn.hasNextInt())
        {
            int x= scn.nextInt() ;
            if( x== -pin ) seats[i] = 0 ;
            else seats[i] = x ;
            i++ ;
        }
        int l = i ;
        
        Writer wr = new FileWriter( s.substring(0,12) + ".txt" ) ;
        
        for(i=0 ; i<l ; i++){
            
            if(i!=0) wr.write(" ") ;
            wr.write(Integer.toString(seats[i]));
        }
        wr.close() ;
    }
    
    public static void adminFunction()
    {
        System.out.println("In the admin function.........");
        Stage stage = new Stage() ;
        
        AnchorPane rootNode = new AnchorPane() ;
        
        Scene scene = new Scene(rootNode, 400,400) ;
        
        Button refreshBtn = new Button("Refresh") ;
        Button submitBtn = new Button("Submit") ;
        
        refreshBtn.setLayoutX(300);
        refreshBtn.setLayoutY(280);
        refreshBtn.setStyle("-fx-background-color: #56D201;");
        refreshBtn.setFont(Font.font(null, FontWeight.EXTRA_BOLD,18));
        
        TextField transactionId = new TextField() ;
        TextField amount = new TextField() ;
        
        transactionId.setPromptText("ID");
        transactionId.setLayoutX(180);
        transactionId.setLayoutY(150);
        
        amount.setPromptText("Amount");
        amount.setLayoutX(180);
        amount.setLayoutY(200);
        
        submitBtn.setLayoutX(50);
        submitBtn.setLayoutY(280);
        submitBtn.setStyle("-fx-background-color: #01BFD2;");
        submitBtn.setFont(Font.font(null, FontWeight.EXTRA_BOLD,18));
        
        Label nick = new Label("Transaction ID");
        Label response = new Label("Amount");
        nick.setLayoutX(42);
        nick.setLayoutY(150);
  
        response.setLayoutX(100);
        response.setLayoutY(200);
        
        response.setFont(Font.font ("Verdana", 18));
        nick.setFont(Font.font ("Verdana", 18));
        
        response.setTextFill(Color.web("#023500"));
        nick.setTextFill(Color.web("#D20101"));
        
        Image rmage = new Image(Admin.class.getResource("trnc.jpg").toExternalForm());
        BackgroundImage imagi = new BackgroundImage(rmage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background imgv = new Background(imagi);
                
        
        rootNode.getChildren().addAll(submitBtn,refreshBtn,transactionId,amount,response,nick) ;
        rootNode.setBackground(imgv);
        
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                String transaction = transactionId.getText() ;
                String money = amount.getText() ;
                
                transactionId.setText("") ;
                amount.setText("") ;
                Writer wr ;
                try {
                    wr = new FileWriter("transactionList.txt",true) ;
                    wr.write(" "+transaction+":"+money);
                    wr.close() ;
                } catch (IOException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        refreshBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                String ara[] = new String[10000] ;
                
                int i = 0 ;
                
                Scanner scn ;
                try {
                    scn = new Scanner(new File("pendingList.txt") );
                    
                   while( scn.hasNext() )
                   {
                    String str = scn.next() ;   
                    String s[] = str.split(":") ;
                    
                    Long then = Long.parseLong(s[2]) ;
                    Long now = System.currentTimeMillis() ;
                    
                    if( (now-then) <= (5*60*1000) )
                    {
                        System.out.println(" Timer Condition satisfied............... ");
                        ara[i] = str ;
                        i++ ;
                    }
                    else cancelBooking(s[0]) ;
                   }
                    int l = i ;
                    Writer wr = new FileWriter("pendingList.txt") ;
                    
                    for(i=0 ; i<l ; i++)                    

                    {
                        if(i!=0) wr.write(" ") ;
                        wr.write(ara[i]) ;
                        System.out.println(ara[i]);
                    }
                    wr.close() ;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }
        });
        stage.setScene(scene) ;
        stage.show() ;
    }
    
    public static void display()
    {
        Stage stage = new Stage() ;
        
        AnchorPane rootNode = new AnchorPane() ;
        
        Scene scene = new Scene(rootNode , 400 , 400) ;
        
        TextField id = new TextField() ;
        TextField pass = new TextField() ;
        
        id.setLayoutX(160);
        id.setLayoutY(150);
        pass.setLayoutX(160);
        pass.setLayoutY(200);
        
        Button submitBtn = new Button("Submit") ;
        
        submitBtn.setLayoutX(160);
        submitBtn.setLayoutY(320);
        submitBtn.setFont(Font.font ("Verdana", 16));
        submitBtn.setTextFill(Color.web("#730040"));
        submitBtn.setFont(Font.font(null, FontWeight.EXTRA_BOLD,20));
        
        
        Label idea = new Label("Name");
        Label pos = new Label("Password");
        
        
        idea.setLayoutX(100);
        idea.setLayoutY(150);
  
        pos.setLayoutX(70);
        pos.setLayoutY(200);
        
        idea.setFont(Font.font ("Verdana", 18));
        pos.setFont(Font.font ("Verdana", 18));
        
        idea.setTextFill(Color.web("#D5005E"));
        pos.setTextFill(Color.web("#350200"));
        
        idea.setFont(Font.font(null, FontWeight.EXTRA_BOLD,18));
        pos.setFont(Font.font(null, FontWeight.EXTRA_BOLD,18));
        Image pmage = new Image(Admin.class.getResource("admin.jpg").toExternalForm());
        BackgroundImage imagt = new BackgroundImage(pmage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background imgo = new Background(imagt);
        
        rootNode.getChildren().addAll(id,pass,submitBtn,idea ,pos) ;
        rootNode.setBackground(imgo);
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                  
                String name = id.getText() ; String password = pass.getText() ;
                
                System.out.println(name + "submitBtn" + password );
                
                if( name.equals("A") && password.equals("1") ){
                    adminFunction() ;
                    stage.close() ;
                } 
                
            }
        });
        stage.setScene(scene) ;
        stage.show() ;
    }
    
}
