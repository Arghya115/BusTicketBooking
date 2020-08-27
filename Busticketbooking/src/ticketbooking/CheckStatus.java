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

public class CheckStatus {
    
    public static void display(String IP) throws IOException
    {
        NetworkConnection nc = new NetworkConnection(IP,12347) ;
        
        Stage stage = new Stage() ;
        
        AnchorPane rootNode = new AnchorPane() ;
        
        Scene scene  = new Scene(rootNode , 300 , 300) ;
        Image omage = new Image(CheckStatus.class.getResource("check.jpg").toExternalForm());
         BackgroundImage imagp= new BackgroundImage(omage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background imgt = new Background(imagp);
        
        AnchorPane rootNode1 = new AnchorPane() ;
        Scene scene1 = new Scene(rootNode1,250,250) ;
        Label response = new Label("Status: ") ;
        response.setLayoutX(50);
        response.setLayoutY(100);
        response.setFont(Font.font ("Verdana", 18));
        response.setTextFill(Color.web("#D50CD2"));
        
        TextField txf = new TextField() ;
        
        txf.setLayoutX(65);
        txf.setLayoutY(100) ;
        Label get = new Label("Pin Code");
        get.setLayoutX(90);
        get.setLayoutY(70);
        
        get.setFont(Font.font ("Verdana", 20));
        get.setTextFill(Color.web("#D50C21"));
        Image rmage = new Image(CheckStatus.class.getResource("blue.jpg").toExternalForm());
        BackgroundImage imagi = new BackgroundImage(rmage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background imgv = new Background(imagi);
        
        Button submitBtn = new Button("Submit") ;
        
        submitBtn.setLayoutX(100);
        submitBtn.setLayoutY(190);
        submitBtn.setStyle("-fx-background-color: #01FF01;");
        submitBtn.setFont(Font.font(null, FontWeight.EXTRA_BOLD,14));
        
        
        rootNode.getChildren().addAll(txf,submitBtn,get) ;
        rootNode.setBackground(imgt);
        rootNode1.setBackground(imgv);
        rootNode1.getChildren().addAll(response) ;
        submitBtn.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                String str = txf.getText() ;
                data d = new data() ;
                d.msg = str ;
                try {
                    nc.write(d.clone()) ;
                    Object ob = nc.read() ;
                    d = (data)ob ;
                    response.setText("Status: "+d.msg);
                    stage.setScene(scene1) ;
                    System.out.println("Status: "+d.msg);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(CheckStatus.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CheckStatus.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CheckStatus.class.getName()).log(Level.SEVERE, null, ex);
                }
                 }
        } );
        
        stage.setScene(scene) ;
        stage.show() ;
    }
    
}
