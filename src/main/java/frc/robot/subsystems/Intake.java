// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {

  private final WPI_TalonFX intake = new WPI_TalonFX(IntakeConstants.kIntakePort);

  public Intake() {
    intake.configFactoryDefault();
  }

  // just speed should be fine, motor voltage unecessary

  public void setSpeed(double speed) {
    intake.set(speed);
  }

  public void moveIn() {
    setSpeed(IntakeConstants.kMaxIntakeSpeed);
  }

  public void moveOut() {
    setSpeed(-IntakeConstants.kMaxIntakeSpeed); 
  }

  public void stop() {
    setSpeed(0.000);
  }

  public double getActualVelocity() {
    return intake.getSelectedSensorVelocity();
  }

  public boolean objectHeld() {
    // double expected = getExpectedVelocity();
    // double actual = getActualVelocity();
    // double ratio = Math.abs(expected/actual);
    // return ratio > IntakeConstants.kObjectHeldRatioThreshold;
    return getActualVelocity() > IntakeConstants.kLowThreshold;
  }

  public boolean speedUp() {
    return getActualVelocity() > IntakeConstants.kHighThreshold;
  }

  public boolean objectOut() {
    return getActualVelocity() < -IntakeConstants.kHighThreshold;
  }

  @Override
  public void periodic() {
  }
}
