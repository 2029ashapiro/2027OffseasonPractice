// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.kicker;

import static edu.wpi.first.units.Units.RPM;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import org.littletonrobotics.junction.Logger;

public class Kicker extends SubsystemBase { 
  private final KickerIO io;
  private final KickerIOInputsAutoLogged inputs = new KickerIOInputsAutoLogged();

  /** Creates a new Kicker. */
  public Kicker(KickerIO io) {
    this.io = io;
  }

  public void StartKicker() {
    io.setSpinSpeed(Constants.KickerConstants.defaultOmega);
  }

  public void StopKicker() {
    io.setSpinSpeed(RPM.of(0));
  }

  public void ReverseKicker() {
    io.setSpinSpeed(Constants.KickerConstants.defaultOmega.unaryMinus());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    io.updateInputs(inputs);

    Logger.processInputs(getName(), inputs);
  }
}
