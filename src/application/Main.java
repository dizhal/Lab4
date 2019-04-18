package application;
	
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private MyGridPane grid;
	
	public void start(Stage primaryStage) {
		grid=new MyGridPane();
		grid.positioningElements();
    	grid.addElements();		
		   	
    	grid.buttonWork();

	
		primaryStage.setScene(grid.getScene());	
        primaryStage.setTitle(grid.getNameTitle());        
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
