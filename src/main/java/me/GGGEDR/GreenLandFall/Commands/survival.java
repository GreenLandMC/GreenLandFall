package me.GGGEDR.GreenLandFall.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class survival extends Command {
    public survival() {
        super("survival");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        commandSender.sendMessage("§a§lProxy §8» §7Moving you to §aSurvival");
        ((ProxiedPlayer) commandSender).connect(ProxyServer.getInstance().getServerInfo("survival"));
    }
}
