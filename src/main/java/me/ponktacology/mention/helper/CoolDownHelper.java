package me.ponktacology.mention.helper;

import me.ponktacology.simpleconfig.config.annotation.Configurable;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CoolDownHelper {

    @Configurable(path = "cooldown")
    public static long COOL_DOWN_TIME = 5000L;

    private static final Map<UUID, Map<UUID, Long>> coolDowns = new ConcurrentHashMap<>();

    public static boolean isOnCoolDown(Player player, Player mentioned) {
        if (!coolDowns.containsKey(player.getUniqueId())) return false;

        Map<UUID, Long> playerCoolDowns = coolDowns.get(player.getUniqueId());

        if (!playerCoolDowns.containsKey(mentioned.getUniqueId())) return false;

        Long lastMention = playerCoolDowns.get(mentioned.getUniqueId());

        return System.currentTimeMillis() - lastMention < COOL_DOWN_TIME;
    }

    public static void setOnCoolDown(Player player, Player mentioned) {
        if (!coolDowns.containsKey(player.getUniqueId())) {
            coolDowns.put(player.getUniqueId(), new ConcurrentHashMap<>());
        }

        Map<UUID, Long> playerCoolDowns = coolDowns.get(player.getUniqueId());

        playerCoolDowns.put(mentioned.getUniqueId(), System.currentTimeMillis());
    }
}
