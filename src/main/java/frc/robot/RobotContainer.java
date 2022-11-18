// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Autonomous;
import frc.robot.commands.Drive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.DriveTrain.DriveMode;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Joystick leftJoy = new Joystick(Constants.leftJoystickPort);
  private final Joystick rightJoy = new Joystick(Constants.rightJoystickPort);

  private final DriveTrain driveTrain = new DriveTrain();
  private final Intake intake = new Intake();

  public RobotContainer() {
    configureButtonBindings();

    driveTrain.setDefaultCommand(new Drive(driveTrain, leftJoy, rightJoy));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // normal intaking
    new JoystickButton(leftJoy, 4).whenPressed(() -> {
      intake.setIntakeMotors(Intake.IntakeSpeed.normalIn);
      intake.setRollerMotor(Intake.IntakeSpeed.rollerIn);
    });
    // slow intaking
    new JoystickButton(leftJoy, 5).whenPressed(() -> {
      intake.setIntakeMotors(Intake.IntakeSpeed.slowIn);
      intake.setRollerMotor(Intake.IntakeSpeed.rollerIn);
    });
    // outtaking
    new JoystickButton(leftJoy, 6).whenPressed(() -> {
      intake.setIntakeMotors(Intake.IntakeSpeed.normalOut);
      intake.setRollerMotor(Intake.IntakeSpeed.rollerOut);
    });
    // slow outtaking / stacking
    new JoystickButton(leftJoy, 7).whenPressed(() -> {
      intake.setIntakeMotors(Intake.IntakeSpeed.slowIn);
      intake.setRollerMotor(Intake.IntakeSpeed.rollerOut);
    });
    // stop
    new JoystickButton(leftJoy, 8).whenPressed(() -> {
      intake.setIntakeMotors(Intake.IntakeSpeed.stop);
      intake.setRollerMotor(Intake.IntakeSpeed.stop);
    });
    // switch drive modes
    new JoystickButton(rightJoy, 6).whenPressed(() -> {
      if (driveTrain.mode == DriveMode.TANK) {
        driveTrain.mode = DriveMode.ARCADE;
      } else {
        driveTrain.mode = DriveMode.TANK;
      }
    });
  }

  /**
   * Use this to pass the autonomous command to dsrthe main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new Autonomous(driveTrain);
  }
}
