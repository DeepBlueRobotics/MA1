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
  public static double slowInMotorSpeed = -.05*Constants.greenWheelCircumference;
  public static double normalInMotorSpeed = .8*Constants.greenWheelCircumference;
  public static double normalOutMotor = -.8*Constants.greenWheelCircumference;
  public static double rollerIntake = .6*Constants.greenWheelCircumference;
  public static double rollerOutake = 0.0375*Constants.greenWheelCircumference;
  public static double slowOutSpeed = -.05*Constants.greenWheelCircumference;


  private final CANSparkMax motorL = MotorControllerFactory.createSparkMax(Constants.leftIntakeMotorPort, TemperatureLimit.NEO);
  private final CANSparkMax motorR = MotorControllerFactory.createSparkMax(Constants.rightIntakeMotorPort, TemperatureLimit.NEO);
  private final CANSparkMax roller = MotorControllerFactory.createSparkMax(Constants.intakeRollerMotorPort, TemperatureLimit.NEO);

  public Intake() {
    motorR.setInverted(true);
    SmartDashboard.putNumber("Normal Intake", IntakeSpeed.normalIn.value);
    SmartDashboard.putNumber("Slow Intake", IntakeSpeed.slowIn.value);
    SmartDashboard.putNumber("Roller In", IntakeSpeed.rollerIn.value);
    SmartDashboard.putNumber("Roller Out", IntakeSpeed.rollerOut.value);
    SmartDashboard.putNumber("Normal Outake", IntakeSpeed.normalOut.value);
    SmartDashboard.putNumber("Slow Outake", IntakeSpeed.slowOut.value);
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
    double normalInMotorSpeed = SmartDashboard.getNumber("Normal Intake",.8*Constants.greenWheelCircumference);
    double slowInMotorSpeed = SmartDashboard.getNumber("Slow Intake",.05*Constants.greenWheelCircumference);
    double rollerIntake = SmartDashboard.getNumber("Roller In",.6*Constants.greenWheelCircumference);
    double rollerOutake = SmartDashboard.getNumber("Roller Out",0.0375*Constants.greenWheelCircumference);
    double slowOutSpeed = SmartDashboard.getNumber("Slow Outake",-.05*Constants.greenWheelCircumference);
    double normalOutMotorSpeed = SmartDashboard.getNumber("Normal Outake",-.8*Constants.greenWheelCircumference);
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
