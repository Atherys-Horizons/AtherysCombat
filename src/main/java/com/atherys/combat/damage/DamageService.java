package com.atherys.combat.damage;

import com.atherys.combat.AtherysCombat;
import com.atherys.combat.damage.sources.AtherysDamageSources;
import com.atherys.core.utils.InventoryUtils;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.cause.entity.damage.source.IndirectEntityDamageSource;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.Optional;

public class DamageService {

    private static DamageService instance = new DamageService();

    private DamageService() {}

    public AtherysDamageType getItemDamageType(ItemType type) {
        return AtherysCombat.getConfig().DAMAGE_MAP.getOrDefault(type, AtherysDamageTypes.UNARMED);
    }

    public AtherysDamageType getDamageType(EntityDamageSource originalSource) {
        Entity source;

        if (originalSource instanceof IndirectEntityDamageSource) {
            source = ((IndirectEntityDamageSource) originalSource).getIndirectSource();
        } else {
            source = originalSource.getSource();
        }

        Optional<ItemStack> stack = InventoryUtils.getMainHand(source);
        if (stack.isPresent()) {
            return getItemDamageType(stack.get().getType());
        } else {
            return AtherysDamageTypes.UNARMED;
        }
    }

    public EntityDamageSource getDamageSource(EntityDamageSource source) {
        AtherysDamageType type = getDamageType(source);

        if (source instanceof IndirectEntityDamageSource) {
            Entity entitySource = ((IndirectEntityDamageSource) source).getIndirectSource();

            Entity projectile = source.getSource();
            projectile.remove();

            return AtherysDamageSources.ranged(type, entitySource).build();
        } else {
            Entity entitySource = source.getSource();

            return AtherysDamageSources.melee(type, entitySource).build();
        }

    }

    public static DamageService getInstance() {
        return instance;
    }

}
