package com.xiaozan.common.corba.test1.getMessage;

/**
* getMessage/GetItHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��getMessage.idl
* 2016��11��21�� ����һ ����04ʱ52��51�� CST
*/

public final class GetItHolder implements org.omg.CORBA.portable.Streamable
{
  public GetIt value = null;

  public GetItHolder ()
  {
  }

  public GetItHolder (GetIt initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = GetItHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    GetItHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return GetItHelper.type ();
  }

}
