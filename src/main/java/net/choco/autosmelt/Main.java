package net.choco.autosmelt;

import net.choco.autosmelt.listeners.BlockListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
    }
}
