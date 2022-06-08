package org.samo_lego.fabrictailor.client.screen.tabs;

import com.mojang.authlib.properties.Property;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.samo_lego.fabrictailor.network.SkinPackets;
import org.samo_lego.fabrictailor.util.SkinFetcher;
import org.samo_lego.fabrictailor.util.TextTranslations;

public class LocalSkinTab extends GuiComponent implements SkinTabType {
    private final MutableComponent TITLE;
    private final MutableComponent DESCRIPTION;
    private final ItemStack ICON;

    public LocalSkinTab() {
        this.ICON = new ItemStack(Items.MAGENTA_GLAZED_TERRACOTTA);
        this.DESCRIPTION = TextTranslations.create("description.fabrictailor.title_local");
        this.TITLE = TextTranslations.create("tab.fabrictailor.title_local");
    }

    @Override
    public MutableComponent getTitle() {
        return this.TITLE;
    }

    @Override
    public MutableComponent getDescription() {
        return this.DESCRIPTION;
    }

    @Override
    public ItemStack getIcon() {
        return this.ICON;
    }

    @Override
    public ServerboundCustomPayloadPacket getSkinChangePacket(String filePath, boolean useSlim) {
        Property skinData = SkinFetcher.setSkinFromFile(filePath, useSlim);

        if(skinData == null)
            return null;
        return SkinPackets.createSkinChangePacket(skinData);
    }

    @Override
    public boolean showExplorerButton() {
        return true;
    }
}