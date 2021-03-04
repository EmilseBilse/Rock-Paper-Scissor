package rps.bll.player.trialBots;

import rps.bll.game.Move;
import rps.bll.game.Result;

import java.util.ArrayList;
import java.util.List;

public class Bot_RotationPredicting implements IBot {

    private ArrayList<Result> historicData;
    private BotUtils botUtils = new BotUtils();

    /*
    this bot uses the opponents moves from the last three turns to interpret the next
     */
    public Bot_RotationPredicting(ArrayList<Result> historicData) {
        this.historicData = historicData;
    }

    @Override
    public Move getMove() {
        return calculateMove(historicData);
    }

    @Override
    public Move calculateMove(List<Result> results) {
        //if results size is less than three the calculations wont work and returns a random move
        if(results.size()<3) {
            return botUtils.getRandomMove();
        }
        else {
            //the opponents rotation is interpreted
            ArrayList<Move> oppRotation = botUtils.interpretRotation(botUtils.getHumanMoves(results));
            //the beating move of the interpreted move the opponent would make this round is returned
            return botUtils.getWinningMove(oppRotation.get(0));
        }
    }

    @Override
    public String BotName() {
        return "Bot_RotationPredicting";
    }


}
