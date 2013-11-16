package me.dawars.CraftingPillars.handlers;

import me.dawars.CraftingPillars.CraftingPillars;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandler {
	private static final String SOUND_RESOURCE_LOCATION = CraftingPillars.id.toLowerCase() + ":";

	public static String[] soundFiles = { SOUND_RESOURCE_LOCATION + "UranusParadiseShort.ogg" };

    @ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event) {

        for (String soundFile : soundFiles) {
            try {
                event.manager.addSound(soundFile);
                System.out.println("Sound file loaded: " + soundFile);

            }
            catch (Exception e) {
                System.out.println("Failed loading sound file: " + soundFile);
            }
        }
    }
}