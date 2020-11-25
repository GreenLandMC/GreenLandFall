package me.GGGEDR.GreenLandFall.Commands;

import me.GGGEDR.GreenLandFall.Logger.LoggerCache;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Msg extends Command {

    public Msg() {
        super("msg");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0){
            sender.sendMessage(new TextComponent("§a§lProxy §8» §7Use: §a/msg <player> <msg>"));
        } else if(args.length >= 2){
            if(ProxyServer.getInstance().getPlayer(args[0]) != null){
                if(!sender.getName().equalsIgnoreCase(args[0])){
                    String sprava = null;
                    for(int i = 1; i < args.length; i++){
                        if(sprava == null){
                            sprava = args[i];
                        } else {
                            sprava = sprava +" "+ args[i];
                        }
                    }
                    sender.sendMessage(new TextComponent("§2§lMsg §8» §7[§a§lYOU §8» §f"+ ProxyServer.getInstance().getPlayer(args[0]).getName() +"§7] §f"+ sprava));
                    ProxyServer.getInstance().getPlayer(args[0]).sendMessage(new TextComponent("§2§lMsg §8» §7[§f"+ sender.getName() +" §8» §a§lYOU§7] §f"+ sprava));
                    LoggerCache.cache.get((ProxiedPlayer) sender).info("[(Private) You -> "+ ProxyServer.getInstance().getPlayer(args[0]).getName() +"] » "+ sprava);
                    LoggerCache.cache.get(ProxyServer.getInstance().getPlayer(args[0])).info("[(Private) "+ sender.getName() +" -> You] » "+ sprava);
                    if(!Spy.spyes.isEmpty()){
                        for(ProxiedPlayer player : Spy.spyes){
                            if(ProxyServer.getInstance().getPlayer(player.getName()) != null){
                                if(player.hasPermission("msg.spy")) {
                                    player.sendMessage(new TextComponent("§a§lSpy §8» §7[§a"+ sender.getName() +" §8» §a"+ args[0] +"§7] §f"+ sprava));
                                }
                            }
                        }
                    }
                } else {
                    sender.sendMessage(new TextComponent("§a§lProxy §8» §7Cannot sent message to yourself§a!"));
                }
            } else {
                sender.sendMessage(new TextComponent("§a§lProxy §8» §7This player is offline§a!"));
            }
        }
    }
}
