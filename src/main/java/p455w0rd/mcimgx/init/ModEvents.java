package p455w0rd.mcimgx.init;

import org.lwjgl.input.Keyboard;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import p455w0rd.mcimgx.init.ModGuiHandler.GUIID;
import p455w0rdslib.util.ImageUtils;
import p455w0rdslib.util.MCUtils;
import p455w0rdslib.util.RenderUtils;

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
		if (ModKeyBindings.KEYBIND_EXPORT_ITEM_IMAGE.getKeyCode() != Keyboard.CHAR_NONE && ModKeyBindings.KEYBIND_EXPORT_ITEM_IMAGE.isPressed()) {
			ImageUtils.saveCurrentItem();
			return;
		}
		if (ModKeyBindings.KEYBIND_EXPORT_GUI.getKeyCode() != Keyboard.CHAR_NONE && ModKeyBindings.KEYBIND_EXPORT_GUI.isPressed()) {
			ModGuiHandler.launchGui(GUIID.EXPORT_GUI, RenderUtils.getRenderManager().pointedEntity);
		}
	}

}
