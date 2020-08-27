
package ticketbooking;

import java.io.IOException;
import static java.nio.file.Files.size;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.beans.binding.Bindings.size;
import static javafx.beans.binding.Bindings.size;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TicketBooking extends Application {
    
    TextField tf;
    Label response;
    Label nick;
    Label ip;
    
    public static void main(String[] args) {
// Start the JavaFX application by calling launch().
    launch(args);
}
// Override the start() method.
    @Override
    public void start(Stage myStage) {
    
    AnchorPane rootNode = new AnchorPane() ;
    
   
    Scene scene = new Scene(rootNode , 500 , 500) ;
    
    ToggleGroup tg = new ToggleGroup() ;
    
    Button submitBtn = new Button("Submit") ;
    
    nick = new Label("WELCOME");
    response = new Label("BUS TICKET BOOKING SERVICE");
    ip = new Label("Enter IP Address");
    
    nick.setLayoutX(200);
    nick.setLayoutY(30);
  
    response.setLayoutX(100);
    response.setLayoutY(55);
    
    ip.setLayoutX(162);
    ip.setLayoutY(150);
  
  
    submitBtn.setLayoutX(400);
    submitBtn.setLayoutY(400);
    submitBtn.setStyle("-fx-background-color: #ff0000;");
    submitBtn.setFont(Font.font(null, FontWeight.EXTRA_BOLD,16));
    

    RadioButton bookTicket = new RadioButton("Ticket Booking") ;
    RadioButton checkStatus = new RadioButton("Check My Status") ;
    RadioButton confirmation = new RadioButton("Confirm Booking") ;
    RadioButton adminMode = new RadioButton("Open in administrator mode") ;
    TextField txf = new TextField() ;
    Image rmage = new Image(TicketBooking.class.getResource("scene4.jpg").toExternalForm());
    
    BackgroundImage imagi = new BackgroundImage(rmage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    
    Background imgv = new Background(imagi);
    
    bookTicket.setToggleGroup(tg);
    checkStatus.setToggleGroup(tg);
    confirmation.setToggleGroup(tg);
    adminMode.setToggleGroup(tg);
    
    bookTicket.setLayoutX(50);
    bookTicket.setLayoutY(250);
    
    checkStatus.setLayoutX(50);
    checkStatus.setLayoutY(300);
    
    confirmation.setLayoutX(50);
    confirmation.setLayoutY(350);
    
    adminMode.setLayoutX(50);
    adminMode.setLayoutY(400);
    
    bookTicket.setFont(Font.font ("Verdana", 16));
    bookTicket.setFont(Font.font(null, FontWeight.EXTRA_BOLD,16 ));
    checkStatus.setFont(Font.font ("Verdana", 16));
    checkStatus.setFont(Font.font(null, FontWeight.EXTRA_BOLD,16 ));
    confirmation.setFont(Font.font ("Verdana", 16));
    confirmation.setFont(Font.font(null, FontWeight.EXTRA_BOLD,16 ));
    adminMode.setFont(Font.font ("Verdana", 16));
    adminMode.setFont(Font.font(null, FontWeight.EXTRA_BOLD,16 ));
        
    bookTicket.setTextFill(Color.web("#FF334C"));
    checkStatus.setTextFill(Color.web("#FF334C"));
    confirmation.setTextFill(Color.web("#FF334C"));
    adminMode.setTextFill(Color.web("#FF334C"));
    
    response.setFont(Font.font ("Verdana",FontPosture.ITALIC, 20));
    nick.setFont(Font.font ("Verdana",FontPosture.ITALIC, 20));
    ip.setFont(Font.font ("Verdana",16));
    
    response.setTextFill(Color.web("#800000"));
    nick.setTextFill(Color.web("#800000"));
    ip.setTextFill(Color.web("#800000"));
    
    
    txf.setLayoutX(165);
    txf.setLayoutY(180);
    
    rootNode.getChildren().addAll(submitBtn,bookTicket,checkStatus,confirmation,adminMode,txf,response,nick,ip) ;
    rootNode.setBackground(imgv);
    
    submitBtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            
            RadioButton rb = (RadioButton)tg.getSelectedToggle() ;
            
            String IP = txf.getText() ;
         //   String IP = new String("127.0.0.1") ;
            if(rb.equals(bookTicket)) try {
                SecondStage.display(IP) ;
            } catch (IOException ex) {
                Logger.getLogger(TicketBooking.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(TicketBooking.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TicketBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
            if( rb.equals(confirmation) ) try {
                ConfirmTicket.display(IP) ;
            } catch (IOException ex) {
                Logger.getLogger(TicketBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(rb.equals(checkStatus)) try {
                CheckStatus.display(IP) ;
            } catch (IOException ex) {
                Logger.getLogger(TicketBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(rb.equals(adminMode)) Admin.display() ;
            myStage.close() ;
        }
    });
    myStage.setScene(scene) ;
   

    myStage.show() ;
    
}
}
