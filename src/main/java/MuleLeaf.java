import net.eternalclient.api.containers.Inventory;
import net.eternalclient.api.containers.bank.Bank;
import net.eternalclient.api.data.ItemID;
import net.eternalclient.api.events.ge.GrandExchangeEvent;
import net.eternalclient.api.events.ge.items.SellItem;
import net.eternalclient.api.events.loadout.InventoryLoadout;
import net.eternalclient.api.events.loadout.WithdrawLoadoutEvent;
import net.eternalclient.api.events.muling.MuleRequestEvent;
import net.eternalclient.api.events.muling.OfferedItem;
import net.eternalclient.api.events.muling.RequiredItem;
import net.eternalclient.api.frameworks.tree.Leaf;
import net.eternalclient.api.utilities.MethodProvider;
import net.eternalclient.api.utilities.ReactionGenerator;
import net.eternalclient.api.utilities.container.OwnedItems;

public class MuleLeaf extends Leaf {
    @Override
    public boolean isValid() {
        return   OwnedItems.count(ItemID.COINS_995) >= 500000
                || OwnedItems.count(ItemID.COINS_995) < 10000;
    }

    @Override
    public int onLoop() {
        if (OwnedItems.count(ItemID.COINS_995) < 10000) {
            new MuleRequestEvent().addRequiredItem(new RequiredItem(ItemID.COINS_995, 10000)).execute();
            return ReactionGenerator.getPredictable();
        }
        if(OwnedItems.count(ItemID.COINS_995) >= 500000) {
            new MuleRequestEvent().addOfferedItem(new OfferedItem(ItemID.COINS_995,Bank.count(ItemID.COINS_995))).execute();
        }
        return ReactionGenerator.getPredictable();
    }
}