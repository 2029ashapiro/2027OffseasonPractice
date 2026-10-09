package frc.robot.subsystems.kicker;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.TalonFXSimState;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import frc.robot.Constants;

public class KickerIOSim implements KickerIO{
    public TalonFX kickerMotor = new TalonFX(Constants.KickerConstants.kickerMotorCANID);
    public TalonFXSimState kickerMotorSim = new TalonFXSimState(kickerMotor);
    public TalonFXConfiguration kickerMotorSimConfiguration = new TalonFXConfiguration();
    public DCMotor dcMotor = DCMotor.getKrakenX60(1);
    public FlywheelSim flywheelSim = new FlywheelSim(LinearSystemId.createFlywheelSystem(
                                                    dcMotor,
                                                    Constants.KickerConstants.kickerMOI,
                                                    Constants.KickerConstants.kickerGearing)
    , dcMotor);

     public KickerIOSim() {
        kickerMotor.getConfigurator().apply(new Slot0Configs()
                                            .withKP(Constants.KickerConstants.kPSim)
                                            .withKI(Constants.KickerConstants.kISim)
                                            .withKD(Constants.KickerConstants.kDSim)
                                            .withKV(Constants.KickerConstants.kVSim)
                                            );                                    
    }

    
    @Override
    public void setSpinSpeed(AngularVelocity omega) {
        kickerMotor.setControl(new VelocityVoltage(omega).withSlot(0));
    }

    @Override
    public void updateInputs(KickerIOInputs inputs) {
        inputs.kickerCurrent = kickerMotor.getStatorCurrent().getValue();
        inputs.kickerVelocity = kickerMotor.getVelocity().getValue();
        inputs.kickerVoltage = kickerMotor.getMotorVoltage().getValue();
    }
}
