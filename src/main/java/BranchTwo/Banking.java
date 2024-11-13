package BranchTwo;
import net.eternalclient.api.containers.Inventory;
import net.eternalclient.api.data.ItemID;
import net.eternalclient.api.events.ge.GrandExchangeEvent;
import net.eternalclient.api.events.ge.items.BuyItem;
import net.eternalclient.api.events.loadout.InventoryLoadout;
import net.eternalclient.api.events.loadout.WithdrawLoadoutEvent;
import net.eternalclient.api.frameworks.tree.Leaf;
import net.eternalclient.api.utilities.ReactionGenerator;
import net.eternalclient.api.utilities.container.OwnedItems;

public class Banking extends Leaf
{
    @Override
    public boolean isValid()
    {
        return !OwnedItems.contains(ItemID.KNIFE) || OwnedItems.count(ItemID.LOGS) < 10;
    }

    @Override
    public int onLoop()
    {
        if(OwnedItems.count(ItemID.LOGS) < 10) {
            new GrandExchangeEvent().addBuyItems(new BuyItem(ItemID.LOGS, Constants.NUMOFLOGS)).execute();
            new WithdrawLoadoutEvent(new InventoryLoadout().addReq(ItemID.LOGS, 27)).execute();
            return ReactionGenerator.getPredictable();
        }
        new WithdrawLoadoutEvent(Constants.INVENTORY_LOADOUT)
                .setBuyRemainder(true)
                .execute();
        return ReactionGenerator.getPredictable();
    }
}