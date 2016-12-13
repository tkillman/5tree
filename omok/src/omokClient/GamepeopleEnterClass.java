package omokClient;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GamepeopleEnterClass {
	//먼저 들어온 사람
	void peopleEnter1(GraphicsContext gc){
		gc.setFill(Color.rgb(196, 222, 255));
		gc.fillRect(0, 0, 190, 100);
	} 
	
	//나중에 들어온 사람
	void peopleEnter2(GraphicsContext gc){
		gc.setFill(Color.rgb(196, 222, 255));
		gc.fillRect(0, 0, 190, 100);
	} 
	
}
