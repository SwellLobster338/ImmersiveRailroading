package cam72cam.immersiverailroading.net;

import cam72cam.immersiverailroading.items.nbt.RailSettings;
import cam72cam.immersiverailroading.tile.TileRailPreview;
import cam72cam.mod.entity.Player;
import cam72cam.mod.item.ItemStack;
import cam72cam.mod.math.Vec3i;
import cam72cam.mod.net.Packet;
import cam72cam.mod.serialization.TagField;
import cam72cam.mod.util.Hand;

public class ItemRailUpdatePacket extends Packet {
	@TagField
	private RailSettings settings;
	@TagField
	private Vec3i pos;

	public ItemRailUpdatePacket() { }

	public ItemRailUpdatePacket(RailSettings settings) {
		this.settings = settings;
	}

	public ItemRailUpdatePacket(Vec3i tilePreviewPos, RailSettings settings) {
		this.pos = tilePreviewPos;
		this.settings = settings;
	}

	@Override
	public void handle() {
		if (pos != null) {
			TileRailPreview tile = this.getWorld().getBlockEntity(pos, TileRailPreview.class);
			settings.write(tile.getItem());
		} else {
			Player player = this.getPlayer();
			ItemStack stack = player.getHeldItem(Hand.PRIMARY);
			settings.write(stack);
			player.setHeldItem(Hand.PRIMARY, stack);
		}
	}
}
