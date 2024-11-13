package BranchOne;

import net.eternalclient.api.accessors.Skills;
import net.eternalclient.api.data.ItemID;
import net.eternalclient.api.events.loadout.EquipmentLoadout;
import net.eternalclient.api.wrappers.skill.Skill;

public class Constants {
    public static int attackLevel = Skills.getRealLevel(Skill.ATTACK);
    public static int strengthLevel = Skills.getRealLevel(Skill.STRENGTH);
    public static int defenceLevel = Skills.getRealLevel(Skill.DEFENCE);
    public static EquipmentLoadout getEquipment() {
        EquipmentLoadout equipmentLoadout = new EquipmentLoadout();
        if (attackLevel >= 1 && attackLevel < 20 && strengthLevel >= 1 && strengthLevel < 20 && defenceLevel >= 1 && defenceLevel < 20) {
            equipmentLoadout.addWeapon(ItemID.IRON_SCIMITAR)
                    .addShield(ItemID.IRON_KITESHIELD)
                    .addHat(ItemID.IRON_FULL_HELM)
                    .addChest(ItemID.IRON_PLATEBODY)
                    .addLegs(ItemID.IRON_PLATELEGS)
                    .addAmulet(ItemID.AMULET_OF_POWER)
                    .addCape(ItemID.BLACK_CAPE)
                    .addFeet(ItemID.LEATHER_BOOTS);
            return equipmentLoadout;
        }
        if(attackLevel >= 20 && strengthLevel >= 20 && defenceLevel >= 20){
            equipmentLoadout.addWeapon(ItemID.MITHRIL_SCIMITAR)
                    .addShield(ItemID.MITHRIL_KITESHIELD)
                    .addHat(ItemID.MITHRIL_FULL_HELM)
                    .addChest(ItemID.MITHRIL_PLATEBODY)
                    .addLegs(ItemID.MITHRIL_PLATELEGS)
                    .addAmulet(ItemID.AMULET_OF_POWER)
                    .addCape(ItemID.BLACK_CAPE)
                    .addFeet(ItemID.LEATHER_BOOTS);
            return equipmentLoadout;
        }
        return equipmentLoadout;
    }
}
