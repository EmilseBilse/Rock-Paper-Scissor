package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.player.trialBots.BotUtils;
import rps.bll.player.trialBots.Bot_BeatLastMove;
import rps.bll.player.trialBots.Bot_FrequencyCounting;
import rps.bll.player.trialBots.IBot;

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

    private ArrayList<IBot> bots = new ArrayList<>();

    private Bot_FrequencyCounting bot_Freq;
    private Bot_BeatLastMove bot_beatLast;

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

        bot_Freq = new Bot_FrequencyCounting(results);
        bot_beatLast = new Bot_BeatLastMove(results);
        bots.add(bot_Freq);
        bots.add(bot_beatLast);


        IBot currentBot = calculateBestBot(results);
        System.out.println(currentBot.BotName());

        //Implement better AI here...
        return currentBot.getMove();
    }

    //Ikke pisse smart lavet og jeg er ikke sikker på det virker
    public IBot calculateBestBot(List<Result> results){
        BotUtils botUtils = new BotUtils();
        ArrayList<Move> opponentMoves = botUtils.getOpponentMoves(results);

        //bot1 = Bot_Freq
        int bot1 = 0;
        //bot2 = Bot_BeatLast
        int bot2 = 0;
        for(int i =0; i< opponentMoves.size(); i++) {
                ArrayList<Result> currentResults =new ArrayList<>();
                for(int j = 0; j<i; j++) {
                    currentResults.add(results.get(i));
                }
                for(IBot currentBot: bots) {
                    currentBot.setResults(currentResults);
                    Move botMove = currentBot.getMove();
                    if(botMove == botUtils.getWinningMove(opponentMoves.get(i))) {
                        switch (currentBot.toString()) {
                            case "bot1":
                                bot1++;
                                break;
                            case "bot2":
                                bot2++;
                                break;
                        }
                    }
                }
        }
        List<Integer> bots = Arrays.asList(bot1, bot2);
        Collections.sort(bots);
        int bestBot = bots.get(bots.size()-1);
        IBot returnBot;
        if(bestBot == bot1) {
            returnBot = bot_Freq;
        }
        else {
            returnBot = bot_beatLast;
        }
        return returnBot;
    }
}
