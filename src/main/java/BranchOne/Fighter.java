package BranchOne;

import net.eternalclient.api.accessors.*;
import net.eternalclient.api.data.widgets.WidgetInfo;
import net.eternalclient.api.events.EntityInteractEvent;
import net.eternalclient.api.events.WidgetEvent;
import net.eternalclient.api.frameworks.tree.Leaf;
import net.eternalclient.api.utilities.Log;
import net.eternalclient.api.utilities.MethodProvider;
import net.eternalclient.api.utilities.ReactionGenerator;
import net.eternalclient.api.wrappers.interactives.NPC;
import net.eternalclient.api.wrappers.widgets.WidgetChild;

public class Fighter extends Leaf {
    Enemies target = Enemies.getEnemy();
    private final AttackStyle attackStyle;

    public Fighter(AttackStyle attackStyle) {
        this.attackStyle = attackStyle;
    }

    public boolean Fighter(AttackStyle attackStyle) {
        WidgetInfo widgetInfo;

        switch (attackStyle) {
            default:
            case ACCURATE:
                widgetInfo = WidgetInfo.COMBAT_STYLE_ONE;
                break;
            case AGGRESSIVE:
                widgetInfo = WidgetInfo.COMBAT_STYLE_TWO;
                break;
            case CONTROLLED:
                widgetInfo = WidgetInfo.COMBAT_STYLE_THREE;
                break;
            case DEFENSIVE:
                widgetInfo = WidgetInfo.COMBAT_STYLE_FOUR;
                break;
        }

        WidgetChild widget = Widgets.getWidgetChild(widgetInfo.getGroupId(), widgetInfo.getChildId());
        return widget != null && (new WidgetEvent(widget).setEventCompleteCondition(() -> Combat.getAttackStyle().equals(attackStyle))).executed();
    }

    @Override
    public boolean isValid() {
        return LocalPlayer.get().isInArea(target.getTargetArea()) && Constants.getEquipment().isFulfilled();
    }

    @Override
    public int onLoop() {
        NPC goblins = NPCs.closest(g -> g.hasName(target.getName()) && g.hasAction("Attack") && g.canReach());
        if (!LocalPlayer.get().isInCombat() && !goblins.isDead()) {
            new EntityInteractEvent(goblins, "Attack")
                    .setEventCompleteCondition(() -> LocalPlayer.get().isInCombat(), 3000)
                    .execute();
            return ReactionGenerator.getPredictable();
        }
        if (Fighter(attackStyle)) {
            MethodProvider.tickSleep(1);
            if (attackStyle == Combat.getAttackStyle()) {
                Log.info("Successfully set " + attackStyle + " attack style.");
                return 0;
            } else {
                Log.info("Failed to set " + attackStyle + " attack style.");
                return 0;
            }
        }

        return 0;
    }
}

