package rps.bll.player.trialBots;

import rps.bll.game.Result;

import java.util.ArrayList;
import java.util.List;

public class BotManager {


    private List<IBot> allBots = new ArrayList<>();

    public BotManager(ArrayList<Result> results) { ;
        Bot_RotationPredicting botRot = new Bot_RotationPredicting(results);
        allBots.add(botRot);
        Bot_BeatLastMove botLast = new Bot_BeatLastMove(results);
        allBots.add(botLast);
        Bot_FrequencyCounting botFreq = new Bot_FrequencyCounting(results);
        allBots.add(botFreq);
        Bot_AntiRotationPredicting botAntiRot = new Bot_AntiRotationPredicting(results);
        allBots.add(botAntiRot);

    }

    public List<IBot> getAllBots() {
        return allBots;
    }

}
