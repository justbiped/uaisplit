package com.hotmart.locations.core.tools;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0003J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\fH\u0017J\u0018\u0010\r\u001a\u00020\b2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\fH\u0017J\u0018\u0010\u000e\u001a\u00020\b2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\fH\u0017J\u0017\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00018\u0000H\u0017\u00a2\u0006\u0002\u0010\u0011R\u001c\u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/hotmart/locations/core/tools/SingleLiveEvent;", "T", "Landroidx/lifecycle/MediatorLiveData;", "()V", "observers", "Landroidx/collection/ArraySet;", "Lcom/hotmart/locations/core/tools/SingleLiveEvent$ObserverWrapper;", "observe", "", "owner", "Landroidx/lifecycle/LifecycleOwner;", "observer", "Landroidx/lifecycle/Observer;", "observeForever", "removeObserver", "setValue", "t", "(Ljava/lang/Object;)V", "ObserverWrapper", "core_internal"})
public class SingleLiveEvent<T extends java.lang.Object> extends androidx.lifecycle.MediatorLiveData<T> {
    private final androidx.collection.ArraySet<com.hotmart.locations.core.tools.SingleLiveEvent.ObserverWrapper<? super T>> observers = null;
    
    public SingleLiveEvent() {
        super();
    }
    
    @androidx.annotation.MainThread()
    @java.lang.Override()
    public void observe(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner owner, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.Observer<? super T> observer) {
    }
    
    @androidx.annotation.MainThread()
    @java.lang.Override()
    public void observeForever(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.Observer<? super T> observer) {
    }
    
    @androidx.annotation.MainThread()
    @java.lang.Override()
    public void removeObserver(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.Observer<? super T> observer) {
    }
    
    @androidx.annotation.MainThread()
    @java.lang.Override()
    public void setValue(@org.jetbrains.annotations.Nullable()
    T t) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u0017\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00018\u0001H\u0016\u00a2\u0006\u0002\u0010\rR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/hotmart/locations/core/tools/SingleLiveEvent$ObserverWrapper;", "T", "Landroidx/lifecycle/Observer;", "observer", "(Landroidx/lifecycle/Observer;)V", "getObserver", "()Landroidx/lifecycle/Observer;", "pending", "", "newValue", "", "onChanged", "t", "(Ljava/lang/Object;)V", "core_internal"})
    static final class ObserverWrapper<T extends java.lang.Object> implements androidx.lifecycle.Observer<T> {
        @org.jetbrains.annotations.NotNull()
        private final androidx.lifecycle.Observer<T> observer = null;
        private boolean pending = false;
        
        public ObserverWrapper(@org.jetbrains.annotations.NotNull()
        androidx.lifecycle.Observer<T> observer) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.lifecycle.Observer<T> getObserver() {
            return null;
        }
        
        @java.lang.Override()
        public void onChanged(@org.jetbrains.annotations.Nullable()
        T t) {
        }
        
        public final void newValue() {
        }
    }
}