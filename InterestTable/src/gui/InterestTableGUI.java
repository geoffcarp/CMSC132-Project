package gui;

import controller.InterestTableController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InterestTableGUI extends Application 
{
	private TextArea displayArea;
	private Button buttonBoth, buttonSimp, buttonComp;
	private TextField principal, rate;
	private Slider horizontalSlider;
	private int years = 0;

	@Override
	public void start(Stage primaryStage) 
	{
		int sceneWidth = 1000, sceneHeight = 500;
		int spaceBetweenNodes = 8;
		int paneBorderTop = 20, paneBorderRight = 20;
		int paneBorderBottom = 20, paneBorderLeft = 20;
		
		displayArea = new TextArea();
		displayArea.setEditable(false);
		displayArea.setWrapText(true);
		displayArea.setPrefHeight(sceneHeight - 75);
		displayArea.setPrefWidth(sceneWidth + 500);
	
		/* Adding scroll pane to text area */
		ScrollPane scrollPane = new ScrollPane(displayArea);

		/* Adding elements */
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(scrollPane);
		
		buttonSimp = new Button("Simple Interest");
		buttonComp = new Button("Compound Interest");
		buttonBoth = new Button("Both Interests");
		
		GridPane pane = new GridPane();
		pane.setHgap(spaceBetweenNodes);
		pane.setVgap(spaceBetweenNodes);
	    pane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
			    paneBorderBottom, paneBorderLeft));

	    buttonSimp.setPrefSize(150, 20);
	    buttonComp.setPrefSize(175, 20);
	    buttonBoth.setPrefSize(150, 20);
	  
	    pane.add(buttonSimp, 0, 2);
	    pane.add(buttonComp, 1, 2);
	    pane.add(buttonBoth, 2, 2);
	    
	    borderPane.setBottom(pane);
	    
	    Label princLabel = new Label("Principal: ");
		principal = new TextField();
		pane.add(princLabel,0,0);
		pane.add(principal,1,0);

		Label rateLabel = new Label("Rate: ");
		rate = new TextField();
		pane.add(rateLabel,2,0);
		pane.add(rate,3,0);
		
		Label yearLabel = new Label("Number of Years: ");
		pane.add(yearLabel,0,1);
		
		horizontalSlider = new Slider();
		horizontalSlider.setPrefWidth(250);
		horizontalSlider.setMin(1);
		horizontalSlider.setMax(25);
		horizontalSlider.setValue(1);
		horizontalSlider.setMajorTickUnit(1);
		horizontalSlider.setShowTickMarks(true);
		horizontalSlider.setShowTickLabels(true);
		pane.add(horizontalSlider, 1, 1);

		/* Using lambda expression */
		buttonSimp.setOnAction(e -> {
			double princValue = Double.parseDouble(principal.getText());
			double rateValue = Double.parseDouble(rate.getText());
			years = (int) horizontalSlider.getValue();
			InterestTableController fromInput = new InterestTableController(years, princValue, rateValue, 1);
			displayArea.setText(fromInput.getTable());
		});
		
		HandlingEventAnon handler = new HandlingEventAnon();
		handler.start(primaryStage);
		
		HandlingEvent handlerNon = new HandlingEvent();
		handlerNon.start(primaryStage);

		
		/* Display the stage */
		Scene scene = new Scene(borderPane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Interest Table Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public class HandlingEventAnon extends Application 
	{	
		@Override
		public void start(Stage primaryStage) 
		{	
			/* Using anonymous inner class */
			buttonComp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					double princValue = Double.parseDouble(principal.getText());
					double rateValue = Double.parseDouble(rate.getText());
					years = (int) horizontalSlider.getValue();
					InterestTableController fromInput = new InterestTableController(years, princValue, rateValue, 2);
					displayArea.setText(fromInput.getTable());
				}
			});
					
				
		}
	}

	public class HandlingEvent extends Application 
	{
		@Override
		public void start(Stage primaryStage) 
		{
			buttonBoth.setOnAction(new ButtonHandler());
		}

		private class ButtonHandler implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent e) {
				double princValue = Double.parseDouble(principal.getText());
				double rateValue = Double.parseDouble(rate.getText());
				years = (int) horizontalSlider.getValue();
				InterestTableController fromInput = new InterestTableController(years, princValue, rateValue, 3);
				displayArea.setText(fromInput.getTable());
			}
		}
	}


	public static void main(String[] args) 
	{
		Application.launch(args);
	}
}

