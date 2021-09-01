package com.hotmart.locations.core.tools;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016\u00a8\u0006\u0007"}, d2 = {"Lcom/hotmart/locations/core/tools/ViewInstruction;", "", "()V", "failure", "Lcom/hotmart/locations/core/tools/Instruction;", "loading", "success", "core_production"})
public abstract class ViewInstruction {
    
    public ViewInstruction() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.hotmart.locations.core.tools.Instruction success() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.hotmart.locations.core.tools.Instruction loading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.hotmart.locations.core.tools.Instruction failure() {
        return null;
    }
}