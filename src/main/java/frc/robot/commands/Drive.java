// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Drive extends CommandBase {
  DriveTrain driveTrain;
  double leftSpeed;
  double rightSpeed;

  public Drive(DriveTrain train, double leftSpeed, double rightSpeed) {
    addRequirements(train);
    this.driveTrain = train;
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    this.driveTrain.drive(leftSpeed, rightSpeed);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
