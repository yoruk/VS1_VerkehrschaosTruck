package verkehrschaos;

/**
* verkehrschaos/StreetsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from verkehrschaos.idl
* Freitag, 17. April 2015 12:12 Uhr MESZ
*/

public final class StreetsHolder implements org.omg.CORBA.portable.Streamable
{
  public verkehrschaos.Streets value = null;

  public StreetsHolder ()
  {
  }

  public StreetsHolder (verkehrschaos.Streets initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = verkehrschaos.StreetsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    verkehrschaos.StreetsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return verkehrschaos.StreetsHelper.type ();
  }

}