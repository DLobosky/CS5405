/**@author Dalton Lobosky
	@version 1.0
*/

package code;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.shape.Shape;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;

/** The start of the class FanDemo */
public class FanDemo extends Application 
{
	GridPane drawingBoard;
	Pane p1, p2, p3, p4;
	BorderPane mainPane;
	Scene myScene;
	StackPane authorPane;
	FlowPane descriptionPane;
	
	public String str;
	
    @Override
	
	/** The start method for the application*/
    public void start(Stage myStage) 
	{
		//Setup the scene
        myStage.setTitle("Fan Maker 2000!!!!");
		
		drawingBoard = new GridPane(); // Creates the drawing board for the triangles
		drawingBoard.setStyle("-fx-background-color: LIGHTGRAY;");
		
		Pane p1 = new Pane();
		p1.setStyle("-fx-background-color: LIGHTGRAY;");
		
		Pane p2 = new Pane();
		p2.setStyle("-fx-background-color: LIGHTGRAY;");
		
		Pane p3 = new Pane();
		p3.setStyle("-fx-background-color: LIGHTGRAY;");
		
		Pane p4 = new Pane();
		p4.setStyle("-fx-background-color: LIGHTGRAY;");

		drawingBoard.add(p1, 0, 0);
		drawingBoard.add(p2, 1, 0);
		drawingBoard.add(p3, 0, 1);
		drawingBoard.add(p4, 1, 1);
		
		mainPane = new BorderPane(); // Maine Pane for the whole program
		mainPane.setCenter(drawingBoard);
		
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); // Makes sure the close option is not available on the tabs
		
		StackPane authorPane = new StackPane(); // Pane for the author tab
		authorPane.setStyle("-fx-background-color: CYAN;");
		
		FlowPane descriptionPane = new FlowPane(); // Pane for the description tab
		descriptionPane.setStyle("-fx-background-color: CYAN;");
		
		myScene = new Scene(tabPane, 500, 500);
		
		Tab authorTab = new Tab(); // Creates the "author" tab
		authorTab.setText("Author");
		
		Tab descriptionTab = new Tab(); // Creates the "description" tab
		descriptionTab.setText("Description");
		
		Tab programTab = new Tab(); // Creates the "program" tab
		programTab.setText("Program");
		
		authorTab.setContent(authorPane);
		descriptionTab.setContent(descriptionPane);
		programTab.setContent(mainPane);
		
		tabPane.getTabs().add(authorTab);
		tabPane.getTabs().add(descriptionTab);
		tabPane.getTabs().add(programTab);
		
		Rectangle authorRec = new Rectangle(300,125,Color.LIGHTGRAY);
		//rectangle.relocate(70,70);
		authorPane.getChildren().add(authorRec);
		
		Text authorText = new Text("Author: Dalton Lobosky\nEmail: dgl9t5@mst.edu");
		authorText.setStyle("-fx-font: 20 comicsans;");
		authorText.setFill(Color.DARKBLUE);
		authorText.setId("fancytext");
		authorPane.getChildren().add(authorText);
		
		Text descriptionText = new Text("  This program draws 4 fans on a GridPane using Cirles and Arcs.\n"
										+ "  The dimensions for the fans have been preset in the source code.\n");
		descriptionText.setStyle("-fx-font: 14 arial;");
		descriptionText.relocate(300 ,300);
		descriptionPane.getChildren().add(descriptionText);
		
		//--------------------------------------------------
		
		int w = 200;
		int h = 200;
		int spokeNumber = 10;
		double ratioVal = (2.5 * spokeNumber);
		double radius = Math.min(w,h) * 0.45;
		Arc arc1[] = new Arc[spokeNumber];
		Arc arc2[] = new Arc[spokeNumber];
		Arc arc3[] = new Arc[spokeNumber];
		Arc arc4[] = new Arc[spokeNumber];
		double startAngle = 30;
		Circle c1 = new Circle(w/2, h/2, radius);
		Circle c2 = new Circle(w/2, h/2, radius);
		Circle c3 = new Circle(w/2, h/2, radius);
		Circle c4 = new Circle(w/2, h/2, radius);
		
		c1.setStroke(Color.BLACK);
		c1.setFill(Color.CYAN);
		c2.setStroke(Color.BLACK);
		c2.setFill(Color.CYAN);
		c3.setStroke(Color.BLACK);
		c3.setFill(Color.CYAN);
		c4.setStroke(Color.BLACK);
		c4.setFill(Color.CYAN);
		
		p1.getChildren().add(c1);
		p2.getChildren().add(c2);
		p3.getChildren().add(c3);
		p4.getChildren().add(c4);
				
		
		for(int i = 0; i < spokeNumber; i++)
		{
			arc1[i] = new Arc(w/2, h/2, radius * 0.9, radius * 0.9, startAngle + i*(360/spokeNumber), 15);
			arc1[i].setFill(Color.GREEN);
			arc1[i].setType(ArcType.ROUND);
			p1.getChildren().addAll(arc1[i]);			
		}
		
		for(int i = 0; i < spokeNumber; i++)
		{
			arc2[i] = new Arc(w/2, h/2, radius * 0.9, radius * 0.9, startAngle + i*(360/spokeNumber), 15);
			arc2[i].setFill(Color.GREEN);
			arc2[i].setType(ArcType.ROUND);
			p2.getChildren().addAll(arc2[i]);			
		}
		
		for(int i = 0; i < spokeNumber; i++)
		{
			arc3[i] = new Arc(w/2, h/2, radius * 0.9, radius * 0.9, startAngle + i*(360/spokeNumber), 15);
			arc3[i].setFill(Color.GREEN);
			arc3[i].setType(ArcType.ROUND);
			p3.getChildren().addAll(arc3[i]);			
		}
		
		for(int i = 0; i < spokeNumber; i++)
		{
			arc4[i] = new Arc(w/2, h/2, radius * 0.9, radius * 0.9, startAngle + i*(360/spokeNumber), 15);
			arc4[i].setFill(Color.GREEN);
			arc4[i].setType(ArcType.ROUND);
			p4.getChildren().addAll(arc4[i]);			
		}
		
		myStage.setScene(myScene);

        myStage.show();
	}
	
	/** Main method */
    public static void main(String[] args) 
	{
        launch(args);
    }
}