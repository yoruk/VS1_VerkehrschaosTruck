package verkehrschaostruck;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import verkehrschaos.*;

public class TruckMain {

	public static void main(String[] args) {
		System.out.println(args[0] + "," + args[1] + "," + args[2] + ","
				+ args[3]);

		try {
			/* ORB Eigenschaften setzen */
			Properties probs = new Properties();
			probs.put("org.omg.CORBA.ORBInitialPort", args[0]);
			probs.put("org.omg.CORBA.ORBInitialHost", args[1]);
			ORB orb = ORB.init(args, probs);

			POA rootPoa = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));
			rootPoa.the_POAManager().activate();

			/*
			 * Company vom NameService holen, damit ich meinen Truck erstellen
			 * kann
			 */
			NamingContextExt nc = NamingContextExtHelper.narrow(orb
					.resolve_initial_references("NameService"));
			org.omg.CORBA.Object obj = nc.resolve_str(args[3]);

			TruckCompany company = TruckCompanyHelper.narrow(obj);

			/* Erstellung meines Trucks mit Name des LKWs und Company */
			TruckImpl truck = new TruckImpl(args[2]);

			/* Objektref holen */
			org.omg.CORBA.Object ref = rootPoa.servant_to_reference(truck);
			Truck href = TruckHelper.narrow(ref);

			company.addTruck(href);

			/* F�r Namensdienst Namen vergeben und rebinden */
			String name = args[2];
			NameComponent path[] = nc.to_name(name);
			nc.rebind(path, href);

			System.out.println("LKW ready and waiting....");

			orb.run();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fehler");
			System.exit(1);
		}

	}

}
