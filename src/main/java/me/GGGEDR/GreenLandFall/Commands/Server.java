package me.GGGEDR.GreenLandFall.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Server extends Command {
    public Server() {
        super("server", "admin.*");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            if (args.length == 0) {
                sender.sendMessage(new TextComponent("§a§lProxy §8» §7Use: §a/server <name>"));
                String servers = null;
                for(ServerInfo server : ProxyServer.getInstance().getServersCopy().values()){
                    if(servers == null){
                        servers = "§a"+ server.getName();
                    } else {
                        servers = servers +"§7, §a"+ server.getName();
                    }
                }
                sender.sendMessage(new TextComponent("§a§lProxy §8» §7Servers: "+ servers));
            } else if(args.length == 1){
                if(ProxyServer.getInstance().getServerInfo(args[0]) != null){
                    sender.sendMessage(new TextComponent("§a§lProxy §8» §7Connecting..."));
                    ((ProxiedPlayer) sender).connect(ProxyServer.getInstance().getServerInfo(args[0]));
                } else {
                    sender.sendMessage(new TextComponent("§a§lProxy §8» §7This server not exist"));
                }
            } else {
                sender.sendMessage(new TextComponent("§a§lProxy §8» §7Use: §a/server <name>"));
                String servers = null;
                for(ServerInfo server : ProxyServer.getInstance().getServersCopy().values()){
                    if(servers == null){
                        servers = "§a"+ server.getName();
                    } else {
                        servers = servers +"§7, §a"+ server.getName();
                    }
                }
                sender.sendMessage(new TextComponent("§a§lProxy §8» §7Servers: "+ servers));
            }
        } else {
            sender.sendMessage(new TextComponent("§a§lProxy §8» §7This command is not for console§a!"));
        }
    }
}
