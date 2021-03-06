package thebombzen.mods.enchantview.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import thebombzen.mods.enchantview.Configuration;
import thebombzen.mods.enchantview.EnchantView;
import thebombzen.mods.enchantview.SideSpecificUtilities;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientSideSpecificUtilities implements SideSpecificUtilities {
	@Override
	public boolean canPlayerUseCommand(EntityPlayerMP player) {
		String ownerName = Minecraft.getMinecraft().thePlayer
				.getCommandSenderName();
		if (ownerName.equals(player.getCommandSenderName())) {
			return true;
		} else if (EnchantView.instance.getConfiguration().getBooleanProperty(Configuration.ALLOW_ON_LAN)) {
			return true;
		} else {
			return false;
		}
	}
}
