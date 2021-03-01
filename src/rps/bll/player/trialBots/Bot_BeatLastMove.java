package rps.bll.player.trialBots;

import rps.bll.game.Move;
import rps.bll.game.Result;

import java.util.ArrayList;

/*
this bot uses the opponents last move to predict the next
 */
public class Bot_BeatLastMove implements IBot {

    private ArrayList<Result> historicData;
    private BotUtils botUtils = new BotUtils();

    public Bot_BeatLastMove(ArrayList<Result> historicData) {
        this.historicData = historicData;
    }

    public Move getMove() {
        if(historicData.size()<1) {
            return botUtils.getRandomMove();
        }
        return calculateMove();
    }

    private Move calculateMove() {
        Move opponentMove = botUtils.getLastMove(botUtils.getOpponentMoves(historicData));
        return botUtils.getWinningMove(opponentMove);
    }

    public void setResults(ArrayList<Result> historicData) {
        this.historicData = historicData;
    }

    @Override
    public String BotName() {
        return "bot2";
    }
}
