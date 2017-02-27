package p455w0rd.mcimgx.init;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import p455w0rdslib.util.ImageUtils;
import p455w0rdslib.util.MCUtils;

public class ModEvents {

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new ModEvents());
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void getKeyPress(TickEvent.ClientTickEvent event) {
		if (MCUtils.getWorld() == null) {
			return;
		}
		if (ModKeyBindings.KEYBIND_EXPORT_ITEM_IMAGE.isPressed()) {
			ImageUtils.saveCurrentItem();
		}
	}

}
