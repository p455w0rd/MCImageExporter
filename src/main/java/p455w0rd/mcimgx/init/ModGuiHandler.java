package p455w0rd.mcimgx.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import p455w0rd.mcimgx.MCImageExporter;
import p455w0rd.mcimgx.client.gui.GuiIMGExporter;
import p455w0rdslib.util.MCUtils;
import p455w0rdslib.util.PlayerUtils;

/**
 * @author p455w0rd
 *
 */
public class ModGuiHandler implements IGuiHandler {

	private static Entity entityToRender = null;

	public static void init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(ModGlobals.MODID, new ModGuiHandler());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World worldIn, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World worldIn, int x, int y, int z) {
		if (ID == GUIID.EXPORT_GUI) {
			return new GuiIMGExporter().setEntity(entityToRender);
		}
		return null;
	}

	public static void launchGui(int ID, Entity entityIn) {
		entityToRender = entityIn;
		PlayerUtils.getPlayer().openGui(MCImageExporter.INSTANCE, ID, MCUtils.getWorld(), (int) PlayerUtils.getPlayer().posX, (int) PlayerUtils.getPlayer().posY, (int) PlayerUtils.getPlayer().posZ);
	}

	public static class GUIID {
		private static int guiIndex = 0;
		public static final int EXPORT_GUI = ++guiIndex;
	}

}