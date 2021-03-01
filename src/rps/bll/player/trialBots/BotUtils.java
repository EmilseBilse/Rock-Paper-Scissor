package rps.bll.player.trialBots;

import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.player.PlayerType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BotUtils {

    public ArrayList<Move> getOpponentMoves(List<Result> historicData) {
        ArrayList<Move> opponentMoves = new ArrayList<>();
        for (int i = 0; i < historicData.size(); i++) {
            if (historicData.get(i).getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
                opponentMoves.add(historicData.get(i).getLoserMove());
            } else if(historicData.get(i).getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
                opponentMoves.add(historicData.get(i).getWinnerMove());
            }
        }
        return opponentMoves;
    }

    public Move getMostFrequentMove(List<Move> moveList) {
        int r = 0;
        int p = 0;
        int s = 0;
        for (Move move : moveList) {
            if (move.equals(Move.Rock)) {
                r++;
            } else if (move.equals(Move.Paper)) {
                p++;
            } else if (move.equals(Move.Scissor)) {
                s++;
            }
        }
        List<Integer> list = Arrays.asList(r, p, s);
        Collections.sort(list);
        int mostFrequentMove = list.get(2);
        if (mostFrequentMove == r) {
            return Move.Rock;
        } else if (mostFrequentMove == p) {
            return Move.Paper;
        } else {
            return Move.Scissor;
        }

    }

    public Move getWinningMove(Move opponentMove) {
        Move returnMove;
        switch (opponentMove) {
            case Rock:
                returnMove = Move.Paper;
                break;
            case Paper:
                returnMove = Move.Scissor;
                break;
            default:
                returnMove = Move.Rock;
        }
        return returnMove;
    }
}
