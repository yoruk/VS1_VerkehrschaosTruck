package verkehrschaostruck;

import verkehrschaos.TruckCompany;
import verkehrschaos.TruckPOA;

public class TruckImpl extends TruckPOA {
	private String name;
	private TruckCompany company;
	private double x;
	private double y;
	
	public TruckImpl(String name) {
		super();
		this.name = name;
	}
	
	@Override
	/* Gibt den Namen des LKWs. */
	public String getName() {
		return name;
	}

	@Override
	/* Gibt die zugeordnete Spedition */
	public TruckCompany getCompany() {
		return company;
	}

	@Override
	/* Neue Zuordnung einer Spedition.
     * Wird von der zustaendigen Spedition aufgerufen. 
     * Zu Debug-Zwecken soll der Name der neuen Spedition auf der Konsole ausgegeben werden. 
     */
	public void setCompany(TruckCompany company) {
		this.company = company;
	}

	@Override
	/* Informiert den LKW waehrend einer Fahrt ueber die aktuelle Position.
     * Position kann auf der Konsole ausgegeben werden.
     * Wird von Streets aufgerufen.
     */
	public void setCoordinate(double x, double y) {
		this.x =  x;
		this.y = y;
	}

	@Override
	/*
     * Stilllegung des LKWs (LKW Anwendung wird beendet).
     * Wird z. B. von zugeordneter Spedition aufgerufen, wenn diese still gelegt wird
     * oder von der Steueranwendung (Client).
     * Beenden der Anwendung durch Aufruf von orb.shutdown(true).
     * Nach orb.shutdown kleine Pause einlegen (0.5 sec) um Exception zu vermeiden.
     */
	public void putOutOfService() {
		System.exit(0);
	}

}
