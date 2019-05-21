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
    /**
     * The Board We Manage Game on
     */
    private int[][] board;

    /**
     * empty cell address which is 1,1 at first
     */
    private Point emptyCell = new Point(1,1);



    private final int[][] winningBoard = new int[][]{
            {1,2,3},
            {4,0,5},
            {6,7,8}
    };


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

        boolean isNeighbor(Point point){
            return Math.abs(point.x - this.x) + Math.abs(point.y - this.y) <= 1;
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
        board = new int[3][3];
        for(int i=0;i<8;i++){
            Point point = points.get(i);
            GridPane.setConstraints(elements[i],point.x,point.y);
            board[point.y][point.x] = Integer.valueOf(elements[i].getText());
        }
    }

    public void move(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        Point myAddress = new Point(GridPane.getColumnIndex(source),GridPane.getRowIndex(source));
        if(myAddress.isNeighbor(emptyCell)){
            int sourceNumber = Integer.valueOf(source.getText());
            if(myAddress.y - emptyCell.y == 1){
                //move up
                GridPane.setConstraints(source,myAddress.x,myAddress.y-1);
                board[myAddress.y-1][myAddress.x] = sourceNumber;
            }else if(myAddress.y - emptyCell.y == -1){
                //move down
                GridPane.setConstraints(source,myAddress.x,myAddress.y+1);
                board[myAddress.y+1][myAddress.x] = sourceNumber;
            }else if(myAddress.x - emptyCell.x == 1){
                //move left
                GridPane.setConstraints(source,myAddress.x-1,myAddress.y);
                board[myAddress.y][myAddress.x-1] = sourceNumber;
            }else if(myAddress.x - emptyCell.x == -1){
                //move right
                GridPane.setConstraints(source,myAddress.x+1,myAddress.y);
                board[myAddress.y][myAddress.x+1] = sourceNumber;
            }
            //moving empty cell
            board[myAddress.y][myAddress.x] = 0;
            emptyCell.x = myAddress.x;
            emptyCell.y = myAddress.y;
            if(isCompleted()){
                Main.endWithCongrats();
            }
        }
    }

    private boolean isCompleted(){

        if(Arrays.deepEquals(board,winningBoard)){
            return true;
        }
        return false;
    }

}
