import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

public class Main extends Application {
    double[][] P = new double[4][2]; //point
    double t = 0;
    double[] polar = new double[2]; //length, angle
    Scanner sc;
    int writeNo=0;
    AnimationTimer timer;
    @Override public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        pane.setPrefSize(1000, 800);
        stage.setScene(new Scene(pane));
        stage.show();

        Line base = new Line(175, 0); base.setLayoutX(200); base.setLayoutY(600);
        //For pen: start is writing & end is joint
        Line pen = new Line(base.len*2.5);
        Label a1 = new Label(); a1.relocate(50,0);
        Label a2 = new Label(); a2.relocate(50,20);
        //pen.startXProperty().bind(base.endXProperty().add(base.getLayoutX()));
        //pen.startYProperty().bind(base.endYProperty().add(base.getLayoutY()));
        pane.getChildren().addAll(base,pen,a1,a2);
        Svg.main(new String[0]);
        sc = new Scanner(new File("C:\\Edwin\\java\\BlahFX\\converted.txt"));
        input();
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                double x = cubicBezier(0), y = cubicBezier(1);
                pen.setEndX(x);
                pen.setEndY(y);
                pane.getChildren().add(new Circle(x, y, 0.5));
                t += 0.25; // t has already been used in the iteration
                polarize(x - base.getLayoutX(), y - base.getLayoutY());

                if(base.len + pen.len >= polar[0] && base.len+polar[0]>=pen.len && pen.len+polar[0]>=base.len) {
                    double delta = Math.acos((base.len*base.len + polar[0]*polar[0] - pen.len*pen.len) / (2*base.len*polar[0]));
                    base.setA(polar[1] + delta);
                    a1.setText("a1 = " +Long.toString(Math.round((polar[1] + delta)*180/Math.PI)));
                    a2.setText("a2 = " +Long.toString(Math.round((Math.acos((base.len*base.len - polar[0]*polar[0] + pen.len*pen.len) / (2*base.len*pen.len)))*180/Math.PI)));
                    pen.setStartX(base.getEndX() + base.getLayoutX()); pen.setStartY(base.getEndY() + base.getLayoutY());
                }
                else
                    System.out.println("out of bounds");

                if (t > 1){
                    input();
                    t=0;
                }
            }
        };
        timer.start();

    }
    void input(){
        if (sc.hasNextDouble()) {
            for(int x = 0; x < 8; ++x) {
                P[x / 2][x % 2] = sc.nextDouble();
            }
        }
        else
            timer.stop();
    }

    double cubicBezier(int cord){ //cord - coordinate
        return Math.pow(1-t, 3)*P[0][cord] + 3*Math.pow(1-t, 2)*t*P[1][cord] + 3*(1-t)*t*t*P[2][cord] + t*t*t*P[3][cord];
    }
    void polarize(double x, double y){
        polar[0] = Math.sqrt(x*x + y*y);
        polar[1] = Math.atan2(y,x);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
