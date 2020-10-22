package me.GGGEDR.GreenLandFall.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Lobby extends Command {
    public Lobby() {
        super("lobby");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){
            if(!((ProxiedPlayer) sender).getServer().getInfo().getName().equalsIgnoreCase("lobby")) {
                ((ProxiedPlayer) sender).connect(ProxyServer.getInstance().getServerInfo("lobby"));
            } else {
                sender.sendMessage(new TextComponent("§a§lProxy §8» §7You are already connected to this server§a!"));
            }
        }
    }
}
