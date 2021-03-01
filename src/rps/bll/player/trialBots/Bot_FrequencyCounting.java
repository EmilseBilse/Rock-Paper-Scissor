package rps.bll.player.trialBots;

import rps.bll.game.Move;
import rps.bll.game.Result;
import java.util.ArrayList;

/*
this bot uses counting to predict the players next move
 */

public class Bot_FrequencyCounting implements IBot {

    public ArrayList<Result> historicData;
    private BotUtils botUtils = new BotUtils();

    public Bot_FrequencyCounting(ArrayList<Result> historicData) {
        this.historicData = historicData;
    }

    public Move getMove() {
        return calculateMove();
    }

    private Move calculateMove() {
        Move opponentMove = botUtils.getMostFrequentMove(botUtils.getOpponentMoves(historicData));
        return botUtils.getWinningMove(opponentMove);
    }

    public void setResults(ArrayList<Result> historicData) {
        this.historicData = historicData;
    }

    @Override
    public String BotName() {
        return "bot1";
    }

}
