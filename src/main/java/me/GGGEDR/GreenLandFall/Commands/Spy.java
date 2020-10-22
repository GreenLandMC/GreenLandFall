package me.GGGEDR.GreenLandFall.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.List;

public class Spy extends Command {

    public static List<ProxiedPlayer> spyes = new ArrayList<>();

    public Spy() {
        super("spy", "admin.*");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(spyes.contains((ProxiedPlayer) sender)) {
            spyes.remove((ProxiedPlayer) sender);
            sender.sendMessage(new TextComponent("§a§lSpy §8» §7You removed from spyes§a!"));
        } else {
            spyes.add((ProxiedPlayer) sender);
            sender.sendMessage(new TextComponent("§a§lSpy §8» §7You added to spyes§a!"));
        }
    }
}
