package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.player.trialBots.*;

//Java imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;
    private BotUtils botutils = new BotUtils();
    private BotManager bMan;

    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }


    @Override
    public String getPlayerName() {
        return name;
    }


    @Override
    public PlayerType getPlayerType() {
        return type;
    }


    /**
     * Decides the next move for the bot...
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();

        //initializes the bots and finds the bot that will be playing next round
        bMan = new BotManager(results);
        IBot currentBot = calculateBestBot(results);


        //currentBot = bMan.getAllBots().get(4);
        System.out.println("currentBot: "+ currentBot.BotName());

        return currentBot.getMove();
    }

    /**
    Calculates the best performing bot, by checking which bot would have had the highest win count, had it played the entire game
     @param results Contains the results from the game so far
     @return the best bot
     */
    public IBot calculateBestBot(List<Result> results){
        IBot bestBot = bMan.getAllBots().get(0);
        int highestCount = 0;
        for(IBot currentBot: bMan.getAllBots()) {
            int currentBotCount = botutils.getWinCount(results, currentBot);
            if( currentBotCount > highestCount) {
                bestBot = currentBot;
                highestCount = currentBotCount;
            }
        }
        return bestBot;
    }
}
