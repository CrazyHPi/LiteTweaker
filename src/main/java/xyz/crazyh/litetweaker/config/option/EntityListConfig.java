package xyz.crazyh.litetweaker.config.option;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.option.list.ValueListConfig;
import fi.dy.masa.malilib.config.value.BlackWhiteList;
import fi.dy.masa.malilib.util.restriction.UsageRestriction;
import net.minecraft.entity.Entity;
import xyz.crazyh.litetweaker.util.EntityUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class EntityListConfig extends ValueListConfig<Class <? extends Entity>> {
    public EntityListConfig(String name, ImmutableList<Class <? extends Entity>> defaultValues,
                            Function<Class <? extends Entity>, String> toStringConverter,
                            Function<String, Class <? extends Entity>> fromStringConverter) {
        super(name, defaultValues, toStringConverter, fromStringConverter);
    }

    public EntityListConfig(String name, ImmutableList<Class <? extends Entity>> defaultValues, String comment,
                            Function<Class <? extends Entity>, String> toStringConverter,
                            Function<String, Class <? extends Entity>>  fromStringConverter) {
        super(name, defaultValues, toStringConverter, fromStringConverter, comment);
    }

    @Override
    public EntityListConfig copy() {
        EntityListConfig config = new EntityListConfig(this.name, this.defaultValue, this.toStringConverter, this.fromStringConverter);
        config.copyValuesFrom(this);
        return config;
    }

    public static EntityListConfig fromNames(String cfgName, String... entityNames) {
        return fromNames(cfgName, Arrays.asList(entityNames));
    }

    public static EntityListConfig fromNames(String cfgName, List<String> entityNames)
    {
        ImmutableList.Builder<Class <? extends Entity>> builder = ImmutableList.builder();

        for (String name : entityNames)
        {
            Class <? extends Entity> entity = EntityUtils.getEntityByRegistryName(name);

            if (entity != null)
            {
                builder.add(entity);
            }
        }

        return create(cfgName, builder.build());
    }

    public static EntityListConfig create(String cfgName, ImmutableList<Class <? extends Entity>> entities) {
        return new EntityListConfig(cfgName, entities, EntityUtils::getEntityRegistryName, EntityUtils::getEntityByRegistryName);
    }

    public static BlackWhiteList<Class <? extends Entity>> entities(UsageRestriction.ListType type,
                                                                    ImmutableList<Class <? extends Entity>> blackList,
                                                                    ImmutableList<Class <? extends Entity>> whiteList) {
        return BlackWhiteList.of(type,
                EntityListConfig.create("litetweaker.label.list_type.blacklist", blackList),
                EntityListConfig.create("litetweaker.label.list_type.whitelist", whiteList));
    }

    public static BlackWhiteList<Class <? extends Entity>> entitiyNames(UsageRestriction.ListType type,
                                                                    ImmutableList<String> blackList,
                                                                    ImmutableList<String> whiteList) {
        return BlackWhiteList.of(type,
                EntityListConfig.fromNames("litetweaker.label.list_type.blacklist", blackList),
                EntityListConfig.fromNames("litetweaker.label.list_type.whitelist", whiteList));
    }
}
