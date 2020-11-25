package me.GGGEDR.GreenLandFall.Logger;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.logging.Logger;

public class LoggerCache {

    public static HashMap<ProxiedPlayer, Logger> cache = new HashMap<>();
}
