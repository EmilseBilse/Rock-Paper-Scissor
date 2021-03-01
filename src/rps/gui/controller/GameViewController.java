package rps.gui.controller;

// Java imports
import javafx.fxml.Initializable;
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

    private IPlayer human = new Player("player", PlayerType.Human);
    private IPlayer bot = new Player("bot", PlayerType.AI);

    private GameManager ge = new GameManager(human, bot);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setIMG();


    }

    public void setIMG(){
        File scissorFile = new File("image/scissor.png");
        Image scissorImage = new Image(String.valueOf(scissorFile));
        imgScissor.setImage(scissorImage);

        File paperFile = new File("image/paper.png");
        Image paperImage = new Image(String.valueOf(paperFile));
        imgPaper.setImage(paperImage);

        File rockFile = new File("image/rock.png");
        Image rockImage = new Image(String.valueOf(rockFile));
        imgRock.setImage(rockImage);
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

        ge.getGameState().getHistoricResults().forEach((result) -> {
            listView.getItems().add(getResultAsString(result));
        });
    }

}
