package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;

import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.control.*;
import model.Colour;
import model.card.Card;
import model.card.standard.Standard;
import model.player.Marble;
import model.player.Player;
import engine.Game;
import engine.board.Board;
import engine.board.Cell;
import engine.board.SafeZone;
import exception.CannotDiscardException;
import exception.GameException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;
import exception.SplitOutOfRangeException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Main extends Application implements EventHandler<ActionEvent> {

	Game game;

	Board board;

	BorderPane theGame;

	StackPane midStack;

	ArrayList<Player> players;

	ArrayList<Card> myHand;

	Group boardGroup;

	ArrayList<Button> trackCells = new ArrayList<>();

	private Player currentPlayer;

	private Label turnLabel;

	Group homesGroup;

	VBox boardMid;

	StackPane safeContainer;

	Circle boardTable;

	private Card lastPlayedCard = null;

	private Card theCard;

	VBox safeT = new VBox(6);

	VBox safeB = new VBox(6);

	HBox safeR = new HBox(6);

	HBox safeL = new HBox(6);

	ArrayList<Button> safeTz;

	ArrayList<Button> safeBz;

	ArrayList<Button> safeLz;

	ArrayList<Button> safeRz;

	HBox Cards1 = new HBox(20);

	HBox Cards2 = new HBox(20);

	VBox Cards3 = new VBox(20);

	VBox Cards4 = new VBox(20);

	VBox turns;

	ImageView card1;

	ImageView card2;

	ImageView card3;

	ImageView card4;

	Image backT = new Image("back.png");

	Image backL = new Image("backL.png");

	Image backR = new Image("backR.png");

	GridPane homezone1;

	GridPane homezone2;

	GridPane homezone3;

	GridPane homezone4;

	StackPane homez1;

	StackPane homez2;

	StackPane homez3;

	StackPane homez4;

	ArrayList<Marble> myMarbles;

	ArrayList<Marble> c1Marbles;

	ArrayList<Marble> c2Marbles;

	ArrayList<Marble> c3Marbles;

	ArrayList<Button> myButtons = new ArrayList<>();

	ArrayList<Button> c1Buttons = new ArrayList<>();

	ArrayList<Button> c2Buttons = new ArrayList<>();

	ArrayList<Button> c3Buttons = new ArrayList<>();

	Button playBtn;

	ArrayList<Colour> colorOrder;

	Image home = new Image("homez.png");

	ImageView home1 = new ImageView(home);

	ImageView home2 = new ImageView(home);

	ImageView home3 = new ImageView(home);

	ImageView home4 = new ImageView(home);

	ImageView fireCard;

	HBox bottomP = new HBox(50);

	HBox topP = new HBox(50);

	VBox leftP = new VBox(50);

	VBox rightP = new VBox(50);

	Label CPU1 = new Label("Dana");

	Label CPU2 = new Label("Tala");

	Label CPU3 = new Label("Roka");

	Label name1 = new Label();

	Label curPlayer;

	Label nextPlayer;

	Scene opening;

	TextField enterName;

	Button enterG;

	StackPane firePitContainer;

	DropShadow glow = new DropShadow();

	ArrayList<Cell> track;

	Color myC;

	Color c1C;

	Color c2C;

	Color c3C;

	@Override
	public void start(Stage primaryStage) {

		homezone1 = new GridPane();

		homezone2 = new GridPane();

		homezone3 = new GridPane();

		homezone4 = new GridPane();

		homez1 = new StackPane();

		homez2 = new StackPane();

		homez3 = new StackPane();

		homez4 = new StackPane();

		homezone1.setAlignment(Pos.CENTER);

		homezone2.setAlignment(Pos.CENTER);

		homezone3.setAlignment(Pos.CENTER);

		homezone4.setAlignment(Pos.CENTER);

		glow = new DropShadow();

		glow.setColor(Color.GOLD);

		glow.setRadius(10);

		glow.setSpread(0.5);

		try {

			primaryStage.setTitle("Jackaroo <3");

			Image icon = new Image("icon.jpg");

			primaryStage.getIcons().add(icon);

			 primaryStage.setResizable(false);

			StackPane welcome = new StackPane();

			welcome.setStyle("-fx-background-color: darkseagreen;");

			opening = new Scene(welcome, 1200, 950);

			Text entrance = new Text("JACKAROO");

			entrance.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 38px ;-fx-font-weight: bold; -fx-fill: DARKORCHID; -fx-stroke: white; -fx-stroke-width: 1px;");

			Text hi = new Text(
					"Welcome to Jackaroo! Please enter your name below to play!");

			hi.setStyle("-fx-font-family:'Comic Sans MS'; -fx-font-color: white; -fx-font-size:24px");

			enterName = new TextField();

			enterName
					.setStyle("-fx-background-color: yellow; -fx-text-fill: darkgreen; -fx-font-size: 22px;");

			enterName.setPrefWidth(50);

			enterName.setPrefHeight(40);

			enterG = new Button("Let's play!");

			enterG.setStyle("-fx-background-color: yellow;-fx-text-fill: darkorange;-fx-font-size: 22px;-fx-font-weight: bold;");

			VBox layout1 = new VBox(20);

			layout1.setAlignment(Pos.CENTER);

			layout1.getChildren().addAll(entrance, hi, enterName, enterG);

			welcome.getChildren().add(layout1);

			primaryStage.setScene(opening);

			primaryStage.show();

			hishBoard();

			setC1Cards();

			setC2Cards();

			setC3Cards();

			setMyCards(myHand);

			Scene playing = new Scene(theGame,1200, 950);

			enterG.setOnAction(this);

			enterG.setOnAction(e -> {

				String enteredName = enterName.getText();

				if (enteredName.isEmpty()) {

					alert("",
							"stop trying to be mysterious please enter a name");

					return;

				}

				else {

					name1.setText(enteredName);
				}

				primaryStage.setScene(playing);
			});
		}

		catch (Exception e) {

			e.printStackTrace();
		}
	}

	private Button createMarbleButton(Color fill, Color stroke) {

		Button btn = new Button();

		btn.setMinSize(12, 12);

		btn.setMaxSize(12, 12);

		btn.setPrefSize(12, 12);

		btn.setStyle(

		"-fx-background-radius: 12; " +

		"-fx-background-color: " + toRgbString(fill) + "; " +

		"-fx-border-color: " + toRgbString(stroke) + "; " +

		"-fx-border-width: 2px; " +

		"-fx-padding: 0;"

		);

		btn.setShape(new Circle(12)); // Matches the new size

		return btn;

	}

	private String toRgbString(Color c) {

		return String.format("rgb(%d,%d,%d)",

		(int) (c.getRed() * 255),

		(int) (c.getGreen() * 255),

		(int) (c.getBlue() * 255));

	}

	private void fillHomeZones(ArrayList<Colour> colorOrder){
		for(int i=0;i<4;i++){
			if(colorOrder.get(i)==Colour.RED){
				Button b1 = createMarbleButton(Color.RED, Color.DARKRED);
				Button b2 = createMarbleButton(Color.RED, Color.DARKRED);
				Button b3 = createMarbleButton(Color.RED, Color.DARKRED);
				Button b4 = createMarbleButton(Color.RED, Color.DARKRED);
				b1.setOnAction(this);
				b2.setOnAction(this);
				b3.setOnAction(this);
				b4.setOnAction(this);
				switch (i){
				case 0:
					homezone3.add(b1, 0, 0); homezone3.add(b2, 1, 0); homezone3.add(b3, 0, 1); homezone3.add(b4, 1, 1);
					myButtons.add(b1); myButtons.add(b2); myButtons.add(b3); myButtons.add(b4);
					name1.setStyle("-fx-text-fill: red; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				case 1:
					homezone1.add(b1, 0, 0); homezone1.add(b2, 1, 0); homezone1.add(b3, 0, 1); homezone1.add(b4, 1, 1);
					c1Buttons.add(b1); c1Buttons.add(b2); c1Buttons.add(b3); c1Buttons.add(b4);
					CPU1.setStyle("-fx-text-fill: red; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				case 2:
					homezone4.add(b1, 0, 0); homezone4.add(b2, 1, 0); homezone4.add(b3, 0, 1); homezone4.add(b4, 1, 1);
					c2Buttons.add(b1); c2Buttons.add(b2); c2Buttons.add(b3); c2Buttons.add(b4);
					CPU2.setStyle("-fx-text-fill: red; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				case 3:
					homezone2.add(b1, 0, 0); homezone2.add(b2, 1, 0); homezone2.add(b3, 0, 1); homezone2.add(b4, 1, 1);
					c3Buttons.add(b1); c3Buttons.add(b2); c3Buttons.add(b3); c3Buttons.add(b4);
					CPU3.setStyle("-fx-text-fill: red; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				}
			}
			if(colorOrder.get(i)==Colour.BLUE){
				Button b1 = createMarbleButton(Color.BLUE, Color.DARKBLUE);
				Button b2 = createMarbleButton(Color.BLUE, Color.DARKBLUE);
				Button b3 = createMarbleButton(Color.BLUE, Color.DARKBLUE);
				Button b4 = createMarbleButton(Color.BLUE, Color.DARKBLUE);
				b1.setOnAction(this);
				b2.setOnAction(this);
				b3.setOnAction(this);
				b4.setOnAction(this);
				switch (i){
				case 0:
					homezone3.add(b1, 0, 0); homezone3.add(b2, 1, 0); homezone3.add(b3, 0, 1); homezone3.add(b4, 1, 1);
					myButtons.add(b1); myButtons.add(b2); myButtons.add(b3); myButtons.add(b4);
					name1.setStyle("-fx-text-fill: blue; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				case 1:
					homezone1.add(b1, 0, 0); homezone1.add(b2, 1, 0); homezone1.add(b3, 0, 1); homezone1.add(b4, 1, 1);
					c1Buttons.add(b1); c1Buttons.add(b2); c1Buttons.add(b3); c1Buttons.add(b4);
					CPU1.setStyle("-fx-text-fill: blue; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				case 2:
					homezone4.add(b1, 0, 0); homezone4.add(b2, 1, 0); homezone4.add(b3, 0, 1); homezone4.add(b4, 1, 1);
					c2Buttons.add(b1); c2Buttons.add(b2); c2Buttons.add(b3); c2Buttons.add(b4);
					CPU2.setStyle("-fx-text-fill: blue; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				case 3:
					homezone2.add(b1, 0, 0); homezone2.add(b2, 1, 0); homezone2.add(b3, 0, 1); homezone2.add(b4, 1, 1);
					c3Buttons.add(b1); c3Buttons.add(b2); c3Buttons.add(b3); c3Buttons.add(b4);
					CPU3.setStyle("-fx-text-fill: blue; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				}
			}
			if(colorOrder.get(i)==Colour.GREEN){
				Button b1 = createMarbleButton(Color.GREEN, Color.DARKSLATEGREY);
				Button b2 = createMarbleButton(Color.GREEN, Color.DARKSLATEGREY);
				Button b3 = createMarbleButton(Color.GREEN, Color.DARKSLATEGREY);
				Button b4 = createMarbleButton(Color.GREEN, Color.DARKSLATEGREY);
				b1.setOnAction(this);
				b2.setOnAction(this);
				b3.setOnAction(this);
				b4.setOnAction(this);
				switch (i){
				case 0:
					homezone3.add(b1, 0, 0); homezone3.add(b2, 1, 0); homezone3.add(b3, 0, 1); homezone3.add(b4, 1, 1);
					myButtons.add(b1); myButtons.add(b2); myButtons.add(b3); myButtons.add(b4);
					name1.setStyle("-fx-text-fill: green; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				case 1:
					homezone1.add(b1, 0, 0); homezone1.add(b2, 1, 0); homezone1.add(b3, 0, 1); homezone1.add(b4, 1, 1);
					c1Buttons.add(b1); c1Buttons.add(b2); c1Buttons.add(b3); c1Buttons.add(b4);
					CPU1.setStyle("-fx-text-fill: green; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				case 2:
					homezone4.add(b1, 0, 0); homezone4.add(b2, 1, 0); homezone4.add(b3, 0, 1); homezone4.add(b4, 1, 1);
					c2Buttons.add(b1); c2Buttons.add(b2); c2Buttons.add(b3); c2Buttons.add(b4);
					CPU2.setStyle("-fx-text-fill: green; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				case 3:
					homezone2.add(b1, 0, 0); homezone2.add(b2, 1, 0); homezone2.add(b3, 0, 1); homezone2.add(b4, 1, 1);
					c3Buttons.add(b1); c3Buttons.add(b2); c3Buttons.add(b3); c3Buttons.add(b4);
					CPU3.setStyle("-fx-text-fill: green; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				}
			}
			if(colorOrder.get(i)==Colour.YELLOW){
				Button b1 = createMarbleButton(Color.YELLOW, Color.GOLDENROD);
				Button b2 = createMarbleButton(Color.YELLOW, Color.GOLDENROD);
				Button b3 = createMarbleButton(Color.YELLOW, Color.GOLDENROD);
				Button b4 = createMarbleButton(Color.YELLOW, Color.GOLDENROD);
				b1.setOnAction(this);
				b2.setOnAction(this);
				b3.setOnAction(this);
				b4.setOnAction(this);
				switch (i){
				case 0:
					homezone3.add(b1, 0, 0); homezone3.add(b2, 1, 0); homezone3.add(b3, 0, 1); homezone3.add(b4, 1, 1);
					myButtons.add(b1); myButtons.add(b2); myButtons.add(b3); myButtons.add(b4);
					name1.setStyle("-fx-text-fill: yellow; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				case 1:
					homezone1.add(b1, 0, 0); homezone1.add(b2, 1, 0); homezone1.add(b3, 0, 1); homezone1.add(b4, 1, 1);
					c1Buttons.add(b1); c1Buttons.add(b2); c1Buttons.add(b3); c1Buttons.add(b4);
					CPU1.setStyle("-fx-text-fill: yellow; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				case 2:
					homezone4.add(b1, 0, 0); homezone4.add(b2, 1, 0); homezone4.add(b3, 0, 1); homezone4.add(b4, 1, 1);
					c2Buttons.add(b1); c2Buttons.add(b2); c2Buttons.add(b3); c2Buttons.add(b4);
					CPU2.setStyle("-fx-text-fill: yellow; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				case 3:
					homezone2.add(b1, 0, 0); homezone2.add(b2, 1, 0); homezone2.add(b3, 0, 1); homezone2.add(b4, 1, 1);
					c3Buttons.add(b1); c3Buttons.add(b2); c3Buttons.add(b3); c3Buttons.add(b4);
					CPU3.setStyle("-fx-text-fill: yellow; -fx-font-size: 22px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);");
					break;
				}
			}
		}
	    ArrayList<Button> allButtons = new ArrayList<>();
	    allButtons.addAll(myButtons);
	    allButtons.addAll(c1Buttons);
	    allButtons.addAll(c2Buttons);
	    allButtons.addAll(c3Buttons);

	    for (Button button : allButtons) {
	        button.setOnAction(this);
	    }
	}


	private void setMyCards(ArrayList<Card> myCards) {

		Cards1.getChildren().clear();

		double cardWidth = 76;

		double cardHeight = 112;

		Cards1.setSpacing(10);

		Cards1.setAlignment(Pos.CENTER);

		for (int i = 0; i < myCards.size(); i++) {

			Card theCard = myCards.get(i);

			ImageView cardImage = new ImageView(getCardImage(theCard));

			cardImage.setFitWidth(cardWidth);

			cardImage.setFitHeight(cardHeight);

			cardImage.setPreserveRatio(true);

			Button cardButton = new Button();

			cardButton.setGraphic(cardImage);

			cardButton.setMinSize(cardWidth, cardHeight);

			cardButton.setMaxSize(cardWidth, cardHeight);

			cardButton
					.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

			cardButton.setUserData(theCard);

			cardButton.setOnMouseEntered(e -> {

				if (!cardButton.getStyle().contains("dropshadow")) {

					cardButton.setEffect(new DropShadow(10, Color.GOLD));

				}

			});

			cardButton.setOnMouseExited(e -> {

				if (!cardButton.getStyle().contains("dropshadow")) {

					cardButton.setEffect(null);

				}

			});

			cardButton
					.setOnAction(e -> {

						for (Node child : Cards1.getChildren()) {

							if (child instanceof Button) {

								child.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

								child.setEffect(null);

								child.setTranslateY(0);

							}

						}

						try {

							game.selectCard(theCard);

							lastPlayedCard = theCard;

							cardButton
									.setStyle("-fx-background-color: transparent; -fx-padding: 0; "
											+

											"-fx-effect: dropshadow(gaussian, rgba(255,215,0,0.8), 15, 0.5, 0, 0);");

							cardButton.setTranslateY(-10);

							showCardInfoPopup(Cards1, cardButton, theCard);

						} catch (Exception ex) {

							alert("Card Error", "Cannot select this card: "
									+ ex.getMessage());

							game.deselectAll();

						}

					});

			Cards1.getChildren().add(cardButton);

		}

		if (myCards.isEmpty()) {

			Label emptyHand = new Label("No cards in hand");

			emptyHand.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

			Cards1.getChildren().add(emptyHand);

		}

	}

	private Label currentPopupLabel;

	private void showCardInfoPopup(Pane overlayPane, Node parent,
			Card selectedCard) {

		if (currentPopupLabel != null) {

			overlayPane.getChildren().remove(currentPopupLabel);

		}

		String description1 = selectedCard.getDescription();

		String description = "";

		int Count = 0;

		String x1 = "";

		String x2 = "";

		boolean done = false;

		for (int i = 0; i < description1.length(); i++) {

			char ch = description1.charAt(i);

			if (!done) {

				x1 += ch;

				if (ch == ' ') {

					Count++;

				}

				if (Count >= 10) {

					done = true;

				}

			} else {

				x2 += ch;

			}

		}

		description = x1.trim() + "\n" + x2.trim();

		String rank = "";

		String suit = "";

		if (selectedCard instanceof Standard) {

			Standard standardCard = (Standard) selectedCard;

			rank = String.valueOf(standardCard.getRank());

			suit = standardCard.getSuit().toString();

		}

		String popupText = description + "\n" + rank + "\n" + suit;

		Label infoLabel = new Label(popupText);

		infoLabel
				.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 8; "

						+ "-fx-background-radius: 5; -fx-border-radius: 5;");

		infoLabel.setMouseTransparent(true);

		Bounds bounds = parent.localToScene(parent.getBoundsInLocal());

		Point2D localPoint = overlayPane.sceneToLocal(bounds.getMinX(),
				bounds.getMinY());

		double x = localPoint.getX();

		double y = localPoint.getY() - 50;

		infoLabel.applyCss();

		infoLabel.layout();

		double popupWidth = infoLabel.getWidth();

		double overlayWidth = overlayPane.getWidth();

		if (x + popupWidth > overlayWidth) {

			x = overlayWidth - popupWidth - 10;

			if (x < 0)
				x = 0;

		}

		if (y < 0) {

			y = localPoint.getY() + parent.getBoundsInLocal().getHeight() + 5;

		}

		infoLabel.setLayoutX(x);

		infoLabel.setLayoutY(y);

		overlayPane.getChildren().add(infoLabel);

		currentPopupLabel = infoLabel;

	}

	public void setC2Cards() {
		Cards2.getChildren().clear();

		for (int i = 0; i < players.get(2).getHand().size(); i++) {

			ImageView backTV = new ImageView(backT);

			backTV.setFitWidth(76);

			backTV.setFitHeight(112);

			backTV.setPreserveRatio(true);

			Cards2.getChildren().add(backTV);

		}

		Cards2.setAlignment(Pos.CENTER);
	}

	public void setC1Cards() {
		Cards3.getChildren().clear();

		for (int i = 0; i < players.get(1).getHand().size(); i++) {

			ImageView backLV = new ImageView(backL);

			backLV.setFitWidth(112);

			backLV.setFitHeight(76);

			backLV.setPreserveRatio(true);

			Cards3.getChildren().add(backLV);
		}

		Cards3.setAlignment(Pos.CENTER);

	}

	public void setC3Cards() {
		Cards4.getChildren().clear();

		for (int i = 0; i < players.get(3).getHand().size(); i++) {

			ImageView backRV = new ImageView(backR);

			backRV.setFitWidth(112);

			backRV.setFitHeight(76);

			backRV.setPreserveRatio(true);

			Cards4.getChildren().add(backRV);

		}

		Cards4.setAlignment(Pos.CENTER);

	}

	public void hishBoard() {

		try {

			theGame = new BorderPane();

			theGame.setPrefSize(1200, 950);
			theGame.setMinSize(1200, 950);
			theGame.setMaxSize(1200, 950);

			theGame.setStyle("-fx-background-color: seagreen");

			midStack = new StackPane();

			midStack.setPrefSize(600, 600);

			game = new Game(name1.getText());

			players = game.getPlayers();

			myHand = players.get(0).getHand();

			board = game.getBoard();

			colorOrder = new ArrayList<>();

			track = board.getTrack();

			myMarbles = players.get(0).getMarbles();

			c1Marbles = players.get(1).getMarbles();

			c2Marbles = players.get(2).getMarbles();

			c3Marbles = players.get(3).getMarbles();

			playBtn = new Button("Play Turn!!!");

			playBtn.setStyle("-fx-font-size: 16px; -fx-background-color: darkgreen; -fx-text-fill: white;");

			playBtn.setOnAction(e -> handlePlayTurn());

			bottomP.getChildren().add(playBtn);

			VBox actionButtonsContainer = new VBox(5); // 5px spacing between
														// buttons

			actionButtonsContainer.setAlignment(Pos.CENTER);

			// Create Field Marble Button (if not already existing)

			Button fieldBtn = new Button("Field Marble");

			fieldBtn.setStyle("-fx-background-color: navy; -fx-text-fill: white;");

			fieldBtn.setOnAction(e -> fieldMarble());

			// Create Deselect Button

			Button deselectBtn = new Button("Deselect All");

			deselectBtn.setStyle(

			"-fx-background-color: #d9534f; " + // Bootstrap 'danger' red

					"-fx-text-fill: white; " +

					"-fx-font-weight: bold; " +

					"-fx-min-width: 100; " + // Match field button width

					"-fx-padding: 5 10;"

			);

			setupDeselectButton(deselectBtn); // Will add functionality

			// Add buttons to container (Deselect on TOP of Field)

			actionButtonsContainer.getChildren().addAll(deselectBtn, fieldBtn);

			// Add to your bottom panel (replace old fieldBtn if needed)

			bottomP.getChildren().add(actionButtonsContainer);

			boardTable = new Circle(300, Color.rgb(121, 196, 237));

			boardTable.setStroke(Color.rgb(127, 101, 18));

			boardTable.setStrokeWidth(5);

			boardTable.setEffect(new DropShadow(10, Color.BLACK));

			boardGroup = new Group();

			// Add this where you create other UI elements

			curPlayer = new Label("Current: " + game.getActivePlayerColour());

			nextPlayer = new Label("Next: " + game.getNextPlayerColour());

			for (int i = 0; i < 100; i++) {

				Button cell = new Button("");

				Color fill = Color.WHITE;

				Color stroke = Color.PALEGOLDENROD;

				cell.setMinSize(12, 12);

				cell.setMaxSize(12, 12);

				cell.setPrefSize(12, 12);

				cell.setStyle(

				"-fx-background-radius: 12; " +

				"-fx-background-color: " + toRgbString(fill) + "; " +

				"-fx-border-color: " + toRgbString(stroke) + "; " +

				"-fx-border-width: 2px; " +

				"-fx-padding: 0;");

				cell.setShape(new Circle(12));

				double angle = (2 * Math.PI * i / 100) + Math.PI / 2
						+ (3 * Math.PI / 100);

				cell.setTranslateX(280 * Math.cos(angle));

				cell.setTranslateY(280 * Math.sin(angle));

				boardGroup.getChildren().add(cell);

				trackCells.add(cell);

				cell.setOnAction(ActionEvent -> handle(ActionEvent));

			}

			safeT.setAlignment(Pos.CENTER);

			safeB.setAlignment(Pos.CENTER);

			safeR.setAlignment(Pos.CENTER);

			safeL.setAlignment(Pos.CENTER);

			safeTz = new ArrayList();

			safeBz = new ArrayList();

			safeLz = new ArrayList();

			safeRz = new ArrayList();

			for (int i = 0; i < 4; i++) {

				Color fill = Color.LIGHTGOLDENRODYELLOW;

				Button c1 = new Button();

				c1.setPrefSize(12, 12);

				c1.setMinSize(12, 12);

				c1.setMaxSize(12, 12);

				c1.setStyle("-fx-background-radius: 12; "
						+ "-fx-background-color: " + toRgbString(fill) + "; "
						+ "-fx-padding: 0;");

				c1.setShape(new Circle(12));

				Button c2 = new Button();

				c2.setPrefSize(12, 12);

				c2.setMinSize(12, 12);

				c2.setMaxSize(12, 12);

				c2.setStyle("-fx-background-radius: 12; "
						+ "-fx-background-color: " + toRgbString(fill) + "; "
						+ "-fx-padding: 0;");

				c2.setShape(new Circle(12));

				Button c3 = new Button();

				c3.setPrefSize(12, 12);

				c3.setMinSize(12, 12);

				c3.setMaxSize(12, 12);

				c3.setStyle("-fx-background-radius: 12; "
						+ "-fx-background-color: " + toRgbString(fill) + "; "
						+ "-fx-padding: 0;");

				c3.setShape(new Circle(12));

				Button c4 = new Button();

				c4.setPrefSize(12, 12);

				c4.setMinSize(12, 12);

				c4.setMaxSize(12, 12);

				c4.setStyle("-fx-background-radius: 12; "
						+ "-fx-background-color: " + toRgbString(fill) + "; "
						+ "-fx-padding: 0;");

				c4.setShape(new Circle(12));

				safeT.getChildren().add(c1);
				safeTz.add(c1);

				safeB.getChildren().add(c2);
				safeBz.add(c2);

				safeR.getChildren().add(c3);
				safeRz.add(c3);

				safeL.getChildren().add(c4);
				safeLz.add(c4);

			}

			home1.setFitWidth(100);

			home1.setFitHeight(100);

			home2.setFitWidth(100);

			home2.setFitHeight(100);

			home3.setFitWidth(100);

			home3.setFitHeight(100);

			home4.setFitWidth(100);

			home4.setFitHeight(100);

			for (int i = 0; i < 4; i++) {

				colorOrder.add(players.get(i).getColour());

			}

			myC = getJavaFXColor(colorOrder.get(0));

			c1C = getJavaFXColor(colorOrder.get(1));

			c2C = getJavaFXColor(colorOrder.get(2));

			c3C = getJavaFXColor(colorOrder.get(3));

			homezone1.setPrefSize(100, 100);

			homezone2.setPrefSize(100, 100);

			homezone3.setPrefSize(100, 100);

			homezone4.setPrefSize(100, 100);

			fillHomeZones(colorOrder);

			home1.setTranslateX(-150);
			home1.setTranslateY(-150);
			homezone1.setTranslateX(-150);
			homezone1.setTranslateY(-150);

			home2.setTranslateX(150);
			home2.setTranslateY(-150);
			homezone2.setTranslateX(150);
			homezone2.setTranslateY(-150);

			home3.setTranslateX(-150);
			home3.setTranslateY(150);
			homezone3.setTranslateX(-150);
			homezone3.setTranslateY(150);

			home4.setTranslateX(150);
			home4.setTranslateY(150);
			homezone4.setTranslateX(150);
			homezone4.setTranslateY(150);

			home1.setMouseTransparent(true);

			home2.setMouseTransparent(true);

			home3.setMouseTransparent(true);

			home4.setMouseTransparent(true);

			homez1.getChildren().addAll(home1, homezone1);

			homez2.getChildren().addAll(home2, homezone2);

			homez3.getChildren().addAll(home3, homezone3);

			homez4.getChildren().addAll(home4, homezone4);

			homesGroup = new Group(homez1, homez2, homez3, homez4);

			VBox safeVT = new VBox(380);

			safeVT.getChildren().addAll(safeB, safeT);

			safeVT.setPrefWidth(600);

			safeVT.setPrefHeight(600);

			HBox safeHT = new HBox(380);

			safeHT.getChildren().addAll(safeL, safeR);

			safeHT.setPrefWidth(600);

			safeHT.setPrefHeight(600);

			safeVT.setAlignment(Pos.CENTER);

			safeHT.setAlignment(Pos.CENTER);

			safeContainer = new StackPane();

			safeContainer.setPrefSize(600, 600);

			safeContainer.getChildren().addAll(safeVT, safeHT);

			safeContainer.setAlignment(Pos.CENTER);

			highlightTrapCells();

			ImageView firePit = new ImageView(new Image("firepit.png"));

			firePit.setFitWidth(150);

			firePit.setFitHeight(150);

			// updateFirePit();

			ImageView deck = new ImageView(new Image("back.png"));

			deck.setFitWidth(100);

			deck.setFitHeight(140);

			fireCard = new ImageView(new Image("back.png")); // in play after
																// playing card

			fireCard.setFitWidth(70);

			fireCard.setFitHeight(106);

			// / VBox boardMid = new VBox(10, firePit, deck);

			firePitContainer = new StackPane();

			firePitContainer.getChildren().addAll(firePit, fireCard);

			firePitContainer.setAlignment(Pos.CENTER);

			boardMid = new VBox(10, firePitContainer, deck);

			boardMid.setAlignment(Pos.CENTER);

			midStack.getChildren().addAll(boardTable, boardMid, safeContainer,
					homesGroup, boardGroup);

			midStack.setAlignment(Pos.CENTER);

			theGame.setCenter(midStack);

			bottomP.setPrefWidth(960);

			topP.setPrefWidth(960);

			leftP.setPrefWidth(320);

			rightP.setPrefWidth(320);

			bottomP.setPrefHeight(90);

			topP.setPrefHeight(90);

			leftP.setPrefHeight(850);

			rightP.setPrefHeight(850);

			theGame.setLeft(leftP);

			theGame.setRight(rightP);

			theGame.setTop(topP);

			theGame.setBottom(bottomP);

			turns = new VBox(8);

			Label curPlayer = new Label("Current player: "
					+ game.getActivePlayerColour().toString());

			Label nextPlayer = new Label("Next player: "
					+ game.getNextPlayerColour().toString());

			curPlayer
					.setStyle("-fx-text-fill: purple; -fx-font-size: 18px; -fx-font-weight: bold;");

			nextPlayer
					.setStyle("-fx-text-fill: purple; -fx-font-size: 18px; -fx-font-weight: bold;");

			turns.getChildren().addAll(curPlayer, nextPlayer);

			turns.setStyle("-fx-background-color: yellow;-fx-border-color: orange; -fx-border-width: 2;");

			turns.setAlignment(Pos.BASELINE_LEFT);

			turns.setPrefSize(200, 50);

			turns.setPadding(new Insets(10, 10, 10, 10));

			rightP.getChildren().add(turns);

			leftP.getChildren().addAll(CPU1, Cards3);

			leftP.setAlignment(Pos.CENTER);

			rightP.getChildren().addAll(CPU2, Cards4);

			rightP.setAlignment(Pos.CENTER);

			topP.getChildren().addAll(CPU3, Cards2);

			topP.setAlignment(Pos.CENTER);

			bottomP.getChildren().addAll(name1, Cards1);

			bottomP.setAlignment(Pos.CENTER);

		}

		catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void handle(ActionEvent event) {

		// Clear any previous selection effects

		ArrayList<Button> allButtons = new ArrayList<>();

		allButtons.addAll(myButtons);

		allButtons.addAll(c1Buttons);

		allButtons.addAll(c2Buttons);

		allButtons.addAll(c3Buttons);

		for (Button b : allButtons) {

			b.setEffect(null);

		}

		for (Button b : trackCells) {

			b.setEffect(null);

		}

		// 1. Handle TRACK CELL selection

		if (trackCells.contains(event.getSource())) {

			int index = trackCells.indexOf(event.getSource());

			Cell cell = track.get(index);

			if (cell.getMarble() != null) {

				trackCells.get(index).setEffect(glow);

				try {

					game.selectMarble(cell.getMarble());

					System.out.println("Selected track marble at position: "
							+ index);

				} catch (InvalidMarbleException e) {

					e.printStackTrace();

					game.deselectAll();

				}

			}

		}

		// 2. Handle HOME ZONE marble selection

		else if (myButtons.contains(event.getSource())) {

			int index = myButtons.indexOf(event.getSource());

			myButtons.get(index).setEffect(glow);

			try {

				game.selectMarble(myMarbles.get(index));

				System.out.println("Selected my marble: " + index);

			} catch (InvalidMarbleException e) {

				e.printStackTrace();

				game.deselectAll();

			}

		}

		else if (c1Buttons.contains(event.getSource())) {

			int index = c1Buttons.indexOf(event.getSource());

			c1Buttons.get(index).setEffect(glow);

			try {

				game.selectMarble(c1Marbles.get(index));

				System.out.println("Selected CPU1 marble: " + index);

			} catch (InvalidMarbleException e) {

				e.printStackTrace();

				game.deselectAll();

			}

		}

		else if (c2Buttons.contains(event.getSource())) {

			int index = c2Buttons.indexOf(event.getSource());

			c2Buttons.get(index).setEffect(glow);

			try {

				game.selectMarble(c2Marbles.get(index));

				System.out.println("Selected CPU2 marble: " + index);

			} catch (InvalidMarbleException e) {

				e.printStackTrace();

				game.deselectAll();

			}

		}

		else if (c3Buttons.contains(event.getSource())) {

			int index = c3Buttons.indexOf(event.getSource());

			c3Buttons.get(index).setEffect(glow);

			try {

				game.selectMarble(c3Marbles.get(index));

				System.out.println("Selected CPU3 marble: " + index);

			} catch (InvalidMarbleException e) {

				e.printStackTrace();

				game.deselectAll();

			}

		}

	}

	private void highlightTrapCells() {

		for (int i = 0; i < track.size(); i++) {

			if (track.get(i).isTrap()) {

				Button trapButton = trackCells.get(i); 

				trapButton.setStyle(

				"-fx-background-radius: 12; " +

				"-fx-background-color: #EF2B7C; " + // Tomato color

						"-fx-border-color: #EF2B7C; " + // Dark red border

						"-fx-border-width: 3px; " +

						"-fx-padding: 0;"

				);

				// Add permanent visual effect

				DropShadow trapGlow = new DropShadow();

				trapGlow.setColor(Color.FUCHSIA);

				trapGlow.setRadius(10);

				trapGlow.setSpread(0.3);

				trapButton.setEffect(trapGlow);

				// Optional: Make unclickable

				trapButton.setDisable(true);

			}

		}

	}

	public void updateTrack() {

		for (int i = 0; i < track.size(); i++) {

			Cell cur = track.get(i);

			Marble marble = cur.getMarble();

			Button btn = trackCells.get(i);

			if (marble != null) {

				Color marbleColor = getJavaFXColor(marble.getColour());

				btn.setStyle(

				"-fx-background-radius: 12; " +

				"-fx-background-color: " + toRgbString(marbleColor) + "; " +

				"-fx-border-color: " + toRgbString(Color.PALEGOLDENROD) + "; " +

				"-fx-border-width: 2px; " +

				"-fx-padding: 0;"

				);

			} else {

				btn.setStyle(

				"-fx-background-radius: 12; " +

				"-fx-background-color: white; " +

				"-fx-border-color: " + toRgbString(Color.PALEGOLDENROD) + "; " +

				"-fx-border-width: 2px; " +

				"-fx-padding: 0;"

				);

			}

		}

		//adjustHomeZone();

	}

	public void updateSafeZones() {

		ArrayList<SafeZone> safezones = board.getSafeZones();

		for (int i = 0; i < 4; i++) {

			ArrayList<Cell> cells = safezones.get(i).getCells();

			for (int j = 0; j < 4; j++) {

				Marble marble = cells.get(j).getMarble();

				if (marble != null) {

					switch (i) {

					case 0:
						Button b = safeBz.get(j);
						b.setStyle("-fx-background-color: " + toRgbString(myC)
								+ ";");
						break;

					case 1:
						Button b1 = safeLz.get(j);
						b1.setStyle("-fx-background-color: " + toRgbString(c1C)
								+ ";");
						break;

					case 2:
						Button b2 = safeTz.get(j);
						b2.setStyle("-fx-background-color: " + toRgbString(c2C)
								+ ";");
						break;

					case 3:
						Button b3 = safeRz.get(j);
						b3.setStyle("-fx-background-color: " + toRgbString(c3C)
								+ ";");
						break;

					}

				}

				if (marble == null) {

					switch (i) {

					case 0:
						Button b = safeBz.get(j);
						b.setStyle("-fx-background-color: LIGHTGOLDENRODYELLOW;");
						break;

					case 1:
						Button b1 = safeLz.get(j);
						b1.setStyle("-fx-background-color: LIGHTGOLDENRODYELLOW;");
						break;

					case 2:
						Button b2 = safeTz.get(j);
						b2.setStyle("-fx-background-color: LIGHTGOLDENRODYELLOW;");
						break;

					case 3:
						Button b3 = safeRz.get(j);
						b3.setStyle("-fx-background-color: LIGHTGOLDENRODYELLOW;");
						break;

					}

				}

			}

		}

	}

	private void adjustHomeZone() {

		for (int i = 0; i < track.size(); i++) {

			Cell cell = track.get(i);

			if (cell.isTrap() && cell.getMarble() != null) {

				Marble marble = cell.getMarble();

				Colour color = marble.getColour();

				int playerIndex = colorOrder.indexOf(color);

				GridPane homeZone = getHomeZone(playerIndex);

				ArrayList<Button> homeButtons = getHomeButtons(playerIndex);

				for (int j = 0; j < 4; j++) {

					int row = j < 2 ? 0 : 1;

					int col = j % 2;

					boolean positionEmpty = true;

					for (Node node : homeZone.getChildren()) {

						if (GridPane.getRowIndex(node) == row &&

						GridPane.getColumnIndex(node) == col) {

							positionEmpty = false;

							break;

						}

					}

					if (positionEmpty) {

						homeZone.add(homeButtons.get(j), col, row);

						cell.setMarble(null); // Remove from track

						updateTrack(); // Update the track display

						break;

					}

				}

			}

		}

	}

	private GridPane getHomeZone(int playerIndex) {

		switch (playerIndex) {

		case 0:
			return homezone3; // Player

		case 1:
			return homezone1; // CPU1

		case 2:
			return homezone4; // CPU2

		case 3:
			return homezone2; // CPU3

		default:
			return homezone3;

		}

	}

	private ArrayList<Button> getHomeButtons(int playerIndex) {

		switch (playerIndex) {

		case 0:
			return myButtons;

		case 1:
			return c1Buttons;

		case 2:
			return c2Buttons;

		case 3:
			return c3Buttons;

		default:
			return myButtons;

		}

	}

	private void setupDeselectButton(Button button) {

		button.setOnAction(e -> {

			game.deselectAll();

			lastPlayedCard = null;

			clearMarbleSelections();

			clearCardSelections();

		});

	}

	private void clearMarbleSelections() {

		for (Button marbleBtn : myButtons) {

			marbleBtn.setEffect(null);

		}

		for (Button trackBtn : trackCells) {

			trackBtn.setEffect(null);

		}

	}

	private void clearCardSelections() {

		for (Node node : Cards1.getChildren()) {

			if (node instanceof Button) {

				Button cardBtn = (Button) node;

				cardBtn.setEffect(null);

				cardBtn.setTranslateY(0);

			}

		}

	}

	private void fieldMarble() {

		Colour activeColor = game.getActivePlayerColour();

		int basePosition = -1;

		switch (colorOrder.indexOf(game.getActivePlayerColour())) {

		case 0:

			basePosition = 0;

			break;

		case 1:

			basePosition = 25;

			break;

		case 2:

			basePosition = 50;

			break;

		case 3:

			basePosition = 75;

			break;

		default:

			throw new IllegalStateException("Invalid player color");

		}

		Cell baseCell = track.get(basePosition);

		if (baseCell.getMarble() != null) {

			alert("Cell Occupied", "Base position is already occupied!");

			return;

		}

		if (game.getActivePlayerColour() == players.get(0).getColour()) { // Human
																			// player

			for (int i = 0; i < myMarbles.size(); i++) {

				Button marbleBtn = myButtons.get(i);

				if (homezone3.getChildren().contains(marbleBtn)) {

					baseCell.setMarble(myMarbles.get(i));

					homezone3.getChildren().remove(marbleBtn);

					trackCells.get(basePosition).setStyle(
							"-fx-background-radius: 12; "
									+ "-fx-background-color: "
									+ toRgbString(myC) + "; "
									+ "-fx-border-color: "
									+ toRgbString(Color.PALEGOLDENROD) + "; "
									+ "-fx-border-width: 2px; "
									+ "-fx-padding: 0;");

					return;

				}

			}

			alert("No Marbles", "All marbles are already on track!");

		}

	}


	private boolean isSplitMoveRequired() {

		return lastPlayedCard instanceof Standard &&

		((Standard) lastPlayedCard).getRank() == 7;

	}

	private void showSplitDialog() {

		Stage dialog = new Stage();

		TextField inputField = new TextField();

		inputField.setPromptText("Enter split distance (1-6)");

		Button submit = new Button("Confirm Split");

		Label message = new Label();

		submit.setOnAction(e -> handleSplitInput(inputField, message, dialog));

		VBox layout = new VBox(10, inputField, submit, message);

		layout.setPadding(new Insets(15));

		dialog.setScene(new Scene(layout, 300, 150));

		dialog.setTitle("Split Your Move");

		dialog.show();

	}

	private void handleSplitInput(TextField input, Label message, Stage dialog) {

		try {

			int distance = Integer.parseInt(input.getText());

			game.editSplitDistance(distance);

			dialog.close();

			executePlayerTurn1();

		} catch (NumberFormatException e) {

			message.setText("Please enter a number!");

		} catch (SplitOutOfRangeException e) {

			message.setText("Distance must be 1-6");

		} catch (Exception e) {

			message.setText("Invalid move");

		}

	}

	private void updateGameState() {
	    updateTrack();
	    updateSafeZones();
	    adjustHomeZone();
	    setMyCards(players.get(0).getHand());
	    updateFirePit();
	    updateTurnIndicator();
	    lastPlayedCard = null;
	}

	private void updateHand() {

		Cards1.getChildren().clear(); // Clear existing cards

		ArrayList<Card> currentHand = players.get(0).getHand(); // Get updated
																// hand

		double cardWidth = 76;

		double cardHeight = 112;

		for (Card card : currentHand) {

			if (card == null)
				continue; // safety check

			ImageView cardView = new ImageView(getCardImage(card));

			cardView.setFitWidth(cardWidth);

			cardView.setFitHeight(cardHeight);

			cardView.setPreserveRatio(true);

			// Handle click

			cardView.setOnMouseClicked(e -> {

				try {

					game.selectCard(card);

				} catch (InvalidCardException e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();

				}

				lastPlayedCard = card; // Track for GUI

				updateHand(); // Optional: visually highlight selection

			});

			Cards1.getChildren().add(cardView);

		}

	}

	private void updateFirePit() {

		if (lastPlayedCard != null) {
			fireCard.setImage(null);

			fireCard.setImage(getCardImage(lastPlayedCard));} 

	}

	private Image getCardImage(Card card) {

		if (card.getName().equals("MarbleBurner")) {

			return new Image("burner.png");

		} else if (card.getName().equals("MarbleSaver")) {

			return new Image("saver.png");

		} else if (card instanceof Standard) {

			Standard standCard = (Standard) card;

			String suit = standCard.getSuit().toString().toLowerCase() + "s";

			String name;

			switch (standCard.getRank()) {

			case 1:
				name = "ace";
				break;

			case 11:
				name = "jack";
				break;

			case 12:
				name = "queen";
				break;

			case 13:
				name = "king";
				break;

			default:
				name = String.valueOf(standCard.getRank());

			}

			return new Image(name + "_of_" + suit + ".png");

		}

		return new Image("back.png");

	}


	private void alert(String title, String message) {

		Stage alertStage = new Stage();

		alertStage.setTitle(title);

		Label label = new Label(message);

		Button closeButton = new Button("7ader");

		closeButton.setOnAction(event -> alertStage.close());

		BorderPane pane = new BorderPane();

		pane.setTop(label);

		pane.setCenter(closeButton);

		Scene scene = new Scene(pane, 500, 100);

		alertStage.setScene(scene);

		alertStage.show();

	}

	private Color getJavaFXColor(Colour colour) {

		switch (colour) {

		case RED:

			return Color.rgb(255, 0, 0);

		case GREEN:

			return Color.rgb(0, 255, 0);

		case BLUE:

			return Color.rgb(0, 0, 255);

		case YELLOW:

			return Color.rgb(255, 255, 0);

		default:

			return Color.WHITE;

		}

	}

	public void adjustHomeZones() {

		int myCount = 0;

		int c1Count = 0;

		int c2Count = 0;

		int c3Count = 0;

		for (int i = 0; i < 100; i++) {

			if (getButtonColor(trackCells.get(i)) == myC) {

				myCount++;

			}

			if (getButtonColor(trackCells.get(i)) == c1C) {

				c1Count++;

			}

			if (getButtonColor(trackCells.get(i)) == c2C) {

				c2Count++;

			}

			if (getButtonColor(trackCells.get(i)) == c3C) {

				c3Count++;

			}

		}

		int mydiff = 4 - myCount;

		homezone3.getChildren().clear();

		for (int j = 0; j < mydiff; j++) {

			// homezone3.getChildren().add(myButtons.get(j));

		}

		int diff1 = 4 - c1Count;

		homezone1.getChildren().clear();

		for (int j = 0; j < diff1; j++) {

			homezone1.getChildren().add(c1Buttons.get(j));

		}

		int diff2 = 4 - c2Count;

		homezone4.getChildren().clear();

		for (int j = 0; j < diff2; j++) {

			homezone4.getChildren().add(c2Buttons.get(j));

		}

		int diff3 = 4 - c3Count;

		homezone2.getChildren().clear();

		for (int j = 0; j < diff3; j++) {

			homezone2.getChildren().add(c3Buttons.get(j));

		}

	}

	public static Color getButtonColor(Button button) {

		String style = button.getStyle();

		String bgColorPart = style.split("-fx-background-color:")[1].split(";")[0]
				.trim();

		return parseRgbString(bgColorPart);

	}

	private static Color parseRgbString(String rgbString) {

		try {

			if (rgbString == null || !rgbString.startsWith("rgb(")
					|| !rgbString.endsWith(")")) {

				System.out.println("[ERROR] Invalid RGB string: " + rgbString);

				return Color.BLACK;

			}

			String values = rgbString.substring(4, rgbString.length() - 1);

			String[] components = values.split(",");

			if (components.length != 3) {

				System.out.println("[ERROR] RGB component count mismatch: "
						+ rgbString);

				return Color.BLACK;

			}

			int r = Integer.parseInt(components[0].trim());

			int g = Integer.parseInt(components[1].trim());

			int b = Integer.parseInt(components[2].trim());

			return Color.rgb(r, g, b);

		}

		catch (Exception e) {

			System.out.println("[ERROR] Failed to parse RGB string: "
					+ rgbString);

			e.printStackTrace();

			return Color.BLACK;

		}

	}

	 private void handlePlayTurn() {

		    try {

		        if (!game.canPlayTurn()) {

		            skipTurn();

		            return;

		        }


		        if (isSplitMoveRequired()) {

		            showSplitDialog();  // Waits for user input

		        } else {

		        	executePlayerTurn1() ;

		        }

		    } catch (Exception e) {

		        e.printStackTrace();

		        alert("Turn Error", e.getMessage());

		    }

		}

	 

	 private void executePlayerTurn1() {

		    try {

		        game.playPlayerTurn();

		        updateGameState();

		        checkTrapEntry();          


		        if (lastPlayedCard != null) {

		            fireCard.setImage(getCardImage(lastPlayedCard));

		            fireCard.setVisible(true);


		            PauseTransition pause = new PauseTransition(Duration.seconds(3));

		            pause.setOnFinished(event -> fireCard.setVisible(false));

		            pause.play();

		        }


		        game.endPlayerTurn();     

		        updateTurnIndicator();

		        highlightTrapCells();

		        checkWinOrContinue();


		    } catch (Exception e) {

		        alert("Error", e.getMessage());

		    }

		}


	 

	 private void skipTurn() throws Exception {

		 //checkTrapEntry();   

		    game.endPlayerTurn();

		    checkTrapEntry(); 

		    updateTurnIndicator();

		    highlightTrapCells();

		    setMyCards(players.get(0).getHand());

		    handlenext();

		}

	 

	 private void handlenext() {

		    updateTurnIndicator();

		    highlightTrapCells();

		    if (game.getActivePlayerColour().equals(players.get(0).getColour())) {

		        updateHand();  // Only for human

		        return;

		    }


		    // CPU turn

		    PauseTransition pause = new PauseTransition(Duration.seconds(5));

		    pause.setOnFinished(event -> {

		        try {

		            game.playPlayerTurn();

		            //checkTrapEntry();     

		            updateGameState();

		            //checkTrapEntry();   

		            game.endPlayerTurn();

		            checkTrapEntry(); 

		            

		            updateTurnIndicator();

		            highlightTrapCells();

		            checkWinOrContinue();

		        } catch (Exception e) {

		            System.out.println("CPU error: " + e.getMessage());

		            try {

		            	checkTrapEntry();   

		                game.endPlayerTurn();

		                checkTrapEntry(); 

		                updateTurnIndicator();

		                highlightTrapCells();

		            } catch (Exception ex) {

		                ex.printStackTrace();

		            }

		           handlenext(); //lazem ykamel

		        }

		    });

		    pause.play();

		}

	 

	 private void checkWinOrContinue() {

		    Colour winner = game.checkWin();

		    if (winner != null) {

		        alert("Game Over!!!!!!!", "Player " + winner + " wins!");

		        return;

		    }


		    handlenext();

		}

	 private void updateTurnIndicator() {

	        Colour current = game.getActivePlayerColour();

	        Colour next = game.getNextPlayerColour();


	       curPlayer.setText("Current: " + current);

	       nextPlayer.setText("Next: " + next);

	       

    turns.getChildren().clear();

  turns.getChildren().addAll(curPlayer,nextPlayer);

	       

	        System.out.println("Turn: " + current);

	    }

	 public ArrayList <Cell>trapsArray() {

		 ArrayList<Cell> traps = new ArrayList();

		 for(int i=0; i<track.size();i++) {

			 if (track.get(i).isTrap()) {

				 traps.add(track.get(i));

			 }

		 }

		 return traps;

	 }

	 private void checkTrapEntry() {

		 System.out.println("trap check:");

		    for (int i=0; i<trapsArray().size();i++) {

		    	Cell trap= trapsArray().get(i);

		        Marble m = trap.getMarble();

		        if (m!=null && m.getColour() == players.get(0).getColour()) {

		        	System.out.println("array is : " + m.getColour());//debuggggg

		            alert("Trap!", "yallahwi! You landed on a trap cell.");

		            break;

		        }

		    }

		}
	// --- Turn Handling ---
//
//	private void handlePlayTurn() {
//		   if (game.getActivePlayerColour().equals(players.get(0).getColour())) {
//		       try {
//		           game.playPlayerTurn();
//		           updateGameState();
//		           game.endPlayerTurn();
//		           updateTurnIndicator();
//		           Colour winner = game.checkWin();
//		           if (winner != null) {
//		               alert("Game Over", "Player " + winner + " wins!");
//		               return;
//		           }
//		           updateGameState();
//		           playCPUTurns(1);
//		       } catch (Exception e) {
//		           alert("Play Error", e.getMessage());
//		       }
//		   }
//		}
//
//		private void playCPUTurns(int cpuIndex) {
//		 
//		   PauseTransition pause = new PauseTransition(Duration.seconds(1.2));
//		   pause.setOnFinished(event -> {
//		       try {
//		           game.playPlayerTurn();
//		           updateGameState();
//		           game.endPlayerTurn();
//		           updateTurnIndicator();
//		           Colour winner = game.checkWin();
//		           if (winner != null) {
//		               alert("Game Over", "Player " + winner + " wins!");
//		               return;
//		           }
//		           updateGameState();
//		           setC1Cards();
//		           setC2Cards();
//		           setC3Cards();
//		           playCPUTurns(cpuIndex + 1);
//		       } catch (Exception e) {
//		           playCPUTurns(cpuIndex + 1);
//		       }
//		   });
//		   pause.play();
//		}
	 private void fieldCpuMarbles() {
		// CPU Player 1 (Blue)
		   if (game.getActivePlayerColour() == players.get(1).getColour()) {
		       int basePosition1 = 25;
		       Cell baseCell1 = track.get(basePosition1);
		       
		       if (baseCell1.getMarble() == null) {
		           for (int i = 0; i < c1Marbles.size(); i++) {
		               Button marbleBtn = c1Buttons.get(i);
		               if (homezone1.getChildren().contains(marbleBtn)) {
		                   baseCell1.setMarble(c1Marbles.get(i));
		                   homezone1.getChildren().remove(marbleBtn);
		                   trackCells.get(basePosition1).setStyle(
		                       "-fx-background-radius: 12; " +
		                       "-fx-background-color: " + toRgbString(Color.BLUE) + "; " +
		                       "-fx-border-color: " + toRgbString(Color.PALEGOLDENROD) + "; " +
		                       "-fx-border-width: 2px; " +
		                       "-fx-padding: 0;");
		                   return;
		               }
		           }
		       }
		   }
		   
		   // CPU Player 2 (Green)
		   if (game.getActivePlayerColour() == players.get(2).getColour()) {
		       int basePosition1 = 50;
		       Cell baseCell1 = track.get(basePosition1);
		       
		       if (baseCell1.getMarble() == null) {
		           for (int i = 0; i < c2Marbles.size(); i++) {
		               Button marbleBtn = c2Buttons.get(i);
		               if (homezone2.getChildren().contains(marbleBtn)) {
		                   baseCell1.setMarble(c2Marbles.get(i));
		                   homezone2.getChildren().remove(marbleBtn);
		                   trackCells.get(basePosition1).setStyle(
		                       "-fx-background-radius: 12; " +
		                       "-fx-background-color: " + toRgbString(Color.GREEN) + "; " +
		                       "-fx-border-color: " + toRgbString(Color.PALEGOLDENROD) + "; " +
		                       "-fx-border-width: 2px; " +
		                       "-fx-padding: 0;");
		                   return;
		               }
		           }
		       }
		   }
		   
		   // CPU Player 3 (Yellow)
		   if (game.getActivePlayerColour() == players.get(3).getColour()) {
		       int basePosition1 = 75;
		       Cell baseCell1 = track.get(basePosition1);
		       
		       if (baseCell1.getMarble() == null) {
		           for (int i = 0; i < c3Marbles.size(); i++) {
		               Button marbleBtn = c3Buttons.get(i);
		               if (homezone3.getChildren().contains(marbleBtn)) {
		                   baseCell1.setMarble(c3Marbles.get(i));
		                   homezone3.getChildren().remove(marbleBtn);
		                   trackCells.get(basePosition1).setStyle(
		                       "-fx-background-radius: 12; " +
		                       "-fx-background-color: " + toRgbString(Color.YELLOW) + "; " +
		                       "-fx-border-color: " + toRgbString(Color.PALEGOLDENROD) + "; " +
		                       "-fx-border-width: 2px; " +
		                       "-fx-padding: 0;");
		                   return;
		               }
		           }
		       }
		   }
		 
		}
	
	public static void main(String[] args) {

		launch(args);

	}

}
