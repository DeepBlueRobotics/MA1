// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 * 
 */
public final class Constants {
    public static final int leftDriveMotorPort = 2;
    public static final int rightDriveMotorPort = 3;
    public static final int leftIntakeMotorPort = 9;
    public static final int rightIntakeMotorPort = 8;
    public static final int intakeRollerMotorPort = 7;
    public static final int rampMotorPort = 6;

    public static final int leftJoystickPort = 0;
    public static final int rightJoystickPort = 1;
    public static final int controllerPort = 2;

    public static double wheelCircumference = 0.1; // meters

    public static final class Input {
            public static int X;
            public static int A;
            public static int B;
            public static int Y;
            public static int LB;
            public static int RB;
            public static int LT;
            public static int RT;
            public static int BACK;
            public static int START;

            //TODO: mode button setting to teletop init
            public static void setButtons(XboxController controller) {
                if (controller.getName().equals("Logitech Dual Action")) {
                    // Buttons and triggers
                    X = 1;
                    A = 2;
                    B = 3;
                    Y = 4;
                    LB = 5;
                    RB = 6;
                    LT = 7;
                    RT = 8;
                    BACK = 9;
                    START = 10;
                } else {
                    // Buttons and triggers for xbox controller
                    X = 3;
                    A = 1;
                    B = 2;
                    Y = 4;
                    LB = 5;
                    RB = 6;
                    LT = 7;
                    RT = 8;
                    BACK = 9;
                    START = 10;
                }
            }
    }
}
