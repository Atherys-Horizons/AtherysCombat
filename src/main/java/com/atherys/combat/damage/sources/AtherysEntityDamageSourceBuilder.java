package com.atherys.combat.damage.sources;

import com.atherys.combat.damage.AtherysDamageType;
import org.spongepowered.api.event.cause.entity.damage.source.common.AbstractEntityDamageSource;

public class AtherysEntityDamageSourceBuilder extends
        AbstractEntityDamageSource.AbstractEntityDamageSourceBuilder<AtherysEntityDamageSource, AtherysEntityDamageSourceBuilder> {

    private AtherysDamageType type;

    @Override
    public AtherysEntityDamageSource build() throws IllegalStateException {
        return new AtherysEntityDamageSource(this, type);
    }

    public AtherysEntityDamageSourceBuilder atherysType(AtherysDamageType type) {
        this.type = type;
        return this;
    }
}
