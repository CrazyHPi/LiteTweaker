package xyz.crazyh.litetweaker.config.option;

import fi.dy.masa.malilib.gui.config.ConfigWidgetContext;
import fi.dy.masa.malilib.gui.widget.button.BaseValueListEditButton;
import fi.dy.masa.malilib.gui.widget.button.GenericButton;
import fi.dy.masa.malilib.gui.widget.list.entry.DataListEntryWidgetData;
import fi.dy.masa.malilib.gui.widget.list.entry.config.list.BaseValueListConfigWidget;
import fi.dy.masa.malilib.util.StringUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import xyz.crazyh.litetweaker.util.EntityUtils;

public class EntityListConfigWidget extends BaseValueListConfigWidget<Class <? extends Entity>, EntityListConfig> {
    public EntityListConfigWidget(EntityListConfig config, DataListEntryWidgetData constructData, ConfigWidgetContext ctx) {
        super(config, constructData, ctx);
    }


    @Override
    protected GenericButton createButton(int width, int height, EntityListConfig config, ConfigWidgetContext ctx) {
        String title = StringUtils.translate("litetweaker.title.screen.block_list_edit", this.config.getDisplayName());

        return new BaseValueListEditButton<>(width, height, config,
                this::updateWidgetState,
                () -> EntityPlayer.class,
                EntityUtils::getEntityList,
                EntityUtils::getEntityRegistryName,
                null,
                title
                );
    }
}
