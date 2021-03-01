package rps.bll.player.trialBots;

import rps.bll.game.Move;
import rps.bll.game.Result;
import java.util.ArrayList;



public class Bot_FrequencyCounting {

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


}
