package com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.api.infinityelasticbungeeapi;

import com.github.kaorystudios.mc.happyrogelio7.infinityelasticbungee.InfinityElasticBungee;

public class InfinityElasticBungeeAPI {

    private final InfinityElasticBungee plugin;

    private static InfinityElasticBungeeAPI api;

    public InfinityElasticBungeeAPI(final InfinityElasticBungee plugin) {
        this.plugin = plugin;
        api = this;
    }

    public static InfinityElasticBungeeAPI getAPI() {
        return api;
    }

    /* Sync module methods */

    public void getExecuteGlobalProxyCMD(final String command) {
        this.plugin.getExecuteGlobalProxyCMDSync().sendExecuteGlobalProxyCMDSync(command);
    }




}
