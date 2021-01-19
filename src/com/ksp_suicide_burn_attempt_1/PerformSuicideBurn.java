package com.ksp_suicide_burn_attempt_1;

import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.KRPC;
import krpc.client.services.SpaceCenter;
import krpc.client.services.SpaceCenter.Vessel;
import krpc.client.services.SpaceCenter.Control;
import krpc.client.services.SpaceCenter.Parts;
import krpc.client.services.SpaceCenter.Part;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PerformSuicideBurn {

    public static void main(String[] args) throws IOException, RPCException {
	    try (Connection connection = Connection.newInstance()) {
	        KRPC krpc = KRPC.newInstance(connection);
            System.out.println("Connected to kRPC version " + krpc.getStatus().getVersion());

            SpaceCenter spaceCenter = SpaceCenter.newInstance(connection);
            Vessel activeVessel = spaceCenter.getActiveVessel();
            Control control = activeVessel.getControl();

            // getting a name
            System.out.println(activeVessel.getName());

            // initiating start stage
            System.out.println("initiating ignition and retracting legs");
//            control.setThrottle((float) 1.0);
//            control.activateNextStage();
            control.setLegs(!control.getLegs());
            control.setRCS(true);
            System.out.println("====================");
            Parts parts = activeVessel.getParts();
            List<Part> partList = parts.withName("Grid Fin S");
            Iterator<Part> partIterator = partList.iterator();
            while (partIterator.hasNext()) {
                var next = partIterator.next();
                try {
                    System.out.println(next.getName());

                    if (next instanceof Part) {
                        System.out.println("it's a part");
                    }
                } catch (Exception e) {
                    System.out.println("an exception was thrown");
                }
            }


            // shut off engine at 1000 m

        }
    }
}