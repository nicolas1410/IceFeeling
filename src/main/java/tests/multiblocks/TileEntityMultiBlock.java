package tests.multiblocks;

import java.util.Timer;
import java.util.TimerTask;

import dmz.faction.icefeeling.mod.registry.IFTileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntityMultiBlock extends TileEntity implements ITickableTileEntity {

	private boolean hasMaster = false;
	private boolean isMaster = false;
	private int masterX = 0;
	private int masterY = 0;
	private int masterZ = 0;

	public TileEntityMultiBlock() {
		super(IFTileRegistry.MULTI_BLOCK_TILE.get());

	}

	public boolean hasMaster() {
		return this.hasMaster;
	}

	public boolean isMaster() {
		return this.isMaster;
	}

	public int getMasterX() {
		return this.masterX;
	}

	public int getMasterY() {
		return this.masterY;
	}

	public int getMasterZ() {
		return this.masterZ;
	}

	public void setMaster(int x, int y, int z) {
		this.hasMaster = true;
		this.masterX = x;
		this.masterY = y;
		this.masterZ = z;
	}

	private boolean checkStructure() {
		int n = 0;
		for (int x = this.pos.getX() - 1; x < this.pos.getX() + 2; x++) {
			for (int z = this.pos.getZ() - 1; z < this.pos.getZ() + 2; z++) {
				BlockPos position = new BlockPos(x, this.pos.getY(), z);
				TileEntity tile = world.getTileEntity(position);
				if (tile != null && tile instanceof TileEntityMultiBlock) {
					TileEntityMultiBlock t = (TileEntityMultiBlock) tile;
					if (t.hasMaster) {
						if (this.pos.getX() == t.getMasterX() && this.pos.getY() == t.getMasterY()
								&& this.pos.getZ() == t.getMasterZ()) {
							n++;
						}
					} else {
						n++;
					}
				}
			}

		}

		if (n == 9) {
			System.out.println("Structure == 9 blocks");
			return true;
		}
		return false;

	}

	private void setupStructure() {

		for (int x = this.pos.getX() - 1; x < this.pos.getX() + 2; x++) {
			for (int z = this.pos.getZ() - 1; z < this.pos.getZ() + 2; z++) {
				BlockPos position = new BlockPos(x, this.pos.getY(), z);
				TileEntity tile = world.getTileEntity(position);
				if (tile != null && tile instanceof TileEntityMultiBlock) {
					TileEntityMultiBlock t = (TileEntityMultiBlock) tile;
					t.setMaster(this.pos.getX(), this.pos.getY(), this.pos.getZ());
					this.isMaster = true;
					System.out.println("SetupStructure : isMaster = true");

				}
			}
		}
	}

	private void resetStructure() {

		for (int x = this.pos.getX() - 1; x < this.pos.getX() + 2; x++) {
			for (int z = this.pos.getZ() - 1; z < this.pos.getZ() + 2; z++) {
				BlockPos position = new BlockPos(x, this.pos.getY(), z);
				TileEntity tile = world.getTileEntity(position);
				if (tile != null && tile instanceof TileEntityMultiBlock) {

					TileEntityMultiBlock t = (TileEntityMultiBlock) tile;
					t.reset();
					System.out.println("ResetStructure : RESET");

				}
			}
		}
	}

	private boolean checkForMaster() {
		BlockPos position = new BlockPos(masterX, masterY, masterZ);
		TileEntity tile = world.getTileEntity(position);
		if (tile != null && tile instanceof TileEntityMultiBlock) {
			TileEntityMultiBlock t = (TileEntityMultiBlock) tile;
			if (t.isMaster()) {
				System.out.println("Check for master : true");
				return true;

			}
		}
		return false;
	}

	public void reset() {
		System.out.println("reset");
		this.isMaster = false;
		this.hasMaster = false;
		this.masterX = 0;
		this.masterY = 0;
		this.masterZ = 0;
	}

	@Override
	public void read(BlockState state, CompoundNBT compound) {
		super.read(state, compound);
		this.hasMaster = compound.getBoolean("HasMaster");
		this.isMaster = compound.getBoolean("IsMaster");
		this.masterX = compound.getInt("MasterX");
		this.masterY = compound.getInt("MasterY");
		this.masterZ = compound.getInt("MasterZ");
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.putBoolean("HasMaster", hasMaster);
		compound.putBoolean("IsMaster", isMaster);
		this.masterX = compound.getInt("MasterX");
		this.masterY = compound.getInt("MasterY");
		this.masterZ = compound.getInt("MasterZ");
		return compound;
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT compound = new CompoundNBT();
		this.write(compound);
		this.markDirty();
		return new SUpdateTileEntityPacket(getPos(), -1, compound);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		CompoundNBT tag = pkt.getNbtCompound();
		this.read(world.getBlockState(pos), tag);
		this.markDirty();
		world.markBlockRangeForRenderUpdate(pos, world.getBlockState(pos).getBlock().getDefaultState(),
				world.getBlockState(pos));
	}

	@Override
	public void tick() {

		if (this.isMaster) {
			if (!this.checkStructure()) {
				this.resetStructure();
			}
		} else if (this.hasMaster) {
			if (!checkForMaster()) {
				this.reset();
			}
		} else if (!this.hasMaster) {
			if (this.checkStructure()) {
				this.setupStructure();
			}
		}

	}

}
