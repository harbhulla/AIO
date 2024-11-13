package BranchOne;

import net.eternalclient.api.accessors.AttackStyle;
import net.eternalclient.api.accessors.Skills;
import net.eternalclient.api.data.ItemID;
import net.eternalclient.api.events.LogoutEvent;
import net.eternalclient.api.frameworks.tree.Branch;
import net.eternalclient.api.utilities.container.OwnedItems;
import net.eternalclient.api.wrappers.skill.Skill;

public class MainBranch extends Branch
{
    String[] styles = {String.valueOf(AttackStyle.ACCURATE), String.valueOf(AttackStyle.AGGRESSIVE), String.valueOf(AttackStyle.CONTROLLED), String.valueOf(AttackStyle.DEFENSIVE)};
    public MainBranch() {
        addLeafs( new Traversing(),
                new Fighter(AttackStyle.valueOf(styles[getIndex()])),
                new Banking()
        );
    }
    private int getIndex() {
        if(Skills.getRealLevel(Skill.ATTACK) < 20)
            return 0;
        if(Skills.getRealLevel(Skill.STRENGTH) < 30)
            return 1;
        if(Skills.getRealLevel(Skill.DEFENCE) < 25)
            return 3;
        return 2;
    }
    @Override
    public boolean isValid()
    {
        return Skills.getRealLevel(Skill.DEFENCE) < 45;
    }
}