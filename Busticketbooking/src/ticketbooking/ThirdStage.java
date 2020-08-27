/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking;

import com.sun.deploy.ui.UIFactory;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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

public class ThirdStage {
    
    static int ara[] = new int[45] , availableSeats[] = new int[45] , i , acCost=800 , nonAcCost=500 , currentCost = 0 , priceOfSeat  ;
    static String str ;
    static NetworkConnection nc ;
 //   int ara[] = new int[20] , i ;

    static int[] getAvailableSeats(NetworkConnection nc ) throws IOException, ClassNotFoundException
    {
        Object ob = nc.read() ;
        
        data d = new data() ;
        
        d = (data)ob ;
        
        return d.ara ;
    }  
    
    
    static void display(String strrr, String IP) throws IOException, CloneNotSupportedException, ClassNotFoundException
    {
        System.out.println(" In the third STAGE............................ " + strrr );
        
        str = strrr ;
        
        if(str.charAt(11)=='1') priceOfSeat = acCost ;
        else priceOfSeat = nonAcCost ;
        
 //       priceOfSeat = 800 ;
 
        int []choice = new int[50] ;
        
        for( i= 0 ; i<50 ; i++) choice[i] = 0 ;
        
        nc = new NetworkConnection( IP , 12344 ) ;
        
        data d = new data() ;
        d.msg = str ;
        nc.write(d.clone()) ;
        
        availableSeats = getAvailableSeats(nc) ; 
        
        for(i =0 ; i<availableSeats.length ; i++) System.out.print(availableSeats[i]);
        System.out.println(" end ");
        
        AnchorPane root2 = new AnchorPane() ;
        Scene scene2 = new Scene(root2,400,400) ;
        
        
        
        Stage stage = new Stage() ;
        AnchorPane root = new AnchorPane() ;
        Image rmage = new Image(ThirdStage.class.getResource("blue.jpg").toExternalForm());
        BackgroundImage imagi = new BackgroundImage(rmage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background imgv = new Background(imagi);
       
        Scene scene = new Scene(root,600,600);
        CheckBox[] cb = new CheckBox[40] ; 
        Label showCurrentCost = new Label("Your Cost is: 0") ;
        Label showPass = new Label("Your password is: ") ;
        Label choose = new Label("Choose Your Seat");
        
        //showCurrentCost.setFont(Font.font ("Verdana", 10));
        showCurrentCost.setTextFill(Color.web("#4D0443"));
        //showCurrentCost.setFont(Font.font(null, FontWeight.EXTRA_BOLD,16 ));
        
        showCurrentCost.setLayoutX(400) ;
        showCurrentCost.setLayoutY(100) ;
        showCurrentCost.setPrefHeight(50);
        showCurrentCost.setPrefWidth(100);
        
        for(i=0 ; i<40 ; i++ ) ara[i] = 0 ;
        
        Button btn = new Button("Continue") ;
        Button prevBtn = new Button("Previous") ;
        Label sts = new Label("Red Marked");
        Label stt = new Label("Are Booked");
        
        prevBtn.setLayoutX(400);
        prevBtn.setLayoutY(200);
        prevBtn.setStyle("-fx-background-color: #20C10D;");
        prevBtn.setFont(Font.font(null, FontWeight.EXTRA_BOLD,16));
        
        sts.setLayoutX(400);
        sts.setLayoutY(300);
        
        sts.setFont(Font.font ("Verdana", 16));
        sts.setTextFill(Color.web("#ED0F0F"));
        sts.setFont(Font.font(null, FontWeight.EXTRA_BOLD,16));
        
        stt.setLayoutX(400);
        stt.setLayoutY(320);
        
        stt.setFont(Font.font ("Verdana", 16));
        stt.setTextFill(Color.web("#ED0F0F"));
        stt.setFont(Font.font(null, FontWeight.EXTRA_BOLD,16));
        
        choose.setLayoutX(260);
        choose.setLayoutY(550);
        
        choose.setFont(Font.font ("Verdana", 18));
        choose.setTextFill(Color.web("#16ED0F"));
        choose.setFont(Font.font(null, FontWeight.EXTRA_BOLD,18));
        
        
                

        showPass.setFont(Font.font ("Verdana", 16));
        showPass.setTextFill(Color.web("#0076a3"));
        
        root.setBackground(imgv);
        root.getChildren().add(sts) ;
        root.getChildren().add(stt) ;
        root.getChildren().add(choose) ;
        root.getChildren().add(prevBtn) ;
        root2.getChildren().add(showCurrentCost) ;
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                data d = new data() ;
                d.ara = ara.clone() ;
             //   d.cost = (new Random()).nextInt() ;
                try {
                    System.out.println("before sending "+d.cost);
                    for(i=0 ; i<ara.length ; i++) System.out.print(d.ara[i]);
                    System.out.println();
                    nc.write( d.clone() ) ;
                } catch (IOException | CloneNotSupportedException ex) {
                    Logger.getLogger(ThirdStage.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    Object ob = nc.read() ;
                    d = (data)ob ;
             //       System.out.println("");
                    if( d.cost==-1 )
                    {
                                                
                        d.msg = str ;
                        nc.write(d.clone()) ;
                        ob = nc.read() ;
                        d = (data)ob ;
                        availableSeats = d.ara ;
                        
                        System.out.println("negetive cost detected");
                        for(i=0 ; i<40 ; i++) System.out.print(availableSeats[i]);
                        System.out.println("");
                        for(i=0 ; i<40 ; i++) System.out.print(ara[i]);
                        System.out.println("");

                        
                        System.out.println(" Now the cost is :" + currentCost);
                        for(i=0 ; i<40 ; i++){
                            char ch = (char)(i/4 + 65) ;
                            String seat = Character.toString(ch) ;
                            seat = seat + "-" + Integer.toString( i%4 + 1 ) ;   
            
                         //   cb[i] = new CheckBox(seat) ;
        
                        //    cb[i].setLayoutY((i/4)*50 + 20 ) ;
            
                          //  cb[i].setLayoutX( (i%4)*90 + 20 ) ;
                            cb[i].setText(seat) ;
                            if( availableSeats[i]==0 ) cb[i].setTextFill(Color.BLUE);
                            else{
                                if( ara[i]==1 )
                                {
                                    ara[i] = 0 ;
                                    cb[i].setTextFill(Color.BLACK);
                                    currentCost -= priceOfSeat ;
                                    showCurrentCost.setText( "Your Cost is :" + Integer.toString(currentCost) ) ;
                                    cb[i].setSelected(false) ;
                                }
                                else cb[i].setTextFill(Color.RED ) ;
                            } 
                       //     root.getChildren().add(cb[i]) ;
                        }
                        System.out.println("") ;
                    }
                    else{
                        System.out.println("client's cost "+d.cost) ;
                        
                        Label showCost = new Label() ;
                        Label soi = new Label ("Please pay your amount in 5 minute");
                        Label sui = new Label (" Save this password ");
                        Label thnx = new Label();
                        Label gui = new Label("Bikash NO : 01742286625");
                        showCost.setText(" Your Current Cost is : " + Integer.toString(currentCost) );
                        showPass.setText("Your Pin number is: " +d.pass);
                        showCost.setLayoutX(10);
                        showCost.setLayoutY(140);
                        showPass.setLayoutX(8);
                        showPass.setLayoutY(170);
                        
                        soi.setLayoutX(40);
                        soi.setLayoutY(270);
                        sui.setLayoutX(100);
                        sui.setLayoutY(250);
                        gui.setLayoutX(80);
                        gui.setLayoutY(290);
                        thnx.setText("Thank You Sir!!!!");
                        
                        showPass.setTextFill(Color.web("#680073"));
                        showPass.setFont(Font.font(null, FontWeight.EXTRA_BOLD,20));
                        
                        thnx.setFont(Font.font ("Verdana", 20));
                        thnx.setTextFill(Color.web("#F7FB00"));
                        thnx.setFont(Font.font(null, FontWeight.EXTRA_BOLD,20));
                        
                        sui.setFont(Font.font ("Verdana", 14));
                        sui.setTextFill(Color.web("#0FED66"));
                        sui.setFont(Font.font(null, FontWeight.EXTRA_BOLD,20));
                        
                        soi.setFont(Font.font ("Verdana", 14));
                        soi.setTextFill(Color.web("#0FED66"));
                        soi.setFont(Font.font(null, FontWeight.EXTRA_BOLD,20));
                        
                        gui.setFont(Font.font ("Verdana", 14));
                        gui.setTextFill(Color.web("#ED0F3B"));
                        gui.setFont(Font.font(null, FontWeight.EXTRA_BOLD,20));
                        
                        thnx.setLayoutX(105);
                        thnx.setLayoutY(350);
                        
                        showCost.setTextFill(Color.web("#117300"));
                        showCost.setFont(Font.font ("Verdana", 16));
                        showCost.setFont(Font.font(null, FontWeight.EXTRA_BOLD,20));
                     
                        
                        
                       // showPass.setTextFill(Color.web("#0076a3"));
                        //showPass.setTextFill(Color.web("#0076a3"));
                        
                        Image rmage = new Image(ThirdStage.class.getResource("check.jpg").toExternalForm());
                        BackgroundImage imagi = new BackgroundImage(rmage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
                        Background imgv = new Background(imagi);
                        root2.getChildren().addAll(showPass , showCost,thnx,sui,soi,gui) ;
                        root2.setBackground(imgv);
                        stage.setScene(scene2) ;
                      //  stage.close() ;
                    }
                   
                } catch (IOException ex) {
                    Logger.getLogger(ThirdStage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ThirdStage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(ThirdStage.class.getName()).log(Level.SEVERE, null, ex);
                }       
                
            }
        });
        root.getChildren().addAll(btn,showCurrentCost) ;
        btn.setLayoutX(500);
        btn.setLayoutY(200);
        btn.setStyle("-fx-background-color: #A2A421;");
        btn.setFont(Font.font(null, FontWeight.EXTRA_BOLD,16));
        for( i = 0 ; i<40 ; i++ )
        {
            char ch = (char)(i/4 + 65) ;
            String seat = Character.toString(ch) ;
            seat = seat + "-" + Integer.toString( i%4 + 1 ) ;   
            
            cb[i] = new CheckBox(seat) ;
        
            cb[i].setLayoutY((i/4)*50 + 20 ) ;
            
            cb[i].setLayoutX( (i%4)*90 + 20 );
            if(availableSeats[i]==0) cb[i].setTextFill( Color.BLUE  ) ;
            else cb[i].setTextFill( Color.RED ) ;
            root.getChildren().add(cb[i]) ;
            
        }     
        for(i=0 ; i<40 ; i++)
        {
                cb[i].setOnAction(new EventHandler<ActionEvent>() {
                
                int x = i ;
                        
                @Override
                public void handle(ActionEvent event) {
                    
                    if( availableSeats[x]!=0 ) cb[x].setSelected(false);
                    else if( cb[x].isSelected() == true ){
                        ara[x] = 1 ;
                        currentCost += priceOfSeat ;
                        showCurrentCost.setText("Your cost is: "+Integer.toString(currentCost)) ;
                    } 
                    else{
                        ara[x] = 0 ;
                        currentCost -= priceOfSeat ;
                        showCurrentCost.setText("Your cost is: "+Integer.toString(currentCost)) ;
                    } 
                }
            });   
        }
        prevBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                stage.close() ;
                
                try {
                    nc.sock.close() ;
                } catch (IOException ex) {
                    Logger.getLogger(ThirdStage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        stage.setScene(scene);
        stage.show();
       
    }
}