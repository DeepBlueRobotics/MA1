// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorErrors.TemperatureLimit;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  public static final double NORMAL_INTAKE_SPEED = 0.8;
  public static final double SLOW_INTAKE_SPEED = 0.05;

  private final CANSparkMax motorL = MotorControllerFactory.createSparkMax(Constants.leftIntakeMotorPort, TemperatureLimit.NEO);
  private final CANSparkMax motorR = MotorControllerFactory.createSparkMax(Constants.rightIntakeMotorPort, TemperatureLimit.NEO);

  public Intake() {
    motorR.setInverted(true);
  }

  public void setIntakeMotors(double speed) {
    motorL.set(speed);
    motorR.set(speed);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Normal Intake", NORMAL_INTAKE_SPEED);
    SmartDashboard.putNumber("Slow Intake", SLOW_INTAKE_SPEED);
    SmartDashboard.putNumber("Normal Outake", -NORMAL_INTAKE_SPEED);
    SmartDashboard.putNumber("Slow Outake", -SLOW_INTAKE_SPEED);
  }
}
