package omokClient;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GamepeopleEnterClass {
	//누가 들어왔는지 올리는 메소드
	void peopleEnter(GraphicsContext gc){
		gc.setFill(Color.BURLYWOOD);
		gc.fillRect(0, 0, 190, 200);
	}  
}
