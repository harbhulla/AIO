package BranchOne;

import net.eternalclient.api.accessors.LocalPlayer;
import net.eternalclient.api.frameworks.tree.Leaf;
import net.eternalclient.api.utilities.Log;
import net.eternalclient.api.utilities.ReactionGenerator;
import net.eternalclient.api.wrappers.walking.Walking;

import java.util.List;

public class Traversing extends Leaf {
    Enemies target = Enemies.getEnemy();
    @Override
    public boolean isValid() {
        return !LocalPlayer.get().isInArea(target.getTargetArea()) && Constants.getEquipment().isFulfilled();
    }

    @Override
    public int onLoop() {
        List<Enemies> usuableEnemies = Enemies.getUsable(); // Fetches enemies based on the player’s level
        if (!usuableEnemies.isEmpty()) {;// Chooses a random tree with the highest level
            if (target != null) {
                Walking.walk(target.getPosition()); // Walks to the selected tree’s position
                Log.info("Walking to " + target.getName() + " at " + target.getPosition());
                return ReactionGenerator.getPredictable();
            }
        } else {
            Log.info("No usable Enemies found for current level.");
        }

        return ReactionGenerator.getPredictable();
    }
    }
