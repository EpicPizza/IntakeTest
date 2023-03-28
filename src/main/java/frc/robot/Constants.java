// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static final class IntakeConstants {
    public static final int kIntakePort = 22; // ??
    public static final double kMaxIntakeSpeed = 0.4; // ??
    public static final double kMaxOuttakeSpeed = -0.15; // ??
    public static final double kHoldSpeed = 0.07;

    public static double kSpeedUpFailTime = 0.75; //seconds it tries to speed up
    public static double kOutFailTime = 0.75; //seconds it tries to outtake

    public static double pieceHeldThreshold = 500;
    
    public static double threshold(double speed) {
        speed = Math.abs(speed);
        return (-2077.380952 * Math.sqrt(speed)) + (23166.07143 * speed) - 1603.75;
    }
}

  public static class ArmConstants {
    public static double speedLimit = 0.2;
    public static double deadband = 0.15;
  }

  public static final class DashbaordConstants {
    public static boolean SwerveDebugging = true;
    public static boolean SwerveDriver = true;
    public static boolean ArmDebugging = true;
    public static boolean ArmDriver = true;
    public static boolean ElevatorDebugging = true;
    public static boolean ElevatorDriver = true;
    public static boolean IntakeDebugging = true;
    public static boolean IntakeDriver = true;
    public static boolean AutoDebugging = true;
    public static boolean AutoDriver = true;
    public static boolean TeleDebugging = true;
    public static boolean TeleDriver = true;
    public static boolean LimelightDebugging = true;
    public static boolean LimelightDriver = true;
}
}
