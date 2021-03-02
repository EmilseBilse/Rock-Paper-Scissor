package rps.bll.player.trialBots;


import rps.bll.game.Move;
import rps.bll.game.Result;

import java.util.ArrayList;
import java.util.List;

// this bot assumes the player is predicting, its rotation and beats the moves, that would have defeated it.
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
        if(results.size() <3) {
            return botUtils.getRandomMove();
        }
        ArrayList<Move> ownMoves = new ArrayList<>();
        for (int i = 1; i <=3; i++) {
            ownMoves.add(botUtils.getAIMove(results.get(results.size()-i)));
        }
        ArrayList<Move> beatingMoves = botUtils.getWinningMoves(ownMoves);
        ArrayList<Move> beatingBeatingOwnMoves = botUtils.getWinningMoves(beatingMoves);
        return beatingBeatingOwnMoves.get(2);
    }
}
