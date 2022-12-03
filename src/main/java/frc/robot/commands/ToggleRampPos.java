// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Ramp;

public class ToggleRampPos extends CommandBase {
  private final Ramp ramp;
  private int direction;

  public ToggleRampPos(Ramp ramp) {
    addRequirements(ramp);
    this.ramp = ramp;
    this.direction = -ramp.currentRampPos; // 1 = go up, -1 = go down (flip this to move the correct direction)
  }

  @Override
  public void initialize() {
    ramp.moveRamp(SmartDashboard.getNumber("Ramp Std Speed", .05) * direction);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    ramp.stopRamp();
    ramp.currentRampPos = direction;
  }

  @Override
  public boolean isFinished() {
    return ramp.outOfBounds(direction);
  }
}
