package p455w0rd.mcimgx.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import p455w0rd.mcimgx.init.ModEvents;
import p455w0rd.mcimgx.init.ModGuiHandler;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
	}

	public void init(FMLInitializationEvent e) {
		ModEvents.init();
	}

	public void postInit(FMLPostInitializationEvent e) {
		ModGuiHandler.init();
	}

	public void serverStarting(FMLServerStartingEvent e) {
	}

}
