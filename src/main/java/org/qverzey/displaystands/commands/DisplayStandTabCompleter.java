package org.qverzey.displaystands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class DisplayStandTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] strings) {
        Player player = (Player) sender;
        List<String> completions = new ArrayList<>();


        // Get the entity the player is looking at
        Entity targetEntity = getTargetEntity(player, 5); // 10 blocks maximum range

        if (targetEntity == null) {
            return completions;
        }
        if (targetEntity.getType() != EntityType.ARMOR_STAND) {
            return completions;
        }
        completions.add(String.valueOf(targetEntity.getUniqueId())); // Add the entity type to completions
        return completions;
    }
    public Entity getTargetEntity(Player player, int range) {
        Vector direction = player.getEyeLocation().getDirection();
        List<Entity> entities = player.getNearbyEntities(range, range, range);

        for (Entity entity : entities) {
            if (entity != player) {
                Vector toEntity = entity.getLocation().subtract(player.getEyeLocation()).toVector();
                if (direction.angle(toEntity) < 0.5) {
                    return entity;
                }
            }
        }
        return null;
    }
}
