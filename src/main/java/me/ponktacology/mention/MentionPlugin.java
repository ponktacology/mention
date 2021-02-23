package me.ponktacology.mention;

import me.ponktacology.mention.listener.AsyncPlayerChatListener;
import me.ponktacology.simpleconfig.config.ConfigFactory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MentionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new ConfigFactory(this.getClass());
        Bukkit.getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
    }
}
