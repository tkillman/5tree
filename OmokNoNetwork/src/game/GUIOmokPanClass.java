package game;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GUIOmokPanClass {
	//�¹����� �κ� ��ǥ ����
	ArrayList<GUILineCrossPoint> lineCrossArray = new ArrayList<GUILineCrossPoint>();

	//���� ��, �� ��
	boolean blackBool = true;
	boolean whiteBool = false;

	//�ٵ��� ���μ�, ���μ� ������
	final short horLine = 20;
	final short verLine = 560;
	final short lineBetweenCount = 30;
	final short PanLineCount = 19;

	//�ٵ��� ���� �¹����� ���� ���ϱ�
	void lineCrossPoint(){      

		//30�� �������� �ٵ��� ��ǥ�� �����Ѵ�.
		short plusVerLine = 0;
		short plusHorLine = 0;
		for(int a=0; a<PanLineCount; a++){      
			plusHorLine = 0;
			for(int b=0; b<PanLineCount; b++){                        
				lineCrossArray.add(new GUILineCrossPoint(horLine+plusHorLine, horLine+plusVerLine));
				plusHorLine += lineBetweenCount;
			}         
			plusVerLine += lineBetweenCount;
		}

		//����Ʈ�� �� ��� ������
		/*for(LineCrossPoint line:lineCrossArray){
	         System.out.println(line.x+", "+line.y);
	    }*/
	}
	
	//������ �׸��� �޼ҵ�
	void drawPan(GraphicsContext gc) {   
		gc.setFill(Color.CHOCOLATE);
		gc.fillRect(0, 0, 580, 580); //����, ����, ����ũ��, ����ũ��

		//���߱�
		short Count = 0;      
		for(short a = 0; a<PanLineCount; a++){         
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(2);
			gc.strokeLine(horLine+Count, horLine, horLine+Count, verLine); //������ �׾��ֱ�. x1, y1, x2, y2 
			gc.strokeLine(horLine, horLine+Count, verLine, horLine+Count); //������ �׾��ֱ�. x1, y1, x2, y2  

			Count += lineBetweenCount;
		}      

		//������ �� ��ġ�� �κ� ��ǥ ���� �Լ� ȣ�����ֱ�
		lineCrossPoint();
	}

	//Ŭ�������� ���� ���´�.
	public void stone(int lineX, int lineY, GraphicsContext gc){

		//���� �� ����
		if(blackBool == true){
			gc.setFill(Color.BLACK);
			whiteBool = true;
			blackBool = false;

		//�� �� ����
		}else if(whiteBool == true){
			gc.setFill(Color.WHITE);  
			blackBool = true;
			whiteBool = false;
		}
		
		gc.fillOval(lineX-15, lineY-15, 30, 30); //���콺 Ŭ���� �κ��� �߰��� ���� �������� -15���ְ�, �� ũ��� 30����
	}



	//����� �� �� ���� ��
	final int nearPoint(double xy){
		int pointXY = (int)(Math.round((xy-20)/30)*30+20);
		
		System.out.println("xy��ǥ : " + pointXY);
		return pointXY;
	}
	
	//����� �� �� ��ǥ�� �ε��� ������ ��ȯ�ϴ� ��
	final int nearPointArrayValue(double xy){		
		int pointXY = (int)(Math.round((xy-20)/30));
	
		return pointXY;
	}
}
