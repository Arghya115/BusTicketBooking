package ticketbooking;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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

public class SecondStage {
    
    static String day , month , year , source , destination ;
  //  ChoiceBox gday ;
    static boolean isDateValid()
    {
        return true ;
    }
    
    public static void display(String IP) throws IOException, CloneNotSupportedException, ClassNotFoundException
    {
        System.out.println(" In the second STAGE............................ ");
        
        Stage stage = new Stage() ;
        
        AnchorPane rootNode = new AnchorPane() ;
        
        Scene scene = new Scene( rootNode , 500 , 500 ) ;
        
        Button submitBtn = new Button("Submit") ;
    //    Button prevBtn = new Button("Previous") ;
        
        submitBtn.setLayoutX(270);
        submitBtn.setLayoutY(400);
        submitBtn.setStyle("-fx-background-color: #FFFF33;");
        submitBtn.setFont(Font.font(null, FontWeight.EXTRA_BOLD,16));
        
    //    prevBtn.setLayoutX(170);
    //    prevBtn.setLayoutY(400) ;
    //    prevBtn.setStyle("-fx-background-color: #3933FF;");
    //    prevBtn.setFont(Font.font(null, FontWeight.EXTRA_BOLD,16));
        
        Image Smage = new Image(SecondStage.class.getResource("bus.jpg").toExternalForm());
        
        rootNode.getChildren().addAll(submitBtn) ;
       
        stage.setTitle("Choose Date , Time and Bus Type") ;
        
        RadioButton firstBus = new RadioButton("8 am") ;        
        RadioButton secondBus = new RadioButton("2 pm") ;
        RadioButton thirdBus = new RadioButton("12.30 am") ;
        BackgroundImage image = new BackgroundImage(Smage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background imgv = new Background(image);
        firstBus.setLayoutX(50);
        firstBus.setLayoutY(300);
        secondBus.setLayoutX(50);
        secondBus.setLayoutY(330);
        thirdBus.setLayoutX(50) ;
        thirdBus.setLayoutY(360) ;
        
        firstBus.setFont(Font.font ("Verdana", 18));
        secondBus.setFont(Font.font ("Verdana", 18));
        thirdBus.setFont(Font.font ("Verdana", 18));
        
        firstBus.setTextFill(Color.web("#6A4949"));
       secondBus.setTextFill(Color.web("#6A4949"));
       thirdBus.setTextFill(Color.web("#6A4949"));
        
        
        
        
        ToggleGroup tg1 = new ToggleGroup() ;
        firstBus.setToggleGroup(tg1) ;
        secondBus.setToggleGroup(tg1) ;
        thirdBus.setToggleGroup(tg1) ;
        
        RadioButton ac = new RadioButton("AC") ;
        RadioButton nonAc = new RadioButton("NON-AC") ;
        ac.setLayoutX(300) ;
        nonAc.setLayoutX(300);
        ac.setLayoutY(310);
        nonAc.setLayoutY(340);
        ToggleGroup tg2 = new ToggleGroup() ;
        ac.setToggleGroup(tg2);
        nonAc.setToggleGroup(tg2);
        
         //ac.setFill(Color.BLUE);
       // nonAc.setFill(Color.RED);
      
        rootNode.getChildren().addAll(firstBus,secondBus,thirdBus,ac,nonAc) ;        
        rootNode.setBackground(imgv);
        ChoiceBox gday,  gmonth , gyear ;
        
        ChoiceBox from = new ChoiceBox() ;
        ChoiceBox to = new ChoiceBox() ;
        
        gday = new ChoiceBox() ;
        gmonth = new ChoiceBox() ;
        gyear = new ChoiceBox() ;
        
        Label leavingFrom = new Label("Leaving from") ;
        Label goingTo = new Label("Going to") ;
        Label chooseDate = new Label("Choose the date") ;
        Label time = new Label("Time") ;
        Label coachType = new Label("Coach Type") ;
        
        leavingFrom.setFont(Font.font ("Verdana", 18));
        goingTo.setFont(Font.font ("Verdana", 18));
        chooseDate.setFont(Font.font ("Verdana", 18));
        time.setFont(Font.font ("Verdana", 18));
        coachType.setFont(Font.font ("Verdana", 18));
        
        ac.setFont(Font.font ("Veranda",18));
        nonAc.setFont(Font.font ("Verdana", 18));
       
        
        
        
       
        
        leavingFrom.setLayoutX(50);
        leavingFrom.setLayoutY(10);
        
        goingTo.setLayoutX(250);
        goingTo.setLayoutY(10);
        chooseDate.setLayoutX(50);
        chooseDate.setLayoutY(110);
        time.setLayoutX(50) ;
        time.setLayoutY(250);
        coachType.setLayoutX(300);
        coachType.setLayoutY(250);
       leavingFrom.setTextFill(Color.web("#FF3333"));
       goingTo.setTextFill(Color.web("#D50CD2"));
       chooseDate.setTextFill(Color.web("#2A8C02"));
       coachType.setTextFill(Color.web("#11028C"));
       ac.setTextFill(Color.web("#8C027D"));
       nonAc.setTextFill(Color.web("#028C11"));
        
      
       
        
        
        rootNode.getChildren().addAll(leavingFrom,goingTo,chooseDate,time,coachType) ;
        
        submitBtn.setOnAction( e ->{
            try {
                
                day = (String)gday.getValue() ;
                month = (String)gmonth.getValue() ;
                year  = (String)gyear.getValue() ;
                
                source = (String)from.getValue() ;
                destination = (String)to.getValue() ;
                
                RadioButton rb1 = (RadioButton)tg1.getSelectedToggle() ;
                RadioButton rb2 = (RadioButton)tg2.getSelectedToggle() ;
                
                if( day==null || month==null || year==null || rb1==null || rb2==null || source==null || destination==null || source.equals(destination) ) System.out.println("Invalid");
                else{
                    
                    String mainString = new String("") ;
                    
                    if(source.equals("Sylhet")) mainString += "1" ;
                    else if(source.equals("Dhaka")) mainString += "2" ;
                    else if(source.equals("Chittagong")) mainString += "3" ;
                    
                    if(destination.equals("Sylhet")) mainString += "1" ;
                    else if(destination.equals("Dhaka")) mainString += "2" ;
                    else if(destination.equals("Chittagong")) mainString += "3" ;
                    
                    if(day.length() ==1) day = "0" + day ;
                    mainString += day ;
                    
                    if(month.equals("January")) mainString += "01" ;  
                    if(month.equals("February")) mainString += "02" ;
                    if(month.equals("March")) mainString += "03" ;
                    if(month.equals("April")) mainString += "04" ;
                    if(month.equals("May")) mainString += "05" ;
                    if(month.equals("June")) mainString += "06" ;
                    if(month.equals("July")) mainString += "07" ;
                    if(month.equals("August")) mainString += "08" ;
                    if(month.equals("September")) mainString += "09" ;
                    if(month.equals("October")) mainString += "10" ;
                    if(month.equals("November")) mainString += "11" ;
                    if(month.equals("December")) mainString += "12" ;
                    
                    mainString += year ;
                    
                    if( rb1.equals(firstBus) ) mainString += "1" ;
                    else if( rb1.equals(secondBus) ) mainString += "2" ;
                    else if( rb1.equals(thirdBus) ) mainString += "3" ;
                    
                    if( rb2.equals(ac) ) mainString += "1" ;
                    else if( rb2.equals(nonAc) ) mainString += "2" ;
                    
                    System.out.println("this is the choiced string "+ mainString) ;
                    
                    ThirdStage.display(mainString,IP) ;
                    stage.close() ;
                } 
                    //   ThirdStage.display("111612201611",IP) ;
                    //   stage.close() ;
              } catch (IOException ex) {
                Logger.getLogger(SecondStage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(SecondStage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SecondStage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  ) ;
          
        
        from.setLayoutX(50);
        to.setLayoutX(250);
        from.setLayoutY(50);
        to.setLayoutY(50);
        
        from.getItems().addAll("Sylhet","Dhaka","Chittagong") ;        
        to.getItems().addAll("Sylhet","Dhaka","Chittagong") ;
        
       
        //from.setFont(Font.font ("Verdana", 20));
       // to.setFont(Font.font ("Verdana", 20));
        coachType.setFont(Font.font ("Verdana", 20));
        
        rootNode.getChildren().addAll(from,to) ;
                
        for(int i=1 ; i<=30 ; i++) gday.getItems().add( Integer.toString(i) ) ;
        gmonth.getItems().addAll( "January" , "February" , "March" , "April" , "May" , "June" , "July" , "August" , "September" , "October", "November" , "December" ) ;
        gyear.getItems().addAll("2016","2017","2018","2019","2020","2021","2022","2023") ;
        
        gday.setLayoutX(50);
        gday.setLayoutY(150);
        gmonth.setLayoutX(120);
        gmonth.setLayoutY(150);
        gyear.setLayoutX(250);
        gyear.setLayoutY(150);
        
        
        rootNode.getChildren().addAll(gday,gmonth,gyear) ;
        
        
        stage.setScene(scene) ; 
        stage.show() ;  
        
        return ;
}

    
    
}
