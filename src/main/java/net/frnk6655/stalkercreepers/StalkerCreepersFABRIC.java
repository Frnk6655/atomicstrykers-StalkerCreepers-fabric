package net.frnk6655.stalkercreepers;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StalkerCreepersFABRIC implements ModInitializer {
	public static  final String MOD_ID = "stalker-creepers-fabric";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world! Proceeding with Creeper stalkification");
	}
}