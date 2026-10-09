package frc.robot.subsystems.kicker;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import frc.robot.Constants;

public class KickerIOReal implements KickerIO{
    public TalonFX kickerMotor = new TalonFX(Constants.KickerConstants.kickerMotorCANID);
    public TalonFXConfiguration kickerMotorConfig = new TalonFXConfiguration();

    public KickerIOReal() {
        kickerMotor.getConfigurator().apply(new Slot0Configs().withKP(Constants.KickerConstants.kPReal)
                                            .withKI(Constants.KickerConstants.kIReal)
                                            .withKD(Constants.KickerConstants.kDReal)
                                            .withKV(Constants.KickerConstants.kVReal)
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
 