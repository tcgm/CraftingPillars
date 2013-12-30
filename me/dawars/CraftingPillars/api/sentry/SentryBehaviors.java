package me.dawars.CraftingPillars.api.sentry;

import java.util.HashMap;
import java.util.Map;

public class SentryBehaviors {
	/** Registry for all sentry behaviors. */
	private static Map sentryBehaviorRegistry = new HashMap();

	/**
	 * Use this method to register a new behavior for an item/block.
	 */	
	public static void add(int itemID, Object behavior)
	{
		if(behavior instanceof IBehaviorSentryItem)
		{
			sentryBehaviorRegistry.put(itemID, behavior);
		} else {
			System.out.println("[CraftingPillar]: Couldn't register " + behavior.toString() + "! It has to implement IBehaviorSentryItem!");
		}
	}

	public static IBehaviorSentryItem get(int itemID) {
		return (IBehaviorSentryItem) sentryBehaviorRegistry.get(itemID);
	}
}
