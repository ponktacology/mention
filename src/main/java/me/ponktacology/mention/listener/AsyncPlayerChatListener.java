package me.ponktacology.mention.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Locale;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        for (Player mentioned : Bukkit.getServer().getOnlinePlayers()) {
            if (player.equals(mentioned)) continue;

            if (message.toLowerCase(Locale.ROOT).contains(mentioned.getName().toLowerCase(Locale.ROOT))) {
                String[] args = event.getMessage().split("(?i)" + mentioned.getName());

                if (args.length == 0) {
                    event.setMessage(ChatColor.GREEN + mentioned.getName());
                    return;
                }

                StringBuilder formattedMessage = new StringBuilder(args[0]);
                String lastColor = ChatColor.getLastColors(args[0]);

                formattedMessage
                        .append(ChatColor.GREEN)
                        .append(mentioned.getName())
                        .append(ChatColor.RESET)
                        .append(lastColor);

                for (int x = 1; x < args.length; x++) {
                    formattedMessage.append(args[x]);
                }

                event.setMessage(formattedMessage.toString());
            }
        }
    }
}
