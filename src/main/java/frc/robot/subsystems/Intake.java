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
  // public static final double NORMAL_INTAKE_SPEED = 0.8;
  // public static final double SLOW_INTAKE_SPEED = 0.05;
  // public static final double NORMAL_ROLLER_SPEED = 0.6;
  // moved to enum IntakeSpeed

  private final CANSparkMax motorL = MotorControllerFactory.createSparkMax(Constants.leftIntakeMotorPort, TemperatureLimit.NEO);
  private final CANSparkMax motorR = MotorControllerFactory.createSparkMax(Constants.rightIntakeMotorPort, TemperatureLimit.NEO);
  private final CANSparkMax roller = MotorControllerFactory.createSparkMax(Constants.intakeRollerMotorPort, TemperatureLimit.NEO);

  public Intake() {
    motorR.setInverted(true);
  }

  public void setIntakeMotors(IntakeSpeed speed) {
    motorL.set(speed.value);
    motorR.set(speed.value);
  }

  public void setRollerMotor(IntakeSpeed speed) {
    roller.set(speed.value);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Normal Intake", Intakespeed.normalIn.value);
    SmartDashboard.putNumber("Slow Intake", Intakespeed.SlowIn.value);
    SmartDashboard.putNumber("Roller In", Intakespeed.rollerIn.value);
    SmartDashboard.putNumber("Roller Out", Intakespeed.rollerIn.value);
    SmartDashboard.putNumber("Normal Outake", Intakespeed.normalOut.value);
    SmartDashboard.putNumber("Slow Outake", Intakespeed.slowOut.value);
  }

  public static enum IntakeSpeed{
      normalIn(.8*Constants.greenWheelCircumference),
      slowIn(.05*Constants.greenWheelCircumference),
      normalOut(-.8*Constants.greenWheelCircumference),
      slowOut(-.05*Constants.greenWheelCircumference),//for stacking/placing blocks
      rollerIn(.6*Constants.greenWheelCircumference),
      rollerOut(0.0375*Constants.greenWheelCircumference),
      stop(0.0);

      

      public final double value;

      private IntakeSpeed(double value) {
          this.value = value;
      }
    };
}
