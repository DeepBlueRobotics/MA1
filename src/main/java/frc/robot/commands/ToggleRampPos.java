// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Ramp;

public class ToggleRampPos extends CommandBase {
  private final Ramp ramp;
  private int direction;

  public ToggleRampPos(Ramp ramp, int direction) { // 1 = go up, 0 = go down
    addRequirements(ramp);
    this.ramp = ramp;
    this.direction = direction;
  }

  @Override
  public void initialize() {
    ramp.moveRamp(ramp.speed * direction);
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
