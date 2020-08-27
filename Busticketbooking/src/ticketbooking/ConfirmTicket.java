/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking;

import java.io.IOException;
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

public class ConfirmTicket {
    
    public static void display(String IP) throws IOException{
        
        NetworkConnection nc =  new NetworkConnection(IP,12349) ;
        
        Stage stage = new Stage() ;
        
        AnchorPane rootNode = new AnchorPane() ;
        
        Scene scene = new Scene(rootNode , 500 ,400) ;
        
        TextField pass = new TextField() ;
        TextField transactionId = new TextField() ;
        Label trns = new Label("Transaction ID :");
        Label pss = new Label("Password :");
        
        trns.setLayoutX(80);
        trns.setLayoutY(150);
        pss.setLayoutX(130);
        pss.setLayoutY(200);
        
        trns.setFont(Font.font ("Verdana", 18));
        pss.setFont(Font.font ("Verdana", 18));
        
        trns.setTextFill(Color.web("#D20101"));
        pss.setTextFill(Color.web("#0F1E9E"));
        
        transactionId.setLayoutX(240);
        transactionId.setLayoutY(150);
        transactionId.setPromptText("Transaction");
        pass.setLayoutX(240);
        pass.setLayoutY(200);
        pass.setPromptText("Password");
        Button submitBtn  = new Button("Submit") ;
        
        Label response = new Label();
        response.setLayoutX(180);
        response.setLayoutY(300);
        submitBtn.setLayoutX(220);
        submitBtn.setLayoutY(330);
        submitBtn.setStyle("-fx-background-color: #CD009E;");
        submitBtn.setFont(Font.font(null, FontWeight.EXTRA_BOLD,18));
        
        
        Image rmage = new Image( ConfirmTicket.class.getResource("bus1.jpg").toExternalForm());
        BackgroundImage imagi = new BackgroundImage(rmage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background imgv = new Background(imagi);
        
        rootNode.getChildren().addAll(pass,transactionId,submitBtn,trns,pss) ;
        rootNode.setBackground(imgv);
        
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                data2 d = new data2() ;
                
                d.transactionId = transactionId.getText() ;
                d.pass = pass.getText() ;
                
             // d.transactionId = "123456" ;
          //    d.pass = "1116122016113319" ; 
                try {
                    nc.write(d.clone()) ;
                    Object ob = nc.read() ;
                    d = (data2)ob ;
                    if(d.flag==true) stage.close() ;
                    else {
                        response.setText("Invalid Submission");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ConfirmTicket.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(ConfirmTicket.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ConfirmTicket.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        stage.setScene(scene) ;
        stage.show() ;
        
    }
    
    
}
