package com.vicutu.op.query;

import java.util.List;

public class Pagination {

	public final static int PAGESIZE = 30;   
	  
    private int pageSize = PAGESIZE;   
  
    private List<?> items;   
  
    private int totalCount;   
  
    private int[] indexes = new int[1];   
  
    private int startIndex = 0;   
  
    public Pagination(List<?> items, int totalCount){   
        setPageSize(PAGESIZE);   
                setTotalCount(totalCount);   
        setItems(items);           
        setStartIndex(0);   
    }   
  
    public Pagination(List<?> items, int totalCount, int startIndex)  {   
                setPageSize(PAGESIZE);   
        setTotalCount(totalCount);   
        setItems(items);           
        setStartIndex(startIndex);   
    }   
  
    public Pagination(List<?> items, int totalCount, int pageSize, int startIndex)  {   
                setPageSize(pageSize);   
        setTotalCount(totalCount);   
        setItems(items);   
        setStartIndex(startIndex);   
    }   
  
    public List<?> getItems()  {   
        return items;   
    }   
  
    public void setItems(List<?> items)  {   
        this.items = items;   
    }   
  
    public int getPageSize()  {   
        return pageSize;   
    }   
  
    public void setPageSize(int pageSize)  {   
        this.pageSize = pageSize;   
    }   
  
    public int getTotalCount()  {   
        return totalCount;   
    }
    
    public int getPageCount(){
    	return indexes.length;
    }
    
    public int getCurrentPageNo(){
    	return this.getCurrentPageIndex()+1;
    }
  
    public int getCurrentPageIndex(){
    	for(int i=0;i<indexes.length;i++){
    		if(indexes[i]==startIndex){
    			return i;
    		}
    	}
    	return 0;
    }
    public void setTotalCount(int totalCount)  {   
        if (totalCount > 0)  {   
            this.totalCount = totalCount;   
            int count = totalCount / pageSize;   
            if (totalCount % pageSize > 0)   
                count++;   
            indexes = new int[count];   
            for (int i = 0; i < count; i++)  {   
                indexes[i] = pageSize * i;   
            }   
        } else  {   
            this.totalCount = 0;   
        }   
    }   
  
    public int[] getIndexes()  {   
        return indexes;   
    }   
  
    public void setIndexes(int[] indexes)  {   
        this.indexes = indexes;   
    }   
  
    public int getStartIndex()  {   
        return startIndex;   
    }   
  
    public void setStartIndex(int startIndex)  {   
        if (totalCount <= 0)   
            this.startIndex = 0;   
        else if (startIndex >= totalCount)   
            this.startIndex = indexes[indexes.length - 1];   
        else if (startIndex < 0)   
            this.startIndex = 0;   
        else  {   
            this.startIndex = indexes[startIndex / pageSize];   
        }   
    }   
  
    public int getNextIndex()  {   
        int nextIndex = getStartIndex() + pageSize;   
        if (nextIndex >= totalCount)   
            return getStartIndex();   
        else  
            return nextIndex;   
    }   
  
    public int getPreviousIndex() {   
        int previousIndex = getStartIndex() - pageSize;   
        if (previousIndex < 0)   
            return 0;   
        else  
            return previousIndex;   
    }
    
    public int getFirstIndex(){
    	return indexes[0];
    }
    
    public int getLastIndex(){
    	return indexes[indexes.length-1];
    }
}
