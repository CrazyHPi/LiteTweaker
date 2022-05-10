package xyz.crazyh.litetweaker.util.autoContainerProcess;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.value.BaseOptionListConfigValue;

public class AutoDropContainerType extends BaseOptionListConfigValue {
    public static final AutoDropContainerType SHULKER = new AutoDropContainerType("shulker", "litetweaker.label.auto_drop_container_type.shulker");
    public static final AutoDropContainerType ALL = new AutoDropContainerType("all", "litetweaker.label.auto_drop_container_type.all");

    public static final ImmutableList<AutoDropContainerType> VALUES = ImmutableList.of(
            SHULKER,
            ALL
    );

    public AutoDropContainerType(String name, String translationKey) {
        super(name, translationKey);
    }
}
