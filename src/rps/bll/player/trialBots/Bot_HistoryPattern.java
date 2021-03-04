package rps.bll.player.trialBots;

import rps.bll.game.Move;
import rps.bll.game.Result;

import java.util.ArrayList;
import java.util.List;

/*
this bot tries to find patterns in the result-history to interpret the opponents next move
 */
public class Bot_HistoryPattern implements IBot {

    public ArrayList<Result> historicData;
    private BotUtils botUtils = new BotUtils();

    public Bot_HistoryPattern(ArrayList<Result> historicData) {
        this.historicData = historicData;
    }

    @Override
    public Move getMove() {
        return calculateMove(historicData);
    }

    @Override
    public String BotName() {
        return "bot_HistoryPattern";
    }

    @Override
    public Move calculateMove(List<Result> results) {
        //if the result size is less than 9 the calculation wont work and bot plays a random move
        if(results.size() < 9) {
            return botUtils.getRandomMove();
        }
        else {
            //historyMoves is a list which hold the human made moves from each result
            ArrayList<Move> historyMoves = botUtils.getHumanMoves(results);
            //historyMoves is converted into a String which holds chars representing each move
            String historyMovesString = botUtils.movesToString(historyMoves);
            //the historyStringLength is calculated in an integer
            int historyStringLength = historyMovesString.length();
            //the searchPattern is calculated as a substring of historyMovesString. the pattern consist of the last 5 chars.
            String searchPattern = historyMovesString.substring(historyStringLength - 6);
            //the historyMovesWithoutPattern is historyMovesString minus searchPattern
            String historyMovesWithoutPattern = historyMovesString.substring(0, historyStringLength - 6);

            //checks if the pattern exist in the historyMovesString besides from the original
            if (historyMovesWithoutPattern.contains(searchPattern)) {
                //finds the position of the pattern
                int patternPosition = historyMovesWithoutPattern.indexOf(searchPattern);
                //the character(Move) which followed the pattern
                Move expectedMove = botUtils.charToMove(historyMovesString.charAt(patternPosition+6));
                if(expectedMove != null) {
                    //is that move is not null it returns the move which would beat the expected one
                    return botUtils.getWinningMove(expectedMove);
                }
                else {
                    return botUtils.getRandomMove();
                }
            }
            else {
                return botUtils.getRandomMove();
            }
        }
    }
}
