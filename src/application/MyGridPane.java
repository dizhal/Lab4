package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MyGridPane {
	private Scene scene;
	private GridPane root;
	private Label equationRequest;
	private Label rootIs;
	private Label unknown;
	private TextField equationTf;
	private Button btn;	

	public static final String NAME_TITLE = "Lab4";	
	public static final String EQUATION_REQUEST = "Введите линейное уравнение";	
	public static final String FIND_THE_ROOT="Найти корень";
	public static final String rootEquals="Корень равен";
	
	public MyGridPane() {
    	root = new GridPane();
    	scene = new Scene(root, 400, 150);
    	equationRequest=new Label(EQUATION_REQUEST);
    	rootIs=new Label(rootEquals);
    	unknown=new Label();
    	equationTf=new TextField();
    	btn=new Button(FIND_THE_ROOT);
	}
	
	public void positioningElements() {
		GridPane.setMargin(equationRequest, new Insets(10));
		GridPane.setMargin(rootIs, new Insets(10));
		root.getColumnConstraints().add(new ColumnConstraints(200));
		root.getColumnConstraints().add(new ColumnConstraints(170));
		root.getRowConstraints().add(new RowConstraints(35));
    	root.getRowConstraints().add(new RowConstraints(35));
    	root.getRowConstraints().add(new RowConstraints(35));
    	root.getRowConstraints().add(new RowConstraints(35));
	}
	
	public void addElements() {
		root.add(equationRequest,0, 0);
		root.add(equationTf,1, 0);
		root.add(btn,1, 1);
	}
	
	public void buttonWork() {
		btn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					String temp=equationTf.getText();
					MyThread myThread=new MyThread();
	    		    MainMyThread mainMyThread=new MainMyThread(myThread,temp);			
	    		    for (int i = -99; i <= 99; i++) {
	    				myThread.setEquation(mainMyThread.getEquation());
	    				myThread.setNumber(i);
	    				new Thread(mainMyThread).start();
	    				try {
		    		    	 Thread.sleep(50);
		    	        }
		    	        catch (InterruptedException e) {
		    	        	 System.out.println("Thread has been interrupted");
		    	        }
	    				if(myThread.getFlagResult()==true) {
	    					//System.out.println(mainMyThread.getEquation());
	    					break;
	    				}
	    	        }	
	    		    try {
	    		    	 Thread.sleep(50);
	    	        }
	    	        catch (InterruptedException e) {
	    	        	 System.out.println("Thread has been interrupted");
	    	        }
	    		    unknown.setText(Integer.toString(mainMyThread.getMyThread().getNumber()));
	    		    if(mainMyThread.getMyThread().getFlagResult()==true) {
	    		    	root.add(rootIs,0, 2);
	    				root.add(unknown,1, 2);
	    		    }
			}
    	});
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public String getNameTitle() {
		return NAME_TITLE;
	}
}
