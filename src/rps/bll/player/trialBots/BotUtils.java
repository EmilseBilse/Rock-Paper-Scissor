package rps.bll.player.trialBots;

import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.player.PlayerType;

import java.util.*;

public class BotUtils {


    public ArrayList<Move> getHumanMoves(List<Result> historicData) {
        ArrayList<Move> opponentMoves = new ArrayList<>();
        for (int i = 0; i < historicData.size(); i++) {
            if (historicData.get(i).getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
                opponentMoves.add(historicData.get(i).getLoserMove());
            } else if (historicData.get(i).getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
                opponentMoves.add(historicData.get(i).getWinnerMove());
            }
        }
        return opponentMoves;
    }

    public Move getHumanMove(Result result) {
        if (result.getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
            return result.getLoserMove();
        } else if (result.getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
            return result.getWinnerMove();
        }
        else return result.getLoserMove();
    }

    public ArrayList<Move> getAIMoves(List<Result> historicData) {
        ArrayList<Move> botMoves = new ArrayList<>();
        for (int i = 0; i < historicData.size(); i++) {
            if (historicData.get(i).getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
                botMoves.add(historicData.get(i).getWinnerMove());
            } else if (historicData.get(i).getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
                botMoves.add(historicData.get(i).getLoserMove());
            }
        }
        return botMoves;
    }

    public Move getAIMove(Result result) {
        if (result.getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
            return result.getWinnerMove();
        } else if (result.getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
            return result.getLoserMove();
        }
        else return result.getLoserMove();
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

    public ArrayList<Move> getWinningMoves(ArrayList<Move> movesToBeat) {
        ArrayList<Move> returnMoves = new ArrayList<>();
        for(Move move: movesToBeat) {
            returnMoves.add(getWinningMove(move));
        }
        return returnMoves;
    }

    public Move getLastMove(List<Move> list) {
        return list.get(list.size() - 1);
    }

    public Move getRandomMove() {
        Random random = new Random();
        Move returnMove;
        int i = random.nextInt(3) + 1;
        switch (i) {
            case 1:
                returnMove = Move.Rock;
                break;
            case 2:
                returnMove = Move.Scissor;
                break;
            default:
                returnMove = Move.Paper;
        }
        return returnMove;
    }

    public ArrayList<Move> interpretRotation(List<Move> list) {
        ArrayList<Move> returnMoves = null;
        if (list.size() >= 3) {
            returnMoves = new ArrayList<>();
            for (int i = 3; i >= 1; i--) {
                returnMoves.add(list.get(list.size() - i));
            }
        }
        return returnMoves;
    }

    public int getWinCount(List<Result> list, IBot bot) {
        int winCount=0;
            ArrayList<Result> botResults = new ArrayList<>();
        for (Result result : list) {
            Move botMove = bot.calculateMove(botResults);
            botResults.add(result);
            if (botMove.equals(getWinningMove(getHumanMove(result)))) {
                winCount++;
            }
        }
        return winCount;
    }
}
