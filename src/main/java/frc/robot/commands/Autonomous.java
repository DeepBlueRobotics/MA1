// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Autonomous extends SubsystemBase {
  /** Creates a new Autonomous. */
  public Autonomous() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void motorSpeed()
{
motorL.set(0.9);
motorR.set(0.9);
}
  if(getposition()>= 2)
{
  motorL.set(0);
  motorR.set(0);
}

