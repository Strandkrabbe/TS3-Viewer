package strandkrabbe.ts3.viewer;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Log {
	
	private static PrintStream out = System.out;
	private static PrintStream err = System.err;
	private static boolean debug = true;
	private static boolean enable = true;
	private static boolean detailedError = true;
	public static Scanner Sc = new Scanner(System.in);
	
	public static boolean isDebugEnabled()	{
		return debug;
	}
	
	public static void setOutputStream(PrintStream output)	{
		out = output;
	}
	public static void setErrorStream(PrintStream error)	{
		err = error;
	}
	public static void setDebug(boolean u)	{
		debug = u;
	}
	public static void setEnabled(boolean e)	{
		enable = e;
	}
	public static void setDetailedErrors(boolean d)	{
		detailedError = d;
	}
	public static String request(String v)	{
		if (enable)
			out.print(getTimeString() + " [Request] " + v + ": ");
		return Log.Sc.nextLine();
	}
	public static String request(String v,Scanner Sc)	{
		if (enable)
			out.print(getTimeString() + " [Request] " + v + ": ");
		return Sc.nextLine();
	}
	public static String importantRequest(String v)	{
		err.print(getTimeString() + " [Request] " + v + ": ");
		return Log.Sc.nextLine();
	}
	public static void out(String v)	{
		if (enable)
			out.println(getTimeString() + " [Info] " + v);
	}
	public static void out(String v,String caller)	{
		if (enable)
			out.println(getTimeString() + " [Info] [" + caller + "] " + v);
	}
	public static void info(String v)	{
		out(v);
	}
	public static void info(String v,String caller)	{
		out(v, caller);
	}
	public static void warn(String v)	{
		if (enable)
			out.println(getTimeString() + " [Warn] " + v);
	}
	public static void warn(String v,String c)	{
		if (enable)
			out.println(getTimeString() + " [Warn] [" + c + "] " + v);
	}
	public static void error(String v)	{
		if (enable)	{
			err.println(getTimeString() + " [ERROR] " + v);
		}
	}
	public static void error(String v,String caller)	{
		if (enable)	{
			err.println(getTimeString() + " [ERROR] [" + caller + "] " + v);
		}
	}
	public static void error(Exception e)	{
		if (enable)	{
			if (!detailedError)
				err.println(getTimeString() + " [ERROR] " + e.getClass().getName() + " : " + e.getMessage());
			else
				e.printStackTrace(err);
		}
	}
	public static void debug(String v)	{
		if (debug && enable) out.println(getTimeString() + " [debug] " + v);
	}
	public static void debug(String v,String caller)	{
		if (debug && enable) out.println(getTimeString() + " [debug] [" + caller + "] " + v);
	}
	private static String getTimeString()	{
		Date now = Calendar.getInstance().getTime();
		return "[" + new SimpleDateFormat("HH:mm:ss:SSS").format(now) + "]";
	}
	public static void debug(Exception e)	{
		if (debug && enable)
			out.println(getTimeString() + " [debug] " + e.getClass().getName() + " : " + e.getMessage());
	}
	
	public static void important(String msg,String caller)	{
		err.println(getTimeString() + " [!!!] [" + caller + "] " + msg);
	}
	
}
