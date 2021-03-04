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
        if(results.size() < 9) {
            return botUtils.getRandomMove();
        }
        else {
            ArrayList<Move> historyMoves = botUtils.getHumanMoves(results);
            String historyPattern = botUtils.movesToString(historyMoves);
            int historyPatternLength = historyPattern.length();
            String searchPattern = historyPattern.substring(historyPatternLength - 6);
            String historyPatternWithoutPattern = historyPattern.substring(0, historyPatternLength - 6);
            if (historyPatternWithoutPattern.contains(searchPattern)) {
                int patternPosition = historyPatternWithoutPattern.indexOf(searchPattern);
                Move expectedMove = botUtils.charToMove(historyPattern.charAt(patternPosition));
                if(expectedMove != null) {
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
