package org.usfirst.frc.team6882.interfaces;

import org.usfirst.frc.team6882.interfaces.GreenLight;
import org.usfirst.frc.team6882.globals.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer.*;


public class CameraProcessing {
    //Class variables


    public static void config() {
        // Adds the table
        NetworkTable newTable;
        //MjpegServer mjpegServer1 = new MjpegServer("camera thing", 0);

    }

    //Starts the camera stream
    public static void start() {

        // Starts camera feed
        CameraServer.getInstance().addCamera(hardware.visionCamera);
        CameraServer.getInstance().startAutomaticCapture(hardware.visionCamera);
        CvSink cvSink = CameraServer.getInstance().getVideo(hardware.visionCamera);
        CameraServer.getInstance().putVideo("Blur", 640, 480);
    }
}