package com.hotmart.locations.core.injection;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001:\u0001\bJ\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\t"}, d2 = {"Lcom/hotmart/locations/core/injection/CoreComponent;", "", "getContext", "Landroid/content/Context;", "getHttpManager", "Lcom/hotmart/locations/core/http/HttpManager;", "getOkHttpClient", "Lokhttp3/OkHttpClient;", "Factory", "core_local"})
@dagger.Component(modules = {com.hotmart.locations.core.injection.CoreModule.class})
@javax.inject.Singleton()
public abstract interface CoreComponent {
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.content.Context getContext();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.hotmart.locations.core.http.HttpManager getHttpManager();
    
    @org.jetbrains.annotations.NotNull()
    public abstract okhttp3.OkHttpClient getOkHttpClient();
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/hotmart/locations/core/injection/CoreComponent$Factory;", "", "create", "Lcom/hotmart/locations/core/injection/CoreComponent;", "context", "Landroid/content/Context;", "core_local"})
    @dagger.Component.Factory()
    public static abstract interface Factory {
        
        @org.jetbrains.annotations.NotNull()
        public abstract com.hotmart.locations.core.injection.CoreComponent create(@org.jetbrains.annotations.NotNull()
        @dagger.BindsInstance()
        android.content.Context context);
    }
}