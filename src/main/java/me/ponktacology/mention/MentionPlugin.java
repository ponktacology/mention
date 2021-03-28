package me.ponktacology.mention;

import me.ponktacology.mention.listener.AsyncPlayerChatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MentionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
    }
}
