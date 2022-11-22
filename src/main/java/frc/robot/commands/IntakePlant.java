// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakePlant extends CommandBase {
  private final Intake intake;
  private final RelativeEncoder encoder;
  private final double speed;

  public IntakePlant(Intake intake, double speed) {
    addRequirements(intake);
    this.intake = intake;
    this.encoder = intake.getEncoder();
    this.speed = speed;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intake.setIntakeMotors(speed);
  }

  @Override
  public void end(boolean interrupted) {
    intake.setIntakeMotors(0);
  }

  @Override
  public boolean isFinished() {
    return encoder.getVelocity() < Intake.SUCKY_VELOCITY_THRESHHOLD;
  }
}
