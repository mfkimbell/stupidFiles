package com.example.drone1;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application
{
    // Create the Timelines
    Timeline rotate = new Timeline();
    Timeline moveDiagonal = new Timeline();
    Timeline moveUp = new Timeline();
    Timeline rotateNext = new Timeline();
    Timeline rotateLast = new Timeline();
    Timeline moveLeft = new Timeline();
    SequentialTransition sequence = new SequentialTransition();

    // Create the Label
    Label status = new Label("Current State: " + sequence.getStatus());

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws InterruptedException
    {
        // Create the drone
        ImageView drone = new ImageView(new Image("drone.png"));

        // Create the Buttons
        Button play = new Button("Play");
        Button pause = new Button("Pause");
        Button replay = new Button("RePlay");
        Button stop = new Button("Stop");

        // Create the Event-Handlers for the Buttons
        play.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                sequence.play();
                status.setText("Current State: " + sequence.getStatus());
            }
        });

        replay.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                sequence.playFromStart();
                status.setText("Current State: " + sequence.getStatus());
            }
        });

        pause.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                sequence.pause();
                status.setText("Current State: " + sequence.getStatus());
            }
        });

        stop.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                sequence.stop();
                status.setText("Current State: " + sequence.getStatus());
            }
        });

        // Create the Button Box
        HBox buttonBox = new HBox();
        // Set Spacing to 10 pixels
        buttonBox.setSpacing(10);
        // Add the Children to the HBox
        buttonBox.getChildren().addAll(play,pause,replay,stop);

        // Create the VBox
        VBox root = new VBox(buttonBox,status,drone);
        // Set Spacing to 10 pixels
        root.setSpacing(10);
        // Set the Size of the VBox
        root.setPrefSize(800, 600);
        // Set the Style-properties of the VBox
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: transparent;");

        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("A JavaFX Animation Example With Button Controls");
        // Display the Stage
        stage.show();

        // Get the Scene width and height along with image width
        double sceneWidth = scene.getWidth();
        double sceneHeight = scene.getHeight();
        double droneWidth = drone.getLayoutBounds().getWidth();

        // Define the Durations
        Duration startDuration = Duration.ZERO;
        Duration endDuration = Duration.seconds(5);
        Duration endDuration2 = Duration.seconds(2);

        // Create Key Frames
        KeyValue startKeyValue = new KeyValue(drone.translateXProperty(), 0);
        KeyFrame startKeyFrameDiagonal = new KeyFrame(startDuration, startKeyValue);
        KeyValue endKeyValueX = new KeyValue(drone.translateXProperty(), sceneWidth - droneWidth*1.5);
        KeyValue endKeyValueY = new KeyValue(drone.translateYProperty(), sceneHeight - droneWidth*2.375);
        KeyFrame endKeyFrameDiagonal = new KeyFrame(endDuration, endKeyValueX, endKeyValueY);


        KeyValue endKeyValueRotate = new KeyValue(drone.rotateProperty(), drone.getRotate() - 90);
        KeyFrame endKeyFrameRotate = new KeyFrame(endDuration2, endKeyValueRotate);

        KeyValue endKeyValueMoveUp = new KeyValue(drone.translateYProperty(), 0);
        KeyFrame endKeyFrameMoveUp = new KeyFrame(endDuration, endKeyValueMoveUp);

        KeyValue endKeyValueRotateNext = new KeyValue(drone.rotateProperty(),  drone.getRotate() - 180);
        KeyFrame endKeyFrameRotateNext = new KeyFrame(endDuration2, endKeyValueRotateNext);

        KeyValue endKeyValueMoveLeft = new KeyValue(drone.translateXProperty(), 0);
        KeyFrame endKeyFrameMoveLeft = new KeyFrame(endDuration, endKeyValueMoveLeft);

        KeyValue endKeyValueRotateLast = new KeyValue(drone.rotateProperty(),  drone.getRotate() - 360);
        KeyFrame endKeyFrameRotateLast = new KeyFrame(endDuration2, endKeyValueRotateLast);

        // Create Timelines
        rotate = new Timeline(endKeyFrameRotate);
        rotateNext = new Timeline(endKeyFrameRotateNext);
        moveDiagonal = new Timeline(startKeyFrameDiagonal, endKeyFrameDiagonal);
        moveUp = new Timeline(endKeyFrameMoveUp);
        moveLeft = new Timeline(endKeyFrameMoveLeft);
        rotateLast = new Timeline(endKeyFrameRotateLast);

        // Create Sequence
        sequence = new SequentialTransition(moveDiagonal, rotate, moveUp, rotateNext, moveLeft, rotateLast);
        // Let the animation run forever
        sequence.setCycleCount(Timeline.INDEFINITE);
    }
}
