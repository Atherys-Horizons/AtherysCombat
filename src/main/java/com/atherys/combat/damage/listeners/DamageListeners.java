package com.atherys.combat.damage.listeners;

import com.atherys.combat.AtherysCombat;
import com.atherys.combat.damage.sources.AtherysEntityDamageSource;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;

public class DamageListeners {

    @Listener(order = Order.FIRST)
    public void onSingleDamage(DamageEntityEvent event, @Root EntityDamageSource source) {
        if (event.getCause().containsType(AtherysEntityDamageSource.class)) {
            return;
        }

        event.setCancelled(true);

        EntityDamageSource newSource = AtherysCombat.getDamageService().getDamageSource(source);
        event.getTargetEntity().damage(event.getBaseDamage(), newSource);
    }

}
