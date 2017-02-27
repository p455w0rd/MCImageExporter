package p455w0rd.mcimgx;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;
import p455w0rd.mcimgx.init.ModGlobals;
import p455w0rd.mcimgx.proxy.CommonProxy;

@Mod(modid = ModGlobals.MODID, name = ModGlobals.NAME, version = ModGlobals.VERSION, dependencies = ModGlobals.DEPENDANCIES, acceptedMinecraftVersions = "[1.10.2]")
public class MCImageExporter {

	@SidedProxy(clientSide = ModGlobals.CLIENT_PROXY, serverSide = ModGlobals.SERVER_PROXY)
	public static CommonProxy PROXY;

	@Mod.Instance("mcimgx")
	public static MCImageExporter INSTANCE;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		INSTANCE = this;
		PROXY.preInit(e);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		PROXY.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		PROXY.postInit(e);
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent e) {
		PROXY.serverStarting(e);
	}
}
