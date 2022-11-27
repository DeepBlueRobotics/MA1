// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Ramp;

public class ToggleRampPos extends CommandBase {
  private final Ramp ramp;

  public ToggleRampPos(Ramp ramp) {
    addRequirements(ramp);
    this.ramp = ramp;
  }

  @Override
  public void initialize() {
    ramp.moveRamp(ramp.STD_RAMP_SPEED*-ramp.currentRampPos);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    ramp.stopRamp();
  }

  @Override
  public boolean isFinished() {
    if (ramp.outOfBounds()) {
      return true;
    }
    return false;
  }
}
