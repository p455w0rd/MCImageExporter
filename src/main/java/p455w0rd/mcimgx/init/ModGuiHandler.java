package p455w0rd.mcimgx.init;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import p455w0rd.mcimgx.MCImageExporter;

/**
 * @author p455w0rd
 *
 */
public class ModGuiHandler implements IGuiHandler {

	public static void init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(ModGlobals.MODID, new ModGuiHandler());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World worldIn, int x, int y, int z) {
		if (ID == GUIID.EXPORT_GUI) {
			return null;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World worldIn, int x, int y, int z) {
		if (ID == GUIID.EXPORT_GUI) {
			return null;
		}
		return null;
	}

	public static void launchGui(int ID, EntityPlayer playerIn, World worldIn, int x, int y, int z) {
		playerIn.openGui(MCImageExporter.INSTANCE, ID, worldIn, x, y, z);
	}

	public static class GUIID {
		private static int guiIndex = 0;
		public static final int EXPORT_GUI = ++guiIndex;
	}

}