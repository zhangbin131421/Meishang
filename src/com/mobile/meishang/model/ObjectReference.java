package com.mobile.meishang.model;

public class ObjectReference<T>
{

    private T referent;

    public T getReferent()
    {
        return referent;
    }

    public void setReferent(T referent)
    {
        this.referent = referent;
    }

    /**
     * 
     * @param r
     */
    public ObjectReference(T r)
    {
        super();
        referent = r;
    }

    /**
     * 
     */
    public ObjectReference()
    {
        super();
    }
}