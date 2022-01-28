package org.spoorn.spoornpink;

import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.ModInitializer;

@Log4j2
public class SpoornPink implements ModInitializer {

    @Override
    public void onInitialize() {
        log.info("Hello from SpoornPink!");
    }
}
