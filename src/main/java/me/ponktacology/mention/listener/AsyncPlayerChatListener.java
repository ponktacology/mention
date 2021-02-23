package me.ponktacology.mention.listener;

import me.ponktacology.mention.helper.CoolDownHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Locale;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        for (Player mentioned : Bukkit.getServer().getOnlinePlayers()) {
            if(player.equals(mentioned)) continue;

            if (message.toLowerCase(Locale.ROOT).contains(mentioned.getName().toLowerCase(Locale.ROOT))) {

                if (!CoolDownHelper.isOnCoolDown(player, mentioned)) {
                    event.setMessage(event.getMessage().replaceFirst("(?i)" + mentioned.getName(), ChatColor.GREEN + mentioned.getName() + ChatColor.RESET));

                    CoolDownHelper.setOnCoolDown(player, mentioned);
                }
            }
        }
    }
}
