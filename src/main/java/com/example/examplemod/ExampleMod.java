package com.example.examplemod;

import com.example.examplemod.libs.Button;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("examplemod")
public class ExampleMod {
    public static Logger logger = LogManager.getLogger();



    @Mod.EventBusSubscriber(Dist.CLIENT)
    public static class ASuccess {
        @SubscribeEvent
        public static void onGuiOpen(GuiScreenEvent.InitGuiEvent.Post event) {
            if (event.getGui() instanceof GuiMainMenu) {
                // runs fine
                logger.info("Adding button2. Should work.");
                event.addButton(new Button2(-1, 0, 0, 40, 20, "success"));
                logger.info("Added button2. It worked.");
            }
        }
    }

    @Mod.EventBusSubscriber(Dist.CLIENT)
    public static class Failure1 {
        @SubscribeEvent
        public static void onGuiOpen(GuiScreenEvent.InitGuiEvent.Post event) {
            if (event.getGui() instanceof GuiMainMenu) {
                // throws VerifyError
                logger.info("Adding button1. This won't show up.");
                event.addButton(new Button(-1, 40, 0, 40, 20, "success"));
                logger.info("Added button1. This won't show up.");
            }
        }
    }

    @Mod.EventBusSubscriber(Dist.CLIENT)
    public static class Failure2 {
        @SubscribeEvent
        public static void onGuiOpen(GuiScreenEvent.InitGuiEvent.Post event) {
            if (event.getGui() instanceof GuiMainMenu) {
                // processing runs in alphabetical. Failure1 failed, so this won't be registered either.
                logger.info("Doesn't get added :(");
            }
        }
    }
}
