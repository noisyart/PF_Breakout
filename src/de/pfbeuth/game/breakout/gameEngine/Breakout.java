package de.pfbeuth.game.breakout.gameEngine;
import de.pfbeuth.game.breakout.gamelogic.Level;

/**
 * This file implements the game "Breakout".
 * This program is part of the Computer Science and Media Bachelor-Module "Patterns and Frameworks" of the Beuth University Berlin.
 * This is the main class of the game and creates, initialize and controls the game.
 *
 * @version 1.0
 * @author Thomas Glaesser | Isirafil Gülap | Anna Kuzmann | Jan Jasper Wagner
 *
 **/

import de.pfbeuth.game.breakout.gamelogic.ScoreCounter;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import de.pfbeuth.game.breakout.controller.Controller;
import de.pfbeuth.game.breakout.gamelogic.Life;

 public class Breakout extends Application {
     /* Width and height of scene in pixels */
    static final double WIDTH = 540, HEIGHT = 675;
    private StackPane root;
    private Scene scene;
    private GamePlayTimer gameTimer;
    private Controller controller;
    private SpriteManager spriteManager;
    private Paddle paddle;
    private Ball ball;
    private BrickGrid brickGrid;
    private Image paddleImage, brickImage, brickImageRed, brickImageOrange,
                  brickImageYellow, brickImageGreen, ballImage;
    private GUI guiNodes;
    private GameStates gameOver;
    private Level level;
    private Life life;
    private ScoreCounter scoreCounter;

    @Override
    /** create stage, scene and initialize all objects*/
    public void start(Stage primaryStage) {
        /* Stage and Scene Setup */
        root = new StackPane();
        scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setTitle("BREAKOUT");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
        /* Create class instances */
        brickGrid = new BrickGrid(this);
        level = new Level(this);
        life = new Life();
        gameOver = new GameStates(this);
        scoreCounter = new ScoreCounter(this);
        guiNodes = new GUI(this);
        controller = new Controller(this);
        controller.createSceneEventHandling();
        spriteManager = new SpriteManager();
        /* method calls */
        loadImageAssets();
        createGameObjects();
        addObjectsToSpriteManager();
        addGameObjectsNodesToScene();
        addGuiNodesToScene();
        createStartGamePlayTimer();
        guiNodes.createHighScoreScreen();
    }

    /** main method calls launch() */
    public static void main(String[] args) {
        launch(args);
    }

    private void loadImageAssets(){
        paddleImage = new Image("/assets/graphics/paddle.png", 100, 25, true, false, true);
        brickImageRed = new Image("/assets/graphics/brick_red.png", WIDTH/10-2, 22, true, false, true);
        brickImageOrange = new Image("/assets/graphics/brick_orange.png", WIDTH/10-2, 22, true, false, true);
        brickImageYellow = new Image("/assets/graphics/brick_yellow.png", WIDTH/10-2, 22, true, false, true);
        brickImageGreen = new Image("/assets/graphics/brick_green.png", WIDTH/10-2, 22, true, false, true);
        ballImage = new Image("/assets/graphics/ball.png", 200/12, 200/12, true, false, true);
    }
    private void createGameObjects(){
        paddle = new Paddle(this, "M5,0H394C399,0,400,2,400,6V46c0,4-2,5-4,5H7c-7,0-7-4-7-7V6C0,2,1,0,4,0Z", 0, 0, paddleImage);
        paddle.resetState();
        ball = new Ball(this, "M67,0c99,2,94,140,2,141C-22,142-23,1,67,0Z", 0, 0, ballImage);
        ball.resetState();
        ball.resetVelocity();
    }
    private void addObjectsToSpriteManager(){
        spriteManager.addCurrentObjects(paddle);
        spriteManager.addCurrentObjects(ball);
    }
    private void addGameObjectsNodesToScene(){
        brickGrid.createLevelOneGrid();
        root.getChildren().add(paddle.spriteImage);
        root.getChildren().add(ball.spriteImage);
    }
    private void addGuiNodesToScene(){
        root.getChildren().add(guiNodes.getInfoContainer());
        root.getChildren().add(guiNodes.getPlayBackground());
        root.getChildren().add(guiNodes.getBackgroundLayer());
        root.getChildren().add(guiNodes.getHighscoreContainer());
        root.getChildren().add(guiNodes.getHelpContainer());
        root.getChildren().add(guiNodes.getMenueOverlay());
        root.getChildren().add(guiNodes.getGameOverInfo());
        root.getChildren().add(guiNodes.getMasterButtonContainer());
    }
    private void createStartGamePlayTimer(){
        gameTimer = new GamePlayTimer(this);
    }

    /** ------ SETTER ------ */
    void setBrickImage(Image brickImage){
        this.brickImage = brickImage;
    }
    /** ------ GETTER ------ */
    BrickGrid getBrickGrid(){
		 return brickGrid;
	 }
    StackPane getRoot() {
        return root;
    }
    Image getBrickImage() {
        return brickImage;
    }
    Image getBrickImageGreen(){
        return brickImageGreen;
    }
    Image getBrickImageRed() {
        return brickImageRed;
    }
    Image getBrickImageOrange() {
        return brickImageOrange;
    }
    Image getBrickImageYellow() {
        return brickImageYellow;
    }
    Controller getController(){
        return controller;
    }
    GamePlayTimer getGameTimer(){
        return gameTimer;
    }
    Level getLevel(){
       return level;
    }
    SpriteManager getSpriteManager() {
       return spriteManager;
    }
    Paddle getPaddle() {
        return paddle;
    }
    public Ball getBall() {
        return ball;
    }
    public GUI getGuiNodes() {
        return guiNodes;
    }
    public Life getLife(){
        return life;
    }
    public Scene getScene() {
        return scene;
    }
    public GameStates getGameStates(){
        return gameOver;
    }
    public ScoreCounter getScoreCounter(){
        return scoreCounter;
    }
 }