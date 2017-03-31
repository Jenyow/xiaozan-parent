package com.xiaozan.common.corba.test2.getMessage;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "GetIt".
 *
 * @author JacORB IDL compiler V 3.8
 * @version generated at 2016-11-25 10:52:17
 */

public class GetItPOATie
	extends GetItPOA
{
	private GetItOperations _delegate;

	private POA _poa;
	public GetItPOATie(GetItOperations delegate)
	{
		_delegate = delegate;
	}
	public GetItPOATie(GetItOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
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
	public GetItOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(GetItOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public java.lang.String returnString(java.lang.String str)
	{
		return _delegate.returnString(str);
	}

}
