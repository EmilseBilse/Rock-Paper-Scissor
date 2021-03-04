package rps.gui.controller;

// Java imports
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    public ImageView imgScissor;
    public ImageView imgPaper;
    public ImageView imgRock;
    public ListView listView;
    public Label lblBotScore;
    public Label lblPlayerScore;
    public ImageView imgBot;
    public ImageView imgPlayer;

    private IPlayer human = new Player("player", PlayerType.Human);
    private IPlayer bot = new Player("bot", PlayerType.AI);

    private GameManager ge = new GameManager(human, bot);

    private int botScore = 0;
    private int playerScore = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setImage("image/scissors.png", imgScissor);
        setImage("image/copy.png", imgPaper);
        setImage("image/stones.png", imgRock);
        setImage("image/bot.png", imgBot);
        setImage("image/human.png", imgPlayer);


    }


    public void setImage(String path, ImageView imageView){
        File file = new File(path);
        Image image = new Image(String.valueOf(file));
        imageView.setImage(image);
    }


    public void selectRock(MouseEvent mouseEvent) {
        playerMove("Rock");
    }

    public void selectPaper(MouseEvent mouseEvent) {
        playerMove("Paper");
    }

    public void selectScissor(MouseEvent mouseEvent) {
        playerMove("Scissor");
    }

    public String getResultAsString(Result result) {
        String statusText = result.getType() == ResultType.Win ? "wins over " : "ties ";

        return "Round #" + result.getRoundNumber() + ":" +
                result.getWinnerPlayer().getPlayerName() +
                " (" + result.getWinnerMove() + ") " +
                statusText + result.getLoserPlayer().getPlayerName() +
                " (" + result.getLoserMove() + ")!";
    }

    public void playerMove(String move){

        listView.getItems().clear();
        ge.playRound(Move.valueOf(move));
        setScores(ge.getGameState().getLastResult());
        ge.getGameState().getHistoricResults().forEach((result) -> {
            listView.getItems().add(getResultAsString(result));
        });
    }

    public void setScores(Result result){
        if(result.getType() != ResultType.Tie) {
            if (result.getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
                botScore += 1;
                lblBotScore.setText(String.valueOf(new Integer(botScore)));
            } else {
                playerScore += 1;
                lblPlayerScore.setText(String.valueOf(new Integer(playerScore)));
            }
        }
    }

}
