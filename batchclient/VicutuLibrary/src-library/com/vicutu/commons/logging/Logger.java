package com.vicutu.commons.logging;

public abstract class Logger
{
	private static final Object[] EMPTY = new Object[0];

	public abstract void trace(String message, Object[] args, Throwable t);

	public abstract void debug(String message, Object[] args, Throwable t);

	public abstract void info(String message, Object[] args, Throwable t);

	public abstract void warn(String message, Object[] args, Throwable t);

	public abstract void error(String message, Object[] args, Throwable t);

	public abstract void fatal(String message, Object[] args, Throwable t);

	public void trace(String message)
	{
		trace(message, EMPTY, null);
	}

	public void trace(Throwable t)
	{
		trace(null, EMPTY, t);
	}

	public void trace(String message, Object arg1)
	{
		trace(message, new Object[] { arg1 }, null);
	}

	public void trace(String message, Object arg1, Object arg2)
	{
		trace(message, new Object[] { arg1, arg2 }, null);
	}

	public void trace(String message, Object arg1, Object arg2, Object arg3)
	{
		trace(message, new Object[] { arg1, arg2, arg3 }, null);
	}

	public void trace(String message, Throwable t)
	{
		trace(message, EMPTY, t);
	}

	public void trace(String message, Object arg1, Throwable t)
	{
		trace(message, new Object[] { arg1 }, t);
	}

	public void trace(String message, Object arg1, Object arg2, Throwable t)
	{
		trace(message, new Object[] { arg1, arg2 }, t);
	}

	public void trace(String message, Object arg1, Object arg2, Object arg3, Throwable t)
	{
		trace(message, new Object[] { arg1, arg2, arg3 }, t);
	}

	public void debug(String message)
	{
		debug(message, EMPTY, null);
	}

	public void debug(Throwable t)
	{
		debug(null, EMPTY, t);
	}

	public void debug(String message, Object arg1)
	{
		debug(message, new Object[] { arg1 }, null);
	}

	public void debug(String message, Object arg1, Object arg2)
	{
		debug(message, new Object[] { arg1, arg2 }, null);
	}

	public void debug(String message, Object arg1, Object arg2, Object arg3)
	{
		debug(message, new Object[] { arg1, arg2, arg3 }, null);
	}

	public void debug(String message, Throwable t)
	{
		debug(message, EMPTY, t);
	}

	public void debug(String message, Object arg1, Throwable t)
	{
		debug(message, new Object[] { arg1 }, t);
	}

	public void debug(String message, Object arg1, Object arg2, Throwable t)
	{
		debug(message, new Object[] { arg1, arg2 }, t);
	}

	public void debug(String message, Object arg1, Object arg2, Object arg3, Throwable t)
	{
		debug(message, new Object[] { arg1, arg2, arg3 }, t);
	}

	public void info(String message)
	{
		info(message, EMPTY);
	}

	public void info(Throwable t)
	{
		info(null, EMPTY, t);
	}

	public void info(String message, Object arg1)
	{
		info(message, new Object[] { arg1 }, null);
	}

	public void info(String message, Object arg1, Object arg2)
	{
		info(message, new Object[] { arg1, arg2 }, null);
	}

	public void info(String message, Object arg1, Object arg2, Object arg3)
	{
		info(message, new Object[] { arg1, arg2, arg3 }, null);
	}

	public void info(String message, Throwable t)
	{
		info(message, EMPTY, t);
	}

	public void info(String message, Object arg1, Throwable t)
	{
		info(message, new Object[] { arg1 }, t);
	}

	public void info(String message, Object arg1, Object arg2, Throwable t)
	{
		info(message, new Object[] { arg1, arg2 }, t);
	}

	public void info(String message, Object arg1, Object arg2, Object arg3, Throwable t)
	{
		info(message, new Object[] { arg1, arg2, arg3 }, t);
	}

	public void warn(String message)
	{
		warn(message, EMPTY);
	}
	
	public void warn(Throwable t)
	{
		warn(null, EMPTY, t);
	}

	public void warn(String message, Object arg1)
	{
		warn(message, new Object[] { arg1 }, null);
	}

	public void warn(String message, Object arg1, Object arg2)
	{
		warn(message, new Object[] { arg1, arg2 }, null);
	}

	public void warn(String message, Object arg1, Object arg2, Object arg3)
	{
		warn(message, new Object[] { arg1, arg2, arg3 }, null);
	}

	public void warn(String message, Throwable t)
	{
		warn(message, EMPTY, t);
	}

	public void warn(String message, Object arg1, Throwable t)
	{
		warn(message, new Object[] { arg1 }, t);
	}

	public void warn(String message, Object arg1, Object arg2, Throwable t)
	{
		warn(message, new Object[] { arg1, arg2 }, t);
	}

	public void warn(String message, Object arg1, Object arg2, Object arg3, Throwable t)
	{
		warn(message, new Object[] { arg1, arg2, arg3 }, t);
	}

	public void error(Throwable t)
	{
		error(null, EMPTY, t);
	}

	public void error(String message)
	{
		error(message, EMPTY, null);
	}

	public void error(String message, Object arg1)
	{
		error(message, new Object[] { arg1 }, null);
	}

	public void error(String message, Object arg1, Object arg2)
	{
		error(message, new Object[] { arg1, arg2 }, null);
	}

	public void error(String message, Object arg1, Object arg2, Object arg3)
	{
		error(message, new Object[] { arg1, arg2, arg3 }, null);
	}

	public void error(String message, Throwable t)
	{
		error(message, EMPTY, t);
	}

	public void error(String message, Object arg1, Throwable t)
	{
		error(message, new Object[] { arg1 }, t);
	}

	public void error(String message, Object arg1, Object arg2, Throwable t)
	{
		error(message, new Object[] { arg1, arg2 }, t);
	}

	public void error(String message, Object arg1, Object arg2, Object arg3, Throwable t)
	{
		error(message, new Object[] { arg1, arg2, arg3 }, t);
	}

	public void fatal(Throwable t)
	{
		fatal(null, EMPTY, t);
	}

	public void fatal(String message)
	{
		fatal(message, EMPTY, null);
	}

	public void fatal(String message, Object arg1)
	{
		fatal(message, new Object[] { arg1 }, null);
	}

	public void fatal(String message, Object arg1, Object arg2)
	{
		fatal(message, new Object[] { arg1, arg2 }, null);
	}

	public void fatal(String message, Object arg1, Object arg2, Object arg3)
	{
		fatal(message, new Object[] { arg1, arg2, arg3 }, null);
	}

	public void fatal(String message, Throwable t)
	{
		fatal(message, EMPTY, t);
	}

	public void fatal(String message, Object arg1, Throwable t)
	{
		fatal(message, new Object[] { arg1 }, t);
	}

	public void fatal(String message, Object arg1, Object arg2, Throwable t)
	{
		fatal(message, new Object[] { arg1, arg2 }, t);
	}

	public void fatal(String message, Object arg1, Object arg2, Object arg3, Throwable t)
	{
		fatal(message, new Object[] { arg1, arg2, arg3 }, t);
	}
}