package rps.bll.player.trialBots;

import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.player.PlayerType;

import java.util.*;

public class BotUtils {

    /**
     * finds the human made moves in the list
     * @param results
     * @return the human made moves
     */
    public ArrayList<Move> getHumanMoves(List<Result> results) {
        ArrayList<Move> opponentMoves = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
                opponentMoves.add(results.get(i).getLoserMove());
            } else if (results.get(i).getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
                opponentMoves.add(results.get(i).getWinnerMove());
            }
        }
        return opponentMoves;
    }

    /**
     * finds the human made move in the given result
     * @param result
     * @return the human move
     */
    public Move getHumanMove(Result result) {
        if (result.getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
            return result.getLoserMove();
        } else if (result.getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
            return result.getWinnerMove();
        }
        else return result.getLoserMove();
    }

    /**
     * finds the AI made moves in the list
     * @param results
     * @return the AI moves
     */
    public ArrayList<Move> getAIMoves(List<Result> results) {
        ArrayList<Move> botMoves = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
                botMoves.add(results.get(i).getWinnerMove());
            } else if (results.get(i).getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
                botMoves.add(results.get(i).getLoserMove());
            }
        }
        return botMoves;
    }

    /**
     * finds the AI made move in the result
     * @param result
     * @return the Ai made move
     */
    public Move getAIMove(Result result) {
        if (result.getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
            return result.getWinnerMove();
        } else if (result.getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
            return result.getLoserMove();
        }
        else return result.getLoserMove();
    }

    /**
     * calculates the most frequently made move in the list
     * @param moveList
     * @return the most frequently made move
     */
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

    /**
     * calculates which move would beat the given move
     * @param opponentMove
     * @return the winning move
     */
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

    /**
     * calculates which move would beat the given move for every move in the given list
     * @param movesToBeat
     * @return the winning moves
     */
    public ArrayList<Move> getWinningMoves(ArrayList<Move> movesToBeat) {
        ArrayList<Move> returnMoves = new ArrayList<>();
        for(Move move: movesToBeat) {
            returnMoves.add(getWinningMove(move));
        }
        return returnMoves;
    }

    /**
     * gets the most recent move in the given list
     * @param list
     * @return
     */
    public Move getLastMove(List<Move> list) {
        return list.get(list.size() - 1);
    }

    /**
     * returns a Random move
     * @return a random move
     */
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

    /**
     * returns the last three moves in the list as a string
     * @param list
     * @return returnmoves
     */
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

    /**
     * calculates how much the bot would have won given the opponent moves in the list
     * @param list
     * @param bot
     * @return the bots win count
     */
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

    /**
     * converts the moves in a list to a string, where each char represents a move
     * @param moves
     * @return string
     */
    public String movesToString (List<Move> moves) {
        StringBuilder returnString = new StringBuilder();
        for (Move currentMove : moves) {
            switch (currentMove) {
                case Rock -> returnString.append("r");
                case Paper -> returnString.append("p");
                case Scissor -> returnString.append("s");
            }
        }
        return returnString.toString();
    }

    /**
     * converts the given char c into the belonging move
     * @param c
     * @return
     */
    public Move charToMove (char c) {
        return switch (String.valueOf(c)) {
            case "r" -> Move.Rock;
            case "s" -> Move.Scissor;
            case "p" -> Move.Paper;
            default -> null;
        };
    }
}
