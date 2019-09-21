package strandkrabbe.ts3.viewer;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Log {
	
	private static PrintStream out = System.out;
	private static PrintStream err = System.err;
	private static boolean debug = true;
	public static void setOutputStream(PrintStream output)	{
		out = output;
	}
	public static void setErrorStream(PrintStream error)	{
		err = error;
	}
	public static void setDebug(boolean u)	{
		debug = u;
	}
	
	public static void out(String v)	{
		out.println(getTimeString() + " [Info] " + v);
	}
	public static void info(String v)	{
		out(v);
	}
	public static void warn(String v)	{
		out.println(getTimeString() + " [Warn] " + v);
	}
	public static void error(String v)	{
		err.println(getTimeString() + " [ERROR] " + v);
	}
	public static void debug(String v)	{
		if (debug) out.println(getTimeString() + " [debug] " + v);
	}
	public static void debug(String v,String caller)	{
		if (debug) out.println(getTimeString() + " [debug] [" + caller + "] " + v);
	}
	private static String getTimeString()	{
		Date now = Calendar.getInstance().getTime();
		return "[" + new SimpleDateFormat("HH:mm:ss:SSS").format(now) + "]";
	}
	public static void debug(Exception e)	{
		if (debug)
			out.println(getTimeString() + " [debug] " + e.getClass().getName() + " : " + e.getMessage());
	}
}
