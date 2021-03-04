package rps.bll.player.trialBots;


import rps.bll.game.Move;
import rps.bll.game.Result;

import java.util.ArrayList;
import java.util.List;

// this bot assumes the player is predicting its rotation, and beats the moves that would have defeated it.
public class Bot_AntiRotationPredicting implements IBot {

    private ArrayList<Result> historicData;
    private BotUtils botUtils = new BotUtils();

    public Bot_AntiRotationPredicting(ArrayList<Result> historicData) {
        this.historicData = historicData;
    }

    @Override
    public Move getMove() {
        return calculateMove(historicData);
    }

    @Override
    public String BotName() {
        return "Bot_AntiRot";
    }

    @Override
    public Move calculateMove(List<Result> results) {
        //if the size of the given list is less than 3 the bot cant do a calculation and returns a random move
        if(results.size() <3) {
            return botUtils.getRandomMove();
        }
        //finds the third most recent move made by a bot and finds the move which would beat it twice
        Move thirdRecentMove = botUtils.getAIMove(results.get(results.size()-3));
        Move returnMove = botUtils.getWinningMove(botUtils.getWinningMove(thirdRecentMove));

        //it returns the move
        return returnMove;
    }
}
