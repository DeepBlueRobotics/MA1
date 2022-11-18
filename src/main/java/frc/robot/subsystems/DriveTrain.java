// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorErrors.TemperatureLimit;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private final CANSparkMax motorL = MotorControllerFactory.createSparkMax(Constants.leftDriveMotorPort, TemperatureLimit.NEO);
  private final CANSparkMax motorR = MotorControllerFactory.createSparkMax(Constants.rightDriveMotorPort, TemperatureLimit.NEO);

  private final DifferentialDrive differentialDrive = new DifferentialDrive(motorL, motorR);

  private final RelativeEncoder encoderL = motorL.getEncoder();
  private final RelativeEncoder encoderR = motorR.getEncoder();

  public enum DriveMode {TANK, ARCADE}
  public DriveMode mode = DriveMode.TANK;

  public DriveTrain() {
    motorR.setInverted(true);

    encoderL.setPositionConversionFactor(Constants.greenWheelCircumference);
    encoderR.setPositionConversionFactor(Constants.greenWheelCircumference);
  }

  public double getDistance() {
    return (encoderL.getPosition() + encoderR.getPosition()) / 2.0;
  }

  public void resetEncoders() {
    encoderL.setPosition(0);
    encoderR.setPosition(0);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    differentialDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void arcadeDrive(double speed, double rotation) {
    differentialDrive.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() { }
}


/*

You could also implement arcade drive mode. coding is very similar to tank mode. Also prepare smartdashboard values
for testing purposes (such as motor speed and other values that u think are necessary)

Also ur forward and stop methods in the drivetrain subsystem technically don't work since your periodic method
will make the drivetrain drive at the speed of your joysticks right after it tells it to stop or go forward.
(That is the con of putting stuff in periodic, you can create a command and set it as a default command, which i
think was wut i told u to do originally but i told u u could just put it in periodic which is my bad) Personally I
think those methods are unnecessary in the first place and you don't really need them. If you wanna make the robot
stop just make joysticks stay still, no need for it to be mapped to a button or anything like that.
*/
