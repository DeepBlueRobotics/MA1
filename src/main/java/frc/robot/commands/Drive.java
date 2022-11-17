// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Drive extends CommandBase {
  private DriveTrain train;
  private Joystick leftJoy, rightJoy;

  public Drive(DriveTrain train, Joystick leftJoy, Joystick rightJoy) {
    addRequirements(train);

    this.train = train;
    this.leftJoy = leftJoy;
    this.rightJoy = rightJoy;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (train.mode == DriveTrain.DriveMode.TANK) {
      train.tankDrive(-leftJoy.getY(), -rightJoy.getY());
    } else {
      train.arcadeDrive(-leftJoy.getY(), rightJoy.getX());
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
