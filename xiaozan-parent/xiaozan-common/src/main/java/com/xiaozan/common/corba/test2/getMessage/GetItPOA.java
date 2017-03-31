package com.xiaozan.common.corba.test2.getMessage;

/**
 * Generated from IDL interface "GetIt".
 *
 * @author JacORB IDL compiler V 3.8
 * @version generated at 2016-11-25 10:52:17
 */

public abstract class GetItPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, GetItOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "returnString", Integer.valueOf(0));
	}
	private String[] ids = {"IDL:getMessage/GetIt:1.0"};
	public GetIt _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		GetIt __r = GetItHelper.narrow(__o);
		return __r;
	}
	public GetIt _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		GetIt __r = GetItHelper.narrow(__o);
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // returnString
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				java.lang.String tmpResult1 = returnString(_arg0);
_out.write_string( tmpResult1 );
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
