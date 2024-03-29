// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Dashboard;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {

  private final WPI_TalonFX intake = new WPI_TalonFX(IntakeConstants.kIntakePort, "CANivore");
  private double intakeSpeed = 0;
  public boolean stop = false;

  public boolean outtake = false;

  public Intake() {
    intake.configFactoryDefault();
  }

  // just speed should be fine, motor voltage unecessary

  public void setSpeed(double speed) {
    intakeSpeed = speed;
    intake.set(intakeSpeed);
  }

  public void setVoltage(double voltage) {
    intake.setVoltage(voltage);
  }

  public void moveIn() {
    setSpeed(IntakeConstants.kMaxIntakeSpeed);
    intakeSpeed = IntakeConstants.kMaxIntakeSpeed;
  }

  public void moveOut() {
    // setSpeed(-IntakeConstants.kMaxIntakeSpeed);
    setSpeed(IntakeConstants.kMaxOuttakeSpeed);
    intakeSpeed = -IntakeConstants.kMaxIntakeSpeed; 
  }

  public void stop() {
    stop = true;
    setSpeed(0.000);
    intakeSpeed = 0;
  }

  public double getActualVelocity() {
    return Math.abs(intake.getSelectedSensorVelocity());
  }

  public double getActualCurrent() {
    return intake.getStatorCurrent();
  }

  public boolean intakeEmpty() {
    return getActualVelocity() > IntakeConstants.threshold(intakeSpeed);
  }

  public boolean pieceHeld() {
    return getActualVelocity() < IntakeConstants.pieceHeldThreshold;
  }

  public void holdObject() {
    intake.setVoltage(IntakeConstants.kHoldSpeed * 12.0000);
  }

  @Override
  public void periodic() {
    Dashboard.Intake.Debugging.putNumber("Intake Velocity", getActualVelocity());
    Dashboard.Intake.Debugging.putNumber("Intake Speed", intakeSpeed);
    Dashboard.Intake.Debugging.putBoolean("Intake Object Held", pieceHeld());
    Dashboard.Intake.Debugging.putNumber("Intake Current", intake.getStatorCurrent());
    System.out.println("Current: " + intake.getStatorCurrent());
  }
}
