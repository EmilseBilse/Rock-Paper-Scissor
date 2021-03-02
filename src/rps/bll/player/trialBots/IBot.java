package rps.bll.player.trialBots;

import rps.bll.game.Move;
import rps.bll.game.Result;

import java.util.ArrayList;
import java.util.List;

public interface IBot {

    public Move getMove();

    public String BotName();

    Move calculateMove(List<Result> results);
}
