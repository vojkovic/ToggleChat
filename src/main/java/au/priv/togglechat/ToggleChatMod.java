

package au.priv.togglechat;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import au.priv.togglechat.commands.impl.ToggleCommand;
import au.priv.togglechat.config.ConfigLoader;
import au.priv.togglechat.toggles.ToggleHandler;
import au.priv.togglechat.utils.ChatColor;

import java.io.File;
import java.util.Arrays;

@Mod(modid = "togglechat", useMetadata = true)

public class ToggleChatMod {
    private final ToggleHandler toggleHandler;
    private ConfigLoader configLoader;

    @Mod.Instance
    private static ToggleChatMod instance;

    public ToggleChatMod() {
        this.toggleHandler = new ToggleHandler(this);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        this.configLoader = new ConfigLoader(this, new File(event.getModConfigurationDirectory(), "togglechat"));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        this.toggleHandler.remake();

        MinecraftForge.EVENT_BUS.register(new ToggleEvents(this));
        ClientCommandHandler.instance.registerCommand(new ToggleCommand(this));
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        this.configLoader.loadCustomToggles();
        this.configLoader.loadToggles();
        this.configLoader.loadModernUtils();

        this.toggleHandler.inheritFavourites(this.configLoader.getFavourites());
    }
    
    /**
     * Version independent event registering. 1.7 does not
     * use the same event bus as 1.8 and above.
     *
     * @param target the object to register events under.
     */
    public void registerEvents(Object target) {
        // noinspection ConstantConditions
        if (ForgeVersion.mcVersion.startsWith("1.7")) {
            FMLCommonHandler.instance().bus().register(target);
        } else {
            MinecraftForge.EVENT_BUS.register(target);
        }
    }
    
    /**
     * Version independent event registering. 1.7 does not
     * use the same event bus as 1.8 and above.
     *
     * @param target the object to deregister events under.
     */
    public void unregisterEvents(Object target) {
        // noinspection ConstantConditions
        if (ForgeVersion.mcVersion.startsWith("1.7")) {
            FMLCommonHandler.instance().bus().unregister(target);
        } else {
            MinecraftForge.EVENT_BUS.unregister(target);
        }
    }

    /**
     * Getter for our config loader, used for saving
     *
     * @return the configloader
     */
    public ConfigLoader getConfigLoader() {
        return this.configLoader;
    }
    
    /**
     * Returns the instance of the togglehandler which stores all the registered
     * custom and default toggles for the mod
     *
     * @return the ToggleHandler for the mod.
     */
    public ToggleHandler getToggleHandler() {
        return this.toggleHandler;
    }
    
    /**
     * Getter for this instance
     *
     * @return the instance of this togglechat
     */
    public static ToggleChatMod getInstance() {
        return instance;
    }
}
