package me.ngcsonsplash.messengerintegration.death;

import me.ngcsonsplash.messengerintegration.death.environment.*;
import me.ngcsonsplash.messengerintegration.death.mob.*;
import me.ngcsonsplash.messengerintegration.death.pvp.PvPDeathMessages;
import me.ngcsonsplash.messengerintegration.death.environment.SuicideDeathMessages;

import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathMessageUtil {

    public static String generate(PlayerDeathEvent event) {

        Player victim = event.getEntity();

        if (victim == null) {
            return null;
        }

        EntityDamageEvent lastDamage = victim.getLastDamageCause();
        EntityDamageEvent.DamageCause cause =
                (lastDamage != null) ? lastDamage.getCause() : null;

        Player killer = victim.getKiller();

        // 1️⃣ PvP
        if (killer != null) {
            return PvPDeathMessages.get(victim.getName(), killer.getName());
        }

        // 2️⃣ Mob kill
        if (lastDamage instanceof org.bukkit.event.entity.EntityDamageByEntityEvent damageByEntity) {

            Entity damager = damageByEntity.getDamager();

            if (damager instanceof Creeper) {
                return CreeperDeathMessages.get(victim.getName());
            }

            if (damager instanceof Monster ||
                damager instanceof Mob ||
                damager instanceof Slime ||
                damager instanceof Ghast ||
                damager instanceof Phantom) {

                return MobDeathMessages.get(victim.getName());
            }

            if (damager instanceof TNTPrimed ||
                damager instanceof Fireball) {

                return ExplosionDeathMessages.get(victim.getName());
            }
        }

        // 3️⃣ Environment
        if (cause != null) {

            switch (cause) {

                case FALL:
                    return FallDeathMessages.get(victim.getName());

                case FIRE:
                case FIRE_TICK:
                case LAVA:
                case HOT_FLOOR:
                    return FireDeathMessages.get(victim.getName());

                case DROWNING:
                    return DrownDeathMessages.get(victim.getName());

                case BLOCK_EXPLOSION:
                case ENTITY_EXPLOSION:
                    return ExplosionDeathMessages.get(victim.getName());

                case LIGHTNING:
                    return LightningDeathMessages.get(victim.getName());

                case VOID:
                case SUICIDE:
                    return SuicideDeathMessages.get(victim.getName());

                default:
                    return SuicideDeathMessages.get(victim.getName());
            }
        }

        return SuicideDeathMessages.get(victim.getName());
    }
}
