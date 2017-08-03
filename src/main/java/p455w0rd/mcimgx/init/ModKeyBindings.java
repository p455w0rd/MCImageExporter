package p455w0rd.mcimgx.init;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * @author p455w0rd
 *
 */
public class ModKeyBindings {

	public static KeyBinding KEYBIND_EXPORT_ITEM_IMAGE, KEYBIND_EXPORT_GUI;

	public static void init() {
		KEYBIND_EXPORT_ITEM_IMAGE = new KeyBinding("key.exportItemImage", Keyboard.CHAR_NONE, "key.categories.mcimgx");
		KEYBIND_EXPORT_GUI = new KeyBinding("key.exportGui", Keyboard.CHAR_NONE, "key.categories.mcimgx");
		ClientRegistry.registerKeyBinding(KEYBIND_EXPORT_ITEM_IMAGE);
		ClientRegistry.registerKeyBinding(KEYBIND_EXPORT_GUI);
	}

}
