package BranchTwo;

import BranchTwo.Banking;
import BranchTwo.Fletching;
import net.eternalclient.api.accessors.AttackStyle;
import net.eternalclient.api.data.ItemID;
import net.eternalclient.api.frameworks.tree.Branch;
import net.eternalclient.api.utilities.container.OwnedItems;

public class BranchTwo extends Branch
{
    public BranchTwo() {
        addLeafs(
                new Banking(),
                new Fletching()
        );
    }
    @Override
    public boolean isValid()
    {
        return false;
    }
}