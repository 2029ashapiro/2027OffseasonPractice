package frc.robot.subsystems.kicker;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.RPM;
import static edu.wpi.first.units.Units.Volts;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;

public interface KickerIO {

  @AutoLog
  public static class KickerIOInputs {
    public AngularVelocity kickerVelocity = RPM.of(0);
    public Current kickerCurrent = Amps.of(0);
    public Voltage kickerVoltage = Volts.of(0);
  }

  public default void setSpinSpeed(AngularVelocity defaultomega) {}

  public default void updateInputs(KickerIOInputs inputs) {}
}
