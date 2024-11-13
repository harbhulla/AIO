package BranchTwo;

import net.eternalclient.api.accessors.*;
import net.eternalclient.api.containers.Inventory;
import net.eternalclient.api.data.ItemID;
import net.eternalclient.api.data.widgets.WidgetInfo;
import net.eternalclient.api.events.EntityInteractEvent;
import net.eternalclient.api.events.InventoryEvent;
import net.eternalclient.api.events.WidgetEvent;
import net.eternalclient.api.events.ge.GrandExchangeEvent;
import net.eternalclient.api.events.ge.items.BuyItem;
import net.eternalclient.api.events.ge.items.SellItem;
import net.eternalclient.api.events.loadout.InventoryLoadout;
import net.eternalclient.api.events.loadout.WithdrawLoadoutEvent;
import net.eternalclient.api.events.mouse.MouseInventoryInteractEvent;
import net.eternalclient.api.frameworks.tree.Leaf;
import net.eternalclient.api.utilities.Log;
import net.eternalclient.api.utilities.MethodProvider;
import net.eternalclient.api.utilities.ReactionGenerator;
import net.eternalclient.api.utilities.container.OwnedItems;
import net.eternalclient.api.wrappers.input.Keyboard;
import net.eternalclient.api.wrappers.interactives.NPC;
import net.eternalclient.api.wrappers.item.Item;
import net.eternalclient.api.wrappers.skill.Skill;
import net.eternalclient.api.wrappers.widgets.WidgetChild;

public class Fletching extends Leaf {
    InventoryLoadout inventoryLoadout = new InventoryLoadout();
    @Override
    public boolean isValid() {
        return OwnedItems.contains(ItemID.KNIFE) && OwnedItems.count(ItemID.LOGS) > 10;
    }

    @Override
    public int onLoop() {
       if(!Inventory.contains(ItemID.KNIFE)) {
           new WithdrawLoadoutEvent(inventoryLoadout.addReq(ItemID.KNIFE)).execute();
           return ReactionGenerator.getLowPredictable();
       }
       if(Inventory.contains(ItemID.LOGS) && Inventory.contains(ItemID.KNIFE) && !LocalPlayer.get().isAnimating()) {
           new InventoryEvent(ItemID.KNIFE)
                   .on(Inventory.get(ItemID.LOGS))
                   .execute();
           MethodProvider.tickSleep(2);
           Keyboard.pressSpace();
           MethodProvider.sleepUntil(() -> LocalPlayer.get().isAnimating(), 5000);
           return ReactionGenerator.getLowPredictable();
       }
       if(!Inventory.contains(ItemID.LOGS) && Inventory.contains(ItemID.KNIFE)) {
           new WithdrawLoadoutEvent(inventoryLoadout.addReq(ItemID.LOGS,27)).execute();
           return ReactionGenerator.getPredictable();
       }
       return ReactionGenerator.getPredictable();
    }
}

