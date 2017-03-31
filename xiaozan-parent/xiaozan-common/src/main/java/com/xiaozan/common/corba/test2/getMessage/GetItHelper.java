package com.xiaozan.common.corba.test2.getMessage;


/**
 * Generated from IDL interface "GetIt".
 *
 * @author JacORB IDL compiler V 3.8
 * @version generated at 2016-11-25 10:52:17
 */

public abstract class GetItHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(GetItHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:getMessage/GetIt:1.0", "GetIt");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final GetIt s)
	{
			any.insert_Object(s);
	}
	public static GetIt extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:getMessage/GetIt:1.0";
	}
	public static GetIt read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(_GetItStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final GetIt s)
	{
		_out.write_Object(s);
	}
	public static GetIt narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof GetIt)
		{
			return (GetIt)obj;
		}
		else if (obj._is_a("IDL:getMessage/GetIt:1.0"))
		{
			_GetItStub stub;
			stub = new _GetItStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static GetIt unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof GetIt)
		{
			return (GetIt)obj;
		}
		else
		{
			_GetItStub stub;
			stub = new _GetItStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
