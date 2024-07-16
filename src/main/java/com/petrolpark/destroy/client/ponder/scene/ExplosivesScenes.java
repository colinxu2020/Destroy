package com.petrolpark.destroy.client.ponder.scene;

import java.util.function.Supplier;

import com.petrolpark.destroy.block.DestroyBlocks;
import com.petrolpark.destroy.block.entity.CustomExplosiveMixBlockEntity;
import com.petrolpark.destroy.block.entity.IDyeableCustomExplosiveMixBlockEntity;
import com.petrolpark.destroy.compat.CompatMods;
import com.petrolpark.destroy.item.DestroyItems;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.PonderPalette;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.EntityElement;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.ponder.element.WorldSectionElement;
import com.simibubi.create.foundation.utility.Pointing;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

public class ExplosivesScenes {
    
    private static final BlockPos craftingTable = new BlockPos(2, 1, 4);
    private static final BlockPos anvil = new BlockPos(2, 1, 0);
    private static final BlockPos first = new BlockPos(1, 1, 2);
    private static final BlockPos second = new BlockPos(3, 1, 2);

    public static void filling(SceneBuilder scene, SceneBuildingUtil util, Supplier<ItemStack> stack) {
        scene.title("explosives.filling", "This text is defined in a language file.");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();

        scene.idle(5);
        scene.world.showSection(util.select.position(craftingTable), Direction.DOWN);
        scene.idle(5);
        scene.overlay.showText(100)
            .text("This text is defined in a language file.")
            .pointAt(util.vector.blockSurface(craftingTable, Direction.WEST));
        scene.idle(10);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(craftingTable), Pointing.DOWN).withItem(stack.get()), 40);
        scene.idle(50);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(craftingTable), Pointing.DOWN).withItem(DestroyItems.CORDITE.asStack()), 40);
        scene.idle(60);

        scene.world.showSection(util.select.position(first), Direction.DOWN);
        scene.idle(20);
        scene.overlay.showText(100)
            .text("This text is defined in a language file")
            .pointAt(util.vector.blockSurface(first, Direction.WEST))
            .attachKeyFrame();
        scene.idle(20);
        ElementLink<WorldSectionElement> funnel = scene.world.showIndependentSection(util.select.position(first.above()), Direction.DOWN);
        scene.idle(20);
        ElementLink<EntityElement> itemEntity = scene.world.createItemEntity(util.vector.centerOf(first.above(3)), Vec3.ZERO, DestroyItems.PICRIC_ACID_TABLET.asStack());
        scene.idle(30);
        scene.world.modifyEntity(itemEntity, Entity::kill);
        scene.idle(30);
        scene.world.hideIndependentSection(funnel, Direction.UP);
        scene.idle(20);

        scene.overlay.showText(100)
            .text("This text is defined in a language file")
            .pointAt(util.vector.blockSurface(first, Direction.WEST))
            .attachKeyFrame();
        scene.idle(20);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(first), Pointing.DOWN).rightClick(), 80);
        scene.idle(100);

        scene.world.showSection(util.select.position(second), Direction.DOWN);
        scene.overlay.showText(170)
            .text("This text is defined in a language file.")
            .independent()
            .attachKeyFrame();
        scene.idle(20);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(first), Pointing.DOWN).rightClick().withItem(AllBlocks.CLIPBOARD.asStack()), 40);
        scene.idle(50);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(second), Pointing.DOWN).leftClick().withItem(AllBlocks.CLIPBOARD.asStack()), 40);
        scene.idle(50);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(second), Pointing.DOWN).withItem(DestroyItems.CORDITE.asStack()), 20);
        scene.idle(30);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(second), Pointing.DOWN).withItem(DestroyItems.PICRIC_ACID_TABLET.asStack()), 20);
        scene.idle(40);
        
        scene.markAsFinished();
    };

    private static final int red = 14128786;
    private static final int purple = 10912960;

    public static void dyeing(SceneBuilder scene, SceneBuildingUtil util, Supplier<ItemStack> stack) {
        scene.title("explosives.dyeing", "This text is defined in a language file.");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();

        scene.idle(5);
        scene.world.showSection(util.select.position(craftingTable), Direction.DOWN);
        scene.idle(5);
        scene.overlay.showText(100)
            .text("This text is defined in a language file.")
            .pointAt(util.vector.blockSurface(craftingTable, Direction.WEST));
        scene.idle(10);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(craftingTable), Pointing.DOWN).withItem(stack.get()), 40);
        scene.idle(50);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(craftingTable), Pointing.DOWN).withItem(new ItemStack(Items.RED_DYE)), 40);
        scene.idle(60);

        scene.world.modifyBlockEntity(first, BlockEntity.class, be -> ((IDyeableCustomExplosiveMixBlockEntity)be).setColor(red));
        scene.world.showSection(util.select.position(first), Direction.DOWN);
        scene.idle(20);

        scene.overlay.showText(80)
            .text("This text is defined in a language file.")
            .pointAt(util.vector.blockSurface(first, Direction.WEST));
        scene.idle(40);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(first), Pointing.DOWN).rightClick().withItem(new ItemStack(Items.BLUE_DYE)), 40);
        scene.idle(5);
        scene.world.modifyBlockEntity(first, BlockEntity.class, be -> ((IDyeableCustomExplosiveMixBlockEntity)be).setColor(purple));
        scene.addInstruction(s -> s.forEach(WorldSectionElement.class, WorldSectionElement::queueRedraw));
        scene.idle(55);

        scene.world.showSection(util.select.position(second), Direction.DOWN);
        scene.overlay.showText(80)
            .text("This text is defined in a language file.")
            .independent()
            .attachKeyFrame();
        scene.idle(20);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(first), Pointing.DOWN).rightClick().withItem(AllBlocks.CLIPBOARD.asStack()), 40);
        scene.idle(50);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(second), Pointing.DOWN).leftClick().withItem(AllBlocks.CLIPBOARD.asStack()), 40);
        scene.idle(5);
        scene.world.modifyBlockEntity(second, BlockEntity.class, be -> ((IDyeableCustomExplosiveMixBlockEntity)be).setColor(purple));
        scene.addInstruction(s -> s.forEach(WorldSectionElement.class, WorldSectionElement::queueRedraw));
        scene.idle(25);

        scene.markAsFinished();
    };

    public static void naming(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("explosives.naming", "This text is defined in a language file.");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();

        scene.idle(5);
        scene.world.showSection(util.select.position(anvil), Direction.DOWN);
        scene.idle(5);
        scene.overlay.showText(120)
            .text("This text is defined in a language file.")
            .pointAt(util.vector.blockSurface(anvil, Direction.WEST));
        scene.idle(10);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(anvil), Pointing.DOWN).withItem(DestroyBlocks.CUSTOM_EXPLOSIVE_MIX.asStack()), 40);
        scene.idle(70);
        ItemStack namedStack = DestroyBlocks.CUSTOM_EXPLOSIVE_MIX.asStack();
        Component name = Component.literal("TNX");
        namedStack.setHoverName(name);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(anvil), Pointing.DOWN).withItem(namedStack), 40);
        scene.idle(60);

        scene.world.modifyBlockEntity(first, CustomExplosiveMixBlockEntity.class, be -> be.setCustomName(name));
        scene.world.showSection(util.select.position(first), Direction.DOWN);
        scene.idle(20);

        scene.overlay.showText(80)
            .text("This text is defined in a language file.")
            .pointAt(util.vector.blockSurface(first, Direction.WEST));
        scene.idle(100);

        scene.world.showSection(util.select.position(second), Direction.DOWN);
        scene.overlay.showText(110)
            .text("This text is defined in a language file.")
            .independent()
            .attachKeyFrame();
        scene.idle(20);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(first), Pointing.DOWN).rightClick().withItem(AllBlocks.CLIPBOARD.asStack()), 40);
        scene.idle(50);
        scene.overlay.showControls(new InputWindowElement(util.vector.topOf(second), Pointing.DOWN).leftClick().withItem(AllBlocks.CLIPBOARD.asStack()), 40);
        scene.idle(5);
        scene.world.modifyBlockEntity(second, CustomExplosiveMixBlockEntity.class, be -> be.setCustomName(name));
        scene.idle(55);

        if (CompatMods.BIG_CANNONS.isLoaded()) {
            scene.overlay.showText(100)
                .text("This text is defined in a language file.")
                .colored(PonderPalette.RED)
                .independent();
            scene.idle(120);
        };

        scene.markAsFinished();
    };
};
