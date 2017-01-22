package com.noboll.core.cache.entity;

public class CacheOption {
	/**
     * 过期时间
     */
    private long expireTime;

    /**
     * 过期类型
     */
    private ExpireType expireType;

    /**
     * 同步
     */
    private boolean async;

    public CacheOption(){
        // 默认为秒
        expireType = ExpireType.SECONDS;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {

        this.expireTime = expireTime;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public ExpireType getExpireType() {
        return expireType;
    }

    public void setExpireType(ExpireType expireType) {
        this.expireType = expireType;
    }
}
