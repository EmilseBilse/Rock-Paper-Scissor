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
        //if the results size is less than 1 the bot cant do the calculation and returns a random move
        if(results.size()<1) {
            return  botUtils.getRandomMove();
        }
         else{
             //finds the last human made move and returns the move which would beat it
            Move opponentMove = botUtils.getLastMove(botUtils.getHumanMoves(results));
            return botUtils.getWinningMove(opponentMove);
        }
    }


    @Override
    public String BotName() {
        return "Bot_beatLastMove";
    }
}
