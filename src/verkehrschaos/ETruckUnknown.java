package verkehrschaos;


/**
* verkehrschaos/ETruckUnknown.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from verkehrschaos.idl
* Freitag, 17. April 2015 12:12 Uhr MESZ
*/

public final class ETruckUnknown extends org.omg.CORBA.UserException
{
  public String msg = null;

  public ETruckUnknown ()
  {
    super(ETruckUnknownHelper.id());
  } // ctor

  public ETruckUnknown (String _msg)
  {
    super(ETruckUnknownHelper.id());
    msg = _msg;
  } // ctor


  public ETruckUnknown (String $reason, String _msg)
  {
    super(ETruckUnknownHelper.id() + "  " + $reason);
    msg = _msg;
  } // ctor

} // class ETruckUnknown
