package fr.mizu.littlegameslib.misc.bossbar;

import fr.mizu.littlegameslib.LittleGamesLib;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class BossBar {

    private static HashMap<UUID, BossBar> bars = new HashMap<>();
    private EntityWither wither;
    private Player player;

    public BossBar (Player player, String text){
        Location loc = player.getLocation();
        WorldServer world = ((CraftWorld) loc.getWorld()).getHandle();

        EntityWither wither = new EntityWither(world);
        wither.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(),loc.getPitch());
        wither.setCustomName(text);
        wither.setInvisible(true);

        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(wither);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        this.wither = wither;
        this.player = player;
        bars.put(player.getUniqueId(), this);
    }

    public void setText(String text){
        wither.setCustomName(text);
        PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(wither.getId(), wither.getDataWatcher(), false);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public void destroy(){
        bars.remove(player.getUniqueId());
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(wither.getId());
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public void setProgress(float percent){
        wither.setHealth((percent*300)/100);
        PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(wither.getId(), wither.getDataWatcher(), false);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public void updateLocation(){
        Location newLoc = player.getEyeLocation().add(player.getEyeLocation().getDirection().normalize().multiply(30).add(new Vector(0, 15, 0)));
        wither.setLocation(newLoc.getX(), newLoc.getY(), newLoc.getZ(), newLoc.getYaw(), newLoc.getPitch());
        PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(wither);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static BossBar getBar(Player player){
        return bars.get(player.getUniqueId());
    }

    public static void initBars(){
        new BukkitRunnable() {
            @Override
            public void run() {
                for (BossBar bar : bars.values()){
                    bar.updateLocation();
                }
            }
        }.runTaskTimerAsynchronously(LittleGamesLib.Instance, 0 ,1);
    }
}
