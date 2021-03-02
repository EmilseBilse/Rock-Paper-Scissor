package rps.bll.player.trialBots;

import rps.bll.game.Move;
import rps.bll.game.Result;

import java.util.ArrayList;
import java.util.List;

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
        return calculateMove(historicData);
    }

    public Move calculateMove(List<Result> results) {
        Move returnMove = botUtils.getRandomMove();
        if(results.size()>1) {
            Move opponentMove = botUtils.getLastMove(botUtils.getHumanMoves(results));
            returnMove = botUtils.getWinningMove(opponentMove);
        }
        return returnMove;
    }


    @Override
    public String BotName() {
        return "Bot_beatLastMove";
    }
}
