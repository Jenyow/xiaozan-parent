package com.xiaozan.common.corba.test1.getMessage;


/**
* getMessage/GetItPOA.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��getMessage.idl
* 2016��11��21�� ����һ ����04ʱ52��51�� CST
*/

public abstract class GetItPOA extends org.omg.PortableServer.Servant
 implements GetItOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("returnString", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // getMessage/GetIt/returnString
       {
         String str = in.read_string ();
         String $result = null;
         $result = this.returnString (str);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:getMessage/GetIt:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public GetIt _this() 
  {
    return GetItHelper.narrow(
    super._this_object());
  }

  public GetIt _this(org.omg.CORBA.ORB orb) 
  {
    return GetItHelper.narrow(
    super._this_object(orb));
  }


} // class GetItPOA
