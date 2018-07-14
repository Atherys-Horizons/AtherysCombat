package com.atherys.combat;

import com.atherys.combat.damage.AtherysDamageType;
import com.atherys.combat.damage.AtherysDamageTypeRegistry;
import com.atherys.combat.damage.AtherysDamageTypes;
import com.atherys.combat.damage.DamageService;
import com.atherys.combat.damage.listeners.DamageListeners;
import com.atherys.core.command.CommandService;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import javax.inject.Inject;
import java.io.IOException;

import static com.atherys.combat.AtherysCombat.*;

@Plugin(id = ID, version = VERSION, name = NAME, description = DESCRIPTION, dependencies = {
        @Dependency(id = "atheryscode")
})
public class AtherysCombat {

    public static final String ID = "atheryscombat";
    public static final String NAME = "A'therys Combat";
    public static final String DESCRIPTION = "A damage-modifying plugin for the A'therys Horizons server";
    public static final String VERSION = "1.0.0a";

    private static AtherysCombat instance;

    private static boolean init = false;

    private static CombatConfig config;

    private DamageService damageService;

    @Inject
    private Logger logger;

    @Inject
    PluginContainer container;

    private void init() {
        instance = this;

        // initialize the static constructor...
        getLogger().info(AtherysDamageTypes.ARCANE.getName());

        Sponge.getRegistry().registerModule(AtherysDamageType.class, AtherysDamageTypeRegistry.getInstance());

        try {
            config = new CombatConfig();
            config.init();
        } catch (IOException e) {
            e.printStackTrace();
            init = false;
            return;
        }

        damageService = DamageService.getInstance();

        init = true;

    }

    private void start() {
        Sponge.getEventManager().registerListeners(this, new DamageListeners());
    }

    private void stop() {
    }

    @Listener(order = Order.EARLY)
    public void onInit(GameInitializationEvent event) {
        init();
    }

    @Listener
    public void onStart(GameStartingServerEvent event) {
        if (init) {
            start();
        }
    }

    @Listener
    public void onStop(GameStoppingServerEvent event) {
        if (init) {
            stop();
        }
    }

    public static CommandService getCommandService() {
        return CommandService.getInstance();
    }

    public Logger getLogger() {
        return logger;
    }

    public static DamageService getDamageService() {
        return getInstance().damageService;
    }

    public static AtherysCombat getInstance() {
        return instance;
    }

    public static CombatConfig getConfig() {
        return config;
    }
}
