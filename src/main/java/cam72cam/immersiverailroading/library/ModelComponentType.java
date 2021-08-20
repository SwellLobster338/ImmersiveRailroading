package cam72cam.immersiverailroading.library;

public enum ModelComponentType {
	// STANDARD
	BOGEY_POS_WHEEL_X("BOGEY_#POS#_WHEEL_#ID#"),
	BOGEY_POS("BOGEY_#POS#"),
	BOGEY_FRONT_WHEEL_X("BOGEY_FRONT_WHEEL_#ID#"),
	BOGEY_FRONT("BOGEY_FRONT"),
	BOGEY_REAR_WHEEL_X("BOGEY_REAR_WHEEL_#ID#"),
	BOGEY_REAR("BOGEY_REAR"),
	FRAME("FRAME"),
	FRAME_WHEEL_X("FRAME_WHEEL_#ID#"),
	
	SHELL("SHELL"),

	// LOCOMOTIVE
	CAB("CAB"),
	BELL("BELL"),
	WHISTLE("WHISTLE"),
	HORN("HORN"),
	
	// DIESEL
	FUEL_TANK("FUEL_TANK"),
	ALTERNATOR("ALTERNATOR"),
	ENGINE_BLOCK("ENGINE_BLOCK"),
	CRANKSHAFT("CRANKSHAFT"),
	PISTON_X("PISTON_#ID#"),
	FAN_X("FAN_#ID#"),
	DRIVE_SHAFT_X("DRIVE_SHAFT_#ID#"),
	GEARBOX("GEARBOX"),
	FLUID_COUPLING("FLUID_COUPLING"),
	FINAL_DRIVE("FINAL_DRIVE"),
	TORQUE_CONVERTER("TORQUE_CONVERTER"),
	
	
	//STEAM
	FIREBOX("FIREBOX"),
	SMOKEBOX("SMOKEBOX"),
	STEAM_CHEST("STEAM_CHEST"),
	STEAM_CHEST_POS("STEAM_CHEST_#POS#"),
	BOILER_SEGMENT_X("BOILER_SEG[E]*MENT_#ID#"),
	PIPING("PIPING"),
	
	
	// WALCHERTS
	WHEEL_DRIVER_X("WHEEL_DRIVER_#ID#"),
	WHEEL_DRIVER_POS_X("WHEEL_DRIVER_#POS#_#ID#"), // MALLET

	CYLINDER_SIDE("CYLINDER_#SIDE#"),
	SIDE_ROD_SIDE("(DRIVE|CONNECTING|SIDE)_ROD_#SIDE#"),
	MAIN_ROD_SIDE("(DRIVING|MAIN)_ROD_#SIDE#"),
	PISTON_ROD_SIDE("PISTON_ROD_#SIDE#"),
	
	UNION_LINK_SIDE("(UNION_LINK|CROSS_HEAD)_#SIDE#"),
	COMBINATION_LEVER_SIDE("COMBINATION_LEVER_#SIDE#"),
	VALVE_STEM_SIDE("VALVE_STEM_#SIDE#"),
	RADIUS_BAR_SIDE("RADIUS_(ROD|BAR)_#SIDE#"),
	EXPANSION_LINK_SIDE("(EXPANSION|SLOTTED)_LINK_#SIDE#"),
	ECCENTRIC_ROD_SIDE("(ECCENTRIC|RETURN_CRANK)_ROD_#SIDE#"),
	ECCENTRIC_CRANK_SIDE("(ECCENTRIC|RETURN)_CRANK_#SIDE#"),
	REVERSING_ARM_SIDE("REVERSING_ARM_#SIDE#"),
	LIFTING_LINK_SIDE("LIFTING_LINK_#SIDE#"),
	REACH_ROD_SIDE("REACH_ROD_#SIDE#"),
	
	// MALLET
	FRONT_FRAME("FRONT_(LOCOMOTIVE|FRAME)"),
	REAR_FRAME("REAR_(LOCOMOTIVE|FRAME)"),
	FRONT_SHELL("FRONT_SHELL"),
	REAR_SHELL("REAR_SHELL"),

	
	// PARTICLES
	PARTICLE_CHIMNEY_X("CHIM[I]*NEY_#ID#", false),
	PRESSURE_VALVE_X("PRESSURE_VALVE_#ID#", false),
	DIESEL_EXHAUST_X("EXHAUST_#ID#", false),
	
	// Cargo
	CARGO_FILL_X("CARGO_FILL_#ID#"),
	CARGO_FILL_POS_X("CARGO_FILL_#POS#_#ID#"),

	// Lights
	HEADLIGHT_X("HEADLIGHT_#ID#"),
	HEADLIGHT_POS_X("HEADLIGHT_#POS#_#ID#"),

	// REST
	IMMERSIVERAILROADING_BASE_COMPONENT("IMMERSIVERAILROADING_BASE_COMPNOENT"),
	REMAINING(""),
	;

    public final String regex;
	public final boolean collisionsEnabled;
	
	ModelComponentType(String regex) {
		this(regex, true);
	}
	ModelComponentType(String regex, boolean collide) {
		this.regex = ".*" + regex + ".*";
		this.collisionsEnabled = collide;
	}

	public static boolean isParticle(String group) {
		return group.contains("CHIMNEY_") || group.contains("CHIMINEY_") || group.contains("PRESSURE_VALVE_") || group.contains("EXHAUST_");
	}
}
