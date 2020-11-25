package me.GGGEDR.GreenLandFall.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class web extends Command {

    public web(){
        super("discord");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(new TextComponent("§a§lWeb §8» §7https://greenlandmc.eu"));
    }
}
