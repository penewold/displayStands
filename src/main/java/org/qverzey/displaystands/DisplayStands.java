package org.qverzey.displaystands;

import org.bukkit.plugin.java.JavaPlugin;
import org.qverzey.displaystands.commands.DisplayStandCommand;
import org.qverzey.displaystands.commands.DisplayStandTabCompleter;

public final class DisplayStands extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("displaystand").setExecutor(new DisplayStandCommand());
        getCommand("displaystand").setTabCompleter(new DisplayStandTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
