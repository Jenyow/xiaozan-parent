package com.xiaozan.common.corba.test1.getMessage;


/**
* getMessage/GetItHelper.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��getMessage.idl
* 2016��11��21�� ����һ ����04ʱ52��51�� CST
*/

abstract public class GetItHelper
{
  private static String  _id = "IDL:getMessage/GetIt:1.0";

  public static void insert (org.omg.CORBA.Any a, GetIt that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static GetIt extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (GetItHelper.id (), "GetIt");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static GetIt read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_GetItStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, GetIt value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static GetIt narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof GetIt)
      return (GetIt)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      _GetItStub stub = new _GetItStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static GetIt unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof GetIt)
      return (GetIt)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      _GetItStub stub = new _GetItStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}