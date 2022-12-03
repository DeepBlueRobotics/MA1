// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;


public class TimedCommand extends ParallelRaceGroup {
  public TimedCommand(CommandBase command, double timeLimit) {
    super(command,new WaitCommand(timeLimit));}
}
