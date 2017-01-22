package com.noboll.core.base.entity;

import java.util.List;

import com.noboll.core.constants.SystemConstant;

public class Page<T extends BaseEntity>  extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	// 当前页
	private Integer current=1;
	// 每页显示多少行
	private Integer pageSize=SystemConstant.DEFAULT_ROW_COUNT;
	// 总计多少页
	private Integer totalPage;
	/* 总行数*/
	private Integer total;
	// 数据
	private List<T> rows;
	
    public Page() {
    	
    }
    
    public Page(Integer current,Integer pageSize) {
    	if(current==null)
    		current=1;
    	if(pageSize==null)
    		pageSize=SystemConstant.DEFAULT_ROW_COUNT;
    	this.current=current;
    	this.pageSize=pageSize;
    }
    
	public Integer getCurrent() {
		if(current==null)
    		current=1;
		current = current < 1 ? 1 : current;  
		return current;
	}
	public void setCurrent(Integer current) {
		this.current = current;
	}
	public Integer getPageSize() {
		if(pageSize==null)
    		pageSize=SystemConstant.DEFAULT_ROW_COUNT;
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		totalPage = 0;  
        if (pageSize != 0) {  
            totalPage = total / pageSize;  
            if (total % pageSize != 0)  
                totalPage++;  
        }  
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
}
