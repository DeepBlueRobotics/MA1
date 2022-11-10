// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Autonomous extends CommandBase {
  public static final double autoDistance = 0.6096; // 2 ft in meters
  public static final double autoSpeed = 1;

  private boolean isFinished = false;
  private DriveTrain train;

  public Autonomous(DriveTrain train) {
    addRequirements(train);

    this.train = train;
  }

  @Override
  public void initialize() {
    train.resetEncoders();
  }

  @Override
  public void execute() {
    if (train.getDistance() < autoDistance) {
      train.forward(autoSpeed);
    } else {
      train.stop();
      isFinished = true;
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
