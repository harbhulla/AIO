package BranchOne;

import net.eternalclient.api.containers.Inventory;
import net.eternalclient.api.events.loadout.InventoryLoadout;
import net.eternalclient.api.events.loadout.WithdrawLoadoutEvent;
import net.eternalclient.api.frameworks.tree.Leaf;
import net.eternalclient.api.utilities.Log;
import net.eternalclient.api.utilities.ReactionGenerator;

public class Banking extends Leaf
{
    @Override
    public boolean isValid()
    {

        Log.info(String.valueOf((Inventory.isFull() || !Constants.getEquipment().isFulfilled())));
        return (Inventory.isFull() || !Constants.getEquipment().isFulfilled());
    }

    @Override
    public int onLoop()
    {
        new WithdrawLoadoutEvent(new InventoryLoadout().setLoadoutStrict(), Constants.getEquipment())
                .setBuyRemainder(true)
                .execute();
        return ReactionGenerator.getPredictable();
    }
}