package com.vicutu.persistence.query;

import java.util.List;

public class Pagination
{
	private int limit = 30;

	private List<?> items;

	private int total;

	private int[] indexes = new int[1];

	private int start = 0;

	public Pagination(List<?> items, int totalCount)
	{
		setTotal(totalCount);
		setItems(items);
		setStart(0);
	}

	public Pagination(List<?> items, int totalCount, int startIndex)
	{
		setTotal(totalCount);
		setItems(items);
		setStart(startIndex);
	}

	public Pagination(List<?> items, int total, int limit, int start)
	{
		setLimit(limit);
		setTotal(total);
		setItems(items);
		setStart(start);
	}

	public List<?> getItems()
	{
		return items;
	}

	public void setItems(List<?> items)
	{
		this.items = items;
	}

	public int getLimit()
	{
		return limit;
	}

	public void setLimit(int pageSize)
	{
		this.limit = pageSize;
	}

	public int getTotal()
	{
		return total;
	}

	public int getPageCount()
	{
		return indexes.length;
	}

	public int getCurrentPageNo()
	{
		return this.getCurrentPageIndex() + 1;
	}

	public int getCurrentPageIndex()
	{
		for (int i = 0; i < indexes.length; i++)
		{
			if (indexes[i] == start)
			{
				return i;
			}
		}
		return 0;
	}

	public void setTotal(int total)
	{
		if (total > 0)
		{
			this.total = total;
			int count = total / limit;
			if (total % limit > 0)
				count++;
			indexes = new int[count];
			for (int i = 0; i < count; i++)
			{
				indexes[i] = limit * i;
			}
		}
		else
		{
			this.total = 0;
		}
	}

	public int[] getIndexes()
	{
		return indexes;
	}

	public void setIndexes(int[] indexes)
	{
		this.indexes = indexes;
	}

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		if (total <= 0)
			this.start = 0;
		else if (start >= total)
			this.start = indexes[indexes.length - 1];
		else if (start < 0)
			this.start = 0;
		else
		{
			this.start = indexes[start / limit];
		}
	}

	public int getNextIndex()
	{
		int nextIndex = getStart() + limit;
		if (nextIndex >= total)
			return getStart();
		else
			return nextIndex;
	}

	public int getPreviousIndex()
	{
		int previousIndex = getStart() - limit;
		if (previousIndex < 0)
			return 0;
		else
			return previousIndex;
	}

	public int getFirstIndex()
	{
		return indexes[0];
	}

	public int getLastIndex()
	{
		return indexes[indexes.length - 1];
	}
}
