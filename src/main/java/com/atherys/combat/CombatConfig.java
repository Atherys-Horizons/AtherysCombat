package com.atherys.combat;

import com.atherys.combat.damage.AtherysDamageType;
import com.atherys.combat.damage.AtherysDamageTypes;
import com.atherys.core.utils.PluginConfig;
import ninja.leaping.configurate.objectmapping.Setting;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for retrieving what sort of damage type different item types ought to
 * be doing. The map itself is saved in a file config. See: {@link com.atherys.core.utils.PluginConfig}
 */
public class CombatConfig extends PluginConfig {

    @Setting("enabled")
    public boolean ENABLED = false;

    @Setting("damage_per_type")
    public Map<ItemType, AtherysDamageType> DAMAGE_MAP = new HashMap<>();
    {
        DAMAGE_MAP.put(ItemTypes.WOODEN_SWORD, AtherysDamageTypes.SLASH);
    }

    protected CombatConfig() throws IOException {
        super("config/" + AtherysCombat.ID, "config.conf");
    }
}
