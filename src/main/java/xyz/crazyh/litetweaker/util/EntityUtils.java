package xyz.crazyh.litetweaker.util;

import fi.dy.masa.malilib.config.value.BlackWhiteList;
import fi.dy.masa.malilib.util.restriction.UsageRestriction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import xyz.crazyh.litetweaker.config.Configs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EntityUtils {
    public static boolean checkEntityBlackList(String name) {
        String[] s = name.split("\\.");

        return Configs.Generic.ENTITY_HIT_LIST.getValuesAsString().contains(s[s.length-1]);
    }

    //not used yet
    public static final UsageRestriction<Class <? extends Entity>> entityHitRestriction = new UsageRestriction<>();

    public static boolean checkEntityBlackList(Class <? extends Entity> entity) {
        return !entityHitRestriction.isAllowed(entity);
    }

    public static void updateEntityHitRestriction(BlackWhiteList<Class <? extends Entity>> list) {
        entityHitRestriction.setListContents(list);
    }

    public static String getEntityRegistryName(Class <? extends Entity > entity) {
        try {
            return EntityList.REGISTRY.getNameForObject(entity).toString();
        } catch (Exception e) {
            return "?";
        }
    }

    public static Class <? extends Entity > getEntityByRegistryName(String name) {
        try {
            return EntityList.REGISTRY.getObject(new ResourceLocation(name));
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Class <? extends Entity>> getEntityList() {
        List<Class <? extends Entity>> entityList = new ArrayList<>();

        for (Class <? extends Entity> entity : EntityList.REGISTRY) {
            entityList.add(entity);
        }

        entityList.sort(Comparator.comparing(EntityUtils::getEntityRegistryName));
        return entityList;
    }
}
