package verkehrschaostruck;

import java.util.*;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

import verkehrschaos.*;

public class TruckMain {
	public static void main(String[] args) {
		try {
			// ORB Eigenschaften setzen
			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialPort", args[0]);
			props.put("org.omg.CORBA.ORBInitialHost", args[1]);
			ORB orb = ORB.init(args, props);

			// Referenz zum rootPOA holen und POA Manager aktivieren
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPoa.the_POAManager().activate();
			
			// Erzeuge Servant
			TruckImpl truck = new TruckImpl(args[2]);
			
			// Objekt Referenz des Servants holen
			org.omg.CORBA.Object ref = rootPoa.servant_to_reference(truck);
			
			// Downcast Corba-Objekt -> Truck
			Truck href = TruckHelper.narrow(ref);
			
			// Referenz zum Namensdienst (root naming context holen)
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			
			// Verwendung von NamingContextExt, ist Teil der Interoperable
			// Naming Sevice (INS) Spezifikation.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			// binde die Objekt Referenz an einen Namen
			String name = args[2];
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);
			
			// Referenz auf company vom Namserver holen
			TruckCompany company = TruckCompanyHelper.narrow(ncRef.resolve_str(args[3]));
						
			// Truck bei Company anmelden
			company.addTruck(href);		

			System.out.println("Truck ready and waiting....");

			// Orb starten
			orb.run();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Truck, ERROR");
			System.exit(1);
		}
	}
}
