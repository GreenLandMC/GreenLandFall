package me.GGGEDR.GreenLandFall.Commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class alert extends Command {


    public alert() {
        super("alert", "admin.alert");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0){
            sender.sendMessage(new TextComponent("§a§lProxy §8» §7Use: §a/alert <message>"));
        } else {
            String message = null;
            for (String word : args){
                if(message == null){
                    message = word;
                } else {
                    message = message + " " + word;
                }
                for(ProxiedPlayer proxiedPlayer : ProxyServer.getInstance().getPlayers()){
                    proxiedPlayer.sendTitle(ProxyServer.getInstance().createTitle().title(new TextComponent("§a§lALERT")));
                    proxiedPlayer.sendMessage(new TextComponent("§7§l(§c§l!§7§l) §a§lALERT §8» §c"+ ChatColor.translateAlternateColorCodes('&', message)));
                    if(proxiedPlayer.hasPermission("admin.alert")){
                        if(proxiedPlayer != (ProxiedPlayer) sender){
                            proxiedPlayer.sendMessage(new TextComponent("§a§lSpy §8» §7Hráč: §a"+ sender.getName() +" §7odoslal nový alert"));
                        }
                    }
                }
            }
        }
    }
}
