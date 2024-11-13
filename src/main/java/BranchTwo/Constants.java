package BranchTwo;

import net.eternalclient.api.accessors.Skills;
import net.eternalclient.api.data.ItemID;
import net.eternalclient.api.events.loadout.EquipmentLoadout;
import net.eternalclient.api.events.loadout.InventoryLoadout;
import net.eternalclient.api.wrappers.skill.Skill;

public class Constants {
    public static final InventoryLoadout INVENTORY_LOADOUT = new InventoryLoadout().addReq(ItemID.KNIFE);
    public static final int NUMOFLOGS = 2000;
}
