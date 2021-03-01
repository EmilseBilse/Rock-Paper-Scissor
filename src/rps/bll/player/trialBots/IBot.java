package rps.bll.player.trialBots;

import rps.bll.game.Move;
import rps.bll.game.Result;

import java.util.ArrayList;

public interface IBot {
    public Move getMove();

    public void setResults(ArrayList<Result> historicData);

    public String BotName();
}
