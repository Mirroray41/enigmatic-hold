package net.theenigmaman.e_hold;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EHold implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");
    public static final String MOD_ID = "e_hold";
    @Override
    public void onInitialize() {


        LOGGER.info("Enigmatic Hold Initialized!");
    }
}
