package fr.mizu.littlegameslib.game;

import fr.mizu.littlegameslib.arena.ArenaSettings;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class GameArea {
    private Location loc1;
    private Location loc2;
    private String name;
    private boolean useSettings;

    /**
     * Define an area to make checking for certain events easier.
     * You can also define areas to have their own settings (Area.getSettings()).
     * Area settings will not take effect unless useSettings() is set to true.
     * Loc1 and loc2 will be re-written so that loc1 is always the "lowest" position, and loc2 to be the "highest"
     *
     * @param loc1 First corner of the area
     * @param loc2 Second corner of the area
     * @param name The name for this area
     */
    public GameArea(Location loc1, Location loc2, String name){
        double x1 = Math.min(loc1.getX(), loc2.getX());
        double y1 = Math.min(loc1.getY(), loc2.getY());
        double z1 = Math.min(loc1.getZ(), loc2.getZ());

        double x2 = Math.max(loc1.getX(), loc2.getX());
        double y2 = Math.max(loc1.getY(), loc2.getY());
        double z2 = Math.max(loc1.getZ(), loc2.getZ());

        this.loc1 = new Location(loc1.getWorld(), x1, y1, z1);
        this.loc2 = new Location(loc2.getWorld(), x2, y2, z2);
        this.name = name;
        this.useSettings = false;
    }

    public Location getCenter(){
        return getCenter(true);
    }
    public Location getCenter(boolean withY){
        double x = loc1.getX() + 0.5 + (loc2.getX() - loc1.getX()) / 2;
        double y = (withY ? loc1.getY() + (loc2.getY() - loc1.getY()) / 2 : loc1.getY());
        double z = loc1.getZ() + 0.5 + (loc2.getZ() - loc1.getZ()) / 2;
        return new Location(loc1.getWorld(), x, y, z);
    }


    public Location getLoc1() {
        return loc1;
    }

    public Location getLoc2() {
        return loc2;
    }

    public String getName() {
        return name;
    }
}
