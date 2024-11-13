package BranchOne;

import net.eternalclient.api.accessors.Skills;
import net.eternalclient.api.wrappers.map.RectArea;
import net.eternalclient.api.wrappers.map.WorldTile;
import net.eternalclient.api.wrappers.skill.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum Enemies {
    GOBLINS("Goblin",1,new WorldTile(3242, 3242,0),new RectArea(3238, 3257, 3266, 3212));

    private final String name;
    private final int levelRequirement;
    private final WorldTile position;
    private final RectArea targetArea;

    Enemies(String name, int levelRequirement, WorldTile worldtile, RectArea targetArea) {
        this.name = name;
        this.levelRequirement = levelRequirement;
        this.position = worldtile;
        this.targetArea = targetArea;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }
    public String getName() {
        return name;
    }
    public WorldTile getPosition() {
        return position;
    }
    public RectArea getTargetArea() {return targetArea;}

    public static List<Enemies> getUsable() {
        int level = Skills.getRealLevel(Skill.ATTACK);

        List<Enemies> enemy = new ArrayList<>();
        for (Enemies t : Enemies.values()) {
            if (t.levelRequirement <= level) {
                enemy.add(t);
            }
        }

        return enemy;
    }

    public static Enemies getEnemy() {
        List<Enemies> usableTrees = getUsable();

        if (usableTrees.isEmpty()) {
            return null;
        }

        int highestLevel = usableTrees.stream()
                .mapToInt(Enemies::getLevelRequirement)
                .max()
                .orElse(-1);

        List<Enemies> highestLevelTrees = usableTrees.stream()
                .filter(tree -> tree.getLevelRequirement() == highestLevel)
                .collect(Collectors.toList());
        return highestLevelTrees.get(highestLevelTrees.size() - 1);
    }
}
