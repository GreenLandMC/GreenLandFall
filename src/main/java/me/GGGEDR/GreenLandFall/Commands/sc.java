package me.GGGEDR.GreenLandFall.Commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class sc extends Command {

    public sc() {
        super("staffchat", "admin.*");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length >= 1){
            String sprava = null;
            for(int i = 0; i < args.length; i++){
                if(sprava == null){
                    sprava = args[i];
                } else {
                    sprava = sprava +" "+ args[i];
                }
            }
            for(ProxiedPlayer p : ProxyServer.getInstance().getPlayers()){
                if(p.hasPermission("staffchat.*")){
                    p.sendMessage(new TextComponent("§2§lStaffChat §a"+ sender.getName() +" §8» §f"+ ChatColor.translateAlternateColorCodes('&', sprava)));
                }
            }
        } else {
            sender.sendMessage(new TextComponent("§a§lProxy §8» §7Use: §a/staffchat <msg>"));
        }
    }
}
