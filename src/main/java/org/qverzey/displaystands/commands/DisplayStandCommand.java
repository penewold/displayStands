package org.qverzey.displaystands.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;

import java.util.UUID;

public class DisplayStandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Entity)) {
            commandSender.sendMessage("Sender isn't an entity");
            return true;
        }
        Entity sender = (Entity) commandSender;
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "No uuid provided");
            return true;
        }
        Entity argumentEntity = getEntityByUUID(UUID.fromString(args[0]));
        if (argumentEntity.getType() != EntityType.ARMOR_STAND) {
            sender.sendMessage(ChatColor.RED + "Entity not an Armor Stand");
            return true;
        } else {
            ArmorStand armorStand = (ArmorStand) argumentEntity;
            armorStand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
            armorStand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING);
            armorStand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.REMOVING_OR_CHANGING);
            armorStand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.REMOVING_OR_CHANGING);
            armorStand.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
            armorStand.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
            armorStand.setArms(true);
        }
        return true;
    }
    public Entity getEntityByUUID(UUID uniqueId){
        for (World world : Bukkit.getWorlds()) {
            for (Chunk chunk : world.getLoadedChunks()) {
                for (Entity entity : chunk.getEntities()) {
                    if (entity.getUniqueId().equals(uniqueId))
                        return entity;
                }
            }
        }

        return null;
    }
}
