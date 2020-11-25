package me.GGGEDR.GreenLandFall.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class discord extends Command {

    public discord(){
        super("discord");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(new TextComponent("§a§lDiscord §8» §7https://discord.gg/6efyEkGYED"));
    }
}
