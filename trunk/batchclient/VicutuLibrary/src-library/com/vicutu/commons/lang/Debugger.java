package com.vicutu.commons.lang;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class Debugger {
	private boolean enable = true;

	public Debugger() {
	}

	public Debugger(boolean enable) {
		this.enable = enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	protected void print(String message) {
		if (enable) {
			System.err.print(message == null ? "{null}" : message);
		}
	}

	protected void print(Object object) {
		if (enable) {
			print(object == null ? null : object.toString());
		}
	}

	protected void print(boolean value) {
		if (enable) {
			print(String.valueOf(value));
		}
	}

	protected void print(int value) {
		if (enable) {
			print(String.valueOf(value));
		}
	}

	protected void print(long value) {
		if (enable) {
			print(String.valueOf(value));
		}
	}

	protected void print(double value) {
		if (enable) {
			print(String.valueOf(value));
		}
	}

	protected void print(float value) {
		if (enable) {
			print(String.valueOf(value));
		}
	}

	protected void print(byte value) {
		if (enable) {
			print(String.valueOf(value));
		}
	}

	public void println() {
		if (enable) {
			System.err.println();
		}
	}

	public void println(Throwable ex) {
		if (enable) {
			ex.printStackTrace();
		}
	}

	public void println(String message) {
		if (enable) {
			System.err.println(message == null ? "{null}" : message);
		}
	}

	public void println(Object object) {
		if (enable) {
			if (object == null) {
				println("{null}");
			} else {
				if (object instanceof Object[]) {
					println((Object[]) object);
				} else if (object instanceof List) {
					println((List) object);
				} else if (object instanceof Map) {
					println((Map) object);
				} else {
					println(object.toString());
				}
			}
		}
	}

	public void println(List list) {
		if (enable) {
			if (list.size() > 0) {
				for (int i = 0, size = list.size(); i < size; i++) {
					println(list.get(i));
				}
			} else {
				println("{empty}");
			}
		}
	}

	public void println(Map map) {
		if (enable) {
			if (!map.isEmpty()) {
				Iterator iter = map.keySet().iterator();
				while (iter.hasNext()) {
					Object key = iter.next();
					Object value = map.get(key);
					println("[key] = " + key + ", [value] = " + value);
				}
			} else {
				println("{empty}");
			}
		}
	}

	public void println(boolean value) {
		if (enable) {
			println(String.valueOf(value));
		}
	}

	public void println(int value) {
		if (enable) {
			println(String.valueOf(value));
		}
	}

	public void println(long value) {
		if (enable) {
			println(String.valueOf(value));
		}
	}

	public void println(double value) {
		if (enable) {
			println(String.valueOf(value));
		}
	}

	public void println(float value) {
		if (enable) {
			println(String.valueOf(value));
		}
	}

	public void println(byte value) {
		if (enable) {
			println(String.valueOf(value));
		}
	}

	public void println(Object[] array) {
		if (enable) {
			if (array.length > 0) {
				for (int i = 0; i < array.length; i++) {
					println(array[i]);
				}
			} else {
				println("{empty}");
			}
		}
	}

	public void println(byte[] array) {
		if (enable) {
			println(new String(array));
		}
	}

	public void println(char[] array) {
		if (enable) {
			println(new String(array));
		}
	}

	public void println(int[] array) {
		if (enable) {
			for (int i = 0; i < array.length; i++) {
				if (i > 0) {
					print(", ");
				}
				print(array[i]);
			}
			println();
		}
	}

	public void println(double[] array) {
		if (enable) {
			for (int i = 0; i < array.length; i++) {
				if (i > 0) {
					print(", ");
				}
				print(array[i]);
			}
			println();
		}
	}

	public void println(boolean[] array) {
		if (enable) {
			for (int i = 0; i < array.length; i++) {
				if (i > 0) {
					print(", ");
				}
				print(array[i]);
			}
			println();
		}
	}

	public void println(long[] array) {
		if (enable) {
			for (int i = 0; i < array.length; i++) {
				if (i > 0) {
					print(", ");
				}
				print(array[i]);
			}
			println();
		}
	}
}