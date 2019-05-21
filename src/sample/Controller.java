package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Button btn5;
    public Button btn6;
    public Button btn7;
    public Button btn8;

    private Button[] elements;
    /**
     * will be used to shuffle elements at first
     */
    private List<Point> points;
    private int[][] board;
    public Button resetButton;

    class Point{
        int x,y;
        Point(int x,int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{x:"+this.x+",y:"+this.y+"}";
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        elements = new Button[]{btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8};
        board = new int[3][3];
        points = new ArrayList<>();
        //initializing points array
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(!(i==1&&j==1)){
                    points.add(new Point(i,j));
                }
            }
        }
        reset();
    }

    public void reset() {
        Collections.shuffle(points);
        for(int i=0;i<8;i++){
            Point point = points.get(i);
            GridPane.setConstraints(elements[i],point.x,point.y);
            board[point.y][point.x] = Integer.valueOf(elements[i].getText());
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.printf("%d ",board[i][j]);
            }
            System.out.println();
        }
    }
}
