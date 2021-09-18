package org.lumenk.onsky;

import listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import utils.TextUtil;

public final class Onsky extends JavaPlugin {

    private static Onsky instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Bukkit.getServer().getLogger().warning(TextUtil.toColor("&bHello &fServer!"));
        Bukkit.getServer().getLogger().info("OnSky가 활성화되었습니다");

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Onsky getInstance() {
        return instance;
    }
}
