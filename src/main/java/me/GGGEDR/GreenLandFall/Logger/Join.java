package me.GGGEDR.GreenLandFall.Logger;

import me.GGGEDR.GreenLandFall.Logger.LogFormater;
import me.GGGEDR.GreenLandFall.Logger.LoggerCache;
import me.GGGEDR.GreenLandFall.Main;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent e){
        File player_file = new File(Main.getInstance().getDataFolder() +"/logy/GGGEDR/");
        if(!player_file.exists()){
            player_file.mkdirs();
        }
        Logger logger = Logger.getLogger(e.getPlayer().getName());
        FileHandler fh;
        try {
            fh = new FileHandler(Main.getInstance().getDataFolder() +"/logy/GGGEDR/"+ e.getPlayer().getUniqueId() +".log");
            logger.addHandler(fh);
            fh.setFormatter(new LogFormater());
            logger.setUseParentHandlers(false);
            logger.info(e.getPlayer().getName() +" just signed in from ip: "+ e.getPlayer().getSocketAddress().toString());
            LoggerCache.cache.put(e.getPlayer(), logger);
        } catch (SecurityException | IOException ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onLeave(PlayerDisconnectEvent e){
        LoggerCache.cache.get(e.getPlayer()).getHandlers()[0].close();
        LoggerCache.cache.remove(e.getPlayer());
    }

    @EventHandler
    public void sendMessage(ChatEvent e){
        if(e.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) e.getSender();
            if(!e.getMessage().substring(0, 1).equalsIgnoreCase("/")){
                LoggerCache.cache.get(player).info(player.getName() + ": " + e.getMessage());
            }
        }
    }
}
