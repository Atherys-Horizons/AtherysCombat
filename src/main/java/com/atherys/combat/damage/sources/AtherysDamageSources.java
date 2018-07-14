package com.atherys.combat.damage.sources;

import com.atherys.combat.damage.AtherysDamageType;
import org.spongepowered.api.entity.Entity;

public class AtherysDamageSources {

    public static AtherysEntityDamageSourceBuilder melee(AtherysDamageType type, Entity source) {
        return AtherysEntityDamageSource.builder().type(type.getPrimitive()).atherysType(type)
                .entity(source);
    }

    public static AtherysEntityDamageSourceBuilder ranged(AtherysDamageType type, Entity source) {
        return AtherysEntityDamageSource.builder().type(type.getPrimitive()).atherysType(type)
                .entity(source);
    }

    public static AtherysEntityDamageSourceBuilder magic(AtherysDamageType type, Entity source) {
        return AtherysEntityDamageSource.builder().type(type.getPrimitive()).atherysType(type)
                .entity(source).magical();
    }

}
