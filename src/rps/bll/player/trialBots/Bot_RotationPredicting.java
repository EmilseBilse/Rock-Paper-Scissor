package rps.bll.player.trialBots;

import rps.bll.game.Move;
import rps.bll.game.Result;

import java.util.ArrayList;
import java.util.List;

public class Bot_RotationPredicting implements IBot {

    private ArrayList<Result> historicData;
    private BotUtils botUtils = new BotUtils();

    /*
    this bot uses the opponentmoves from the last three turns to interpret the next
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
        Move returnMove = botUtils.getRandomMove();
        if(historicData.size()>=3) {
            ArrayList<Move> oppRotation = botUtils.interpretRotation(botUtils.getHumanMoves(historicData));
            ArrayList<Move> winRotation = new ArrayList<>();
            for (Move oppMove: oppRotation) {
                winRotation.add(botUtils.getWinningMove(oppMove));
            }
            returnMove = winRotation.get(0);
        }

        return returnMove;
    }

    @Override
    public ArrayList<Result> getResults() {
        return historicData;
    }

    @Override
    public void setResults(ArrayList<Result> historicData) {
        this.historicData = historicData;
    }

    @Override
    public String BotName() {
        return "Bot_RotationPredicting";
    }


}
