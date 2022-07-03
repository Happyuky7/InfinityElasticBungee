package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee;

/*
 * GitHub: https://github.com/HappyRogelio7/InfinityElasticBungee
 * Author: HappyRogelio7
 * License: Custom
 * License Link: https://github.com/HappyRogelio7/InfinityElasticBungee/blob/master/LICENSE
 */
// JAVA IMPORTS
import java.io.File;
import java.util.logging.Level;

// COMMANDS IMPORT
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands.ExecuteGlobalProxyCMDCommand;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands.GAlertCommand;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.commands.InfinityElasticBungeeCommand;

// REDIS IMPORT
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.redis.RedisMessageBroker;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.redis.RedisStorage;

// SYNC IMPORTS
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.sync.BroadcastSync;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.sync.KickSync;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.sync.OnlineCountSync;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.sync.PlayerSync;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.sync.SendAllSync;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.sync.SendSync;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.sync.ExecuteGlobalProxyCMDSync;

// UTILS IMPORTS
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.utils.ConfigUtils;
import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.utils.StringUtils;

// BUNGEE IMPORTS
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

public final class InfinityElasticBungee extends Plugin {

    private Configuration mainConfig;

    private boolean debug;
    private String serverID;

    private RedisMessageBroker broker;
    private RedisStorage storage;

    // START InfinityElasticBunge

    private ExecuteGlobalProxyCMDSync executeGlobalProxyCMDSync;

    // END InfinityElasticBunge

    private BroadcastSync broadcastSync;
    private KickSync kickSync;
    private OnlineCountSync onlineCountSync;
    private PlayerSync playerSync;
    private SendAllSync sendAllSync;
    private SendSync sendSync;

    public void debug(final String message) {
        if (this.debug) {
            this.getLogger().log(Level.INFO, "§d[DEBUG] §r" + message);
        }
    }

    private void init() throws Exception {
        final File configFile = new File(this.getDataFolder(), "config.yml");
        this.mainConfig = ConfigUtils.getConfig(configFile);

        // Setup server
        this.debug = this.mainConfig.getBoolean("debug");
        this.serverID = this.mainConfig.getString("server.id", "bungee-" + StringUtils.randomString(6));
        this.getLogger().log(Level.INFO, "Defined server id as " + this.serverID);

        // Setup message broker and storage
        final String redisHost = this.mainConfig.getString("redis.host");
        final int redisPort = this.mainConfig.getInt("redis.port");
        final String redisPassword = this.mainConfig.getString("redis.password");

        this.broker = new RedisMessageBroker(redisHost, redisPort, redisPassword);
        this.getLogger().log(Level.INFO, "Connected to redis Message broker");

        this.storage = new RedisStorage(redisHost, redisPort, redisPassword);
        this.getLogger().log(Level.INFO, "Connected to redis Storage Container");

        // Start InfinityElasticBunge Setup sync modules.

        this.executeGlobalProxyCMDSync = new ExecuteGlobalProxyCMDSync(this);
        this.broker.subscribe(executeGlobalProxyCMDSync);
        this.getLogger().log(Level.INFO, "Registered module ExecuteGlobalProxyCMDSync");

        // End InfinityElasticBungee Setup sync modules.

        // Setup sync modules
        this.broadcastSync = new BroadcastSync(this);
        this.broker.subscribe(broadcastSync);
        this.getLogger().log(Level.INFO, "Registered module BroadcastSync");

        this.kickSync = new KickSync(this);
        this.broker.subscribe(kickSync);
        this.getLogger().log(Level.INFO, "Registered module KickSync");

        this.onlineCountSync = new OnlineCountSync(this);
        this.broker.subscribe(onlineCountSync);
        this.getProxy().getPluginManager().registerListener(this, onlineCountSync);
        this.getLogger().log(Level.INFO, "Registered module OnlineCountSync");

        this.playerSync = new PlayerSync(this);
        this.getProxy().getPluginManager().registerListener(this, playerSync);
        this.getLogger().log(Level.INFO, "Registered module PlayerSync");

        this.sendAllSync = new SendAllSync(this);
        this.sendSync = new SendSync(this);
        this.broker.subscribe(sendAllSync);
        this.broker.subscribe(sendSync);
        this.getLogger().log(Level.INFO, "Registered module Send/AllSync");

        // Register commands
        this.getProxy().getPluginManager().registerCommand(this, new InfinityElasticBungeeCommand(this));

        // Start InfinityElasticBunge
        this.getProxy().getPluginManager().registerCommand(this, new ExecuteGlobalProxyCMDCommand(this));
        this.getProxy().getPluginManager().registerCommand(this, new GAlertCommand(this));
        // End InfinityElasticBunge


        this.getLogger().log(Level.INFO, "Registered InfinityElasticBungee commands");

        // Initialize if there are players online
        this.playerSync.sync();

        // Save config after initialize
        ConfigUtils.saveConfig(this.mainConfig, configFile);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        try {
            this.init();
        } catch (final Exception e) {
            this.getLogger().log(Level.SEVERE,
                    "InfinityElasticBunge had an unexpected error and the proxy will be closed to prevent security breaches.");
            e.printStackTrace();
            this.getProxy().stop();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.playerSync.cleanup();
    }


    public Configuration getConfig() {
        return this.mainConfig;
    }

    //START CUSTOM

    public ExecuteGlobalProxyCMDSync getExecuteGlobalProxyCMDSync() {
        return this.executeGlobalProxyCMDSync;
    }


    public BroadcastSync getBroadcastSync() {
        return this.broadcastSync;
    }

    public KickSync getKickSync() {
        return this.kickSync;
    }

    public RedisMessageBroker getMessageBroker() {
        return this.broker;
    }

    public RedisStorage getStorage() {
        return this.storage;
    }

    public OnlineCountSync getOnlineCountSync() {
        return this.onlineCountSync;
    }

    public PlayerSync getPlayerSync() {
        return this.playerSync;
    }

    public SendAllSync getSendAllSync() {
        return this.sendAllSync;
    }

    public SendSync getSendSync() {
        return this.sendSync;
    }

    public String getServerID() {
        return this.serverID;
    }

    /* Static Values */
    private static InfinityElasticBungee instance = null;

    public static InfinityElasticBungee getInstance() {
        return instance;
    }
}
