package cam72cam.immersiverailroading.entity;

import cam72cam.immersiverailroading.util.Speed;

class PhysicsAccummulator {
	//http://evilgeniustech.com/idiotsGuideToRailroadPhysics/HorsepowerAndTractiveEffort/
	//http://www.republiclocomotive.com/locomotive-power-calculations.html
	//http://www.wplives.org/forms_and_documents/Air_Brake_Principles.pdf

	double tractiveEffortNewtons = 0;
	double airBrake = 0;
	//lbs
	double rollingResistanceNewtons = 0;
	double gradeForceNewtons = 0;
	double massToMoveKg = 0;
	Speed speed;
	
	public PhysicsAccummulator(Speed speed) {
		this.speed = speed;
	}

	void accumulate(EntityRollingStock stock, Boolean direction) {
		massToMoveKg += stock.getWeight();
		
		// SHOULD THIS HAVE DIRECTION MULT?
		double stockMassLb = 2.20462 * stock.getWeight();
		rollingResistanceNewtons += 0.0015 * stockMassLb * 4.44822f;
		
		// SHOULD THIS HAVE DIRECTION MULT?
		double grade = -Math.tan(Math.toRadians(stock.rotationPitch % 90));
		// lbs * 1%gradeResistance * grade multiplier
		gradeForceNewtons += (stockMassLb / 100) * (grade * 100)  * 4.44822f;
		
		if (stock instanceof Locomotive) {
			Locomotive loco = (Locomotive) stock;
			tractiveEffortNewtons += loco.getTractiveEffortNewtons(speed) * (direction ? 1 : -1);
			airBrake += loco.getAirBrake();
		}
	}
	
	Speed getVelocity() {
		// 0.25 = steel wheel on steel rail
		double brakeAdhesion =  massToMoveKg * 0.25;
		double airBrakeNewtons = brakeAdhesion * Math.min(airBrake, 1) * 4.44822f;
		
		// a = f (to newtons) * m (to newtons)
		double tractiveAccell = tractiveEffortNewtons / massToMoveKg;
		double resistanceAccell = rollingResistanceNewtons / massToMoveKg;
		double gradeAccell = gradeForceNewtons / massToMoveKg;
		double brakeAccell = airBrakeNewtons / massToMoveKg;
		
		double currentMCVelocity = speed.minecraft();
		double deltaAccellTractiveMCVelocity = Speed.fromMetric(tractiveAccell).minecraft();
		
		
		double deltaAccellGradeMCVelocity = Speed.fromMetric(gradeAccell).minecraft();
		
		double deltaAccellRollingResistanceMCVelocity = Speed.fromMetric(resistanceAccell).minecraft();
		double deltaAccellBrakeMCVelocity = Speed.fromMetric(brakeAccell).minecraft();
		// Limit decell to current speed to trains stop
		// Apply in the reverse direction of current travel
		double deltaDecell = -1 * Math.copySign(Math.min(Math.abs(currentMCVelocity), deltaAccellRollingResistanceMCVelocity +  deltaAccellBrakeMCVelocity), currentMCVelocity);
		
		double newMCVelocity = currentMCVelocity + deltaAccellTractiveMCVelocity + deltaAccellGradeMCVelocity + deltaDecell;

		if (Math.abs(newMCVelocity) < 0.001) {
			newMCVelocity = 0;
		}
		
		return Speed.fromMinecraft(newMCVelocity);
	}
}