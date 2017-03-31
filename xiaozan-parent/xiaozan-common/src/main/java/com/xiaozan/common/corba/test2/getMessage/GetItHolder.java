package com.xiaozan.common.corba.test2.getMessage;

/**
 * Generated from IDL interface "GetIt".
 *
 * @author JacORB IDL compiler V 3.8
 * @version generated at 2016-11-25 10:52:17
 */

public final class GetItHolder	implements org.omg.CORBA.portable.Streamable{
	 public GetIt value;
	public GetItHolder()
	{
	}
	public GetItHolder (final GetIt initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return GetItHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = GetItHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		GetItHelper.write (_out,value);
	}
}
