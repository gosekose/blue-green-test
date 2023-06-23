package com.group.libraryapp.dto.user.response;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B!\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0011"}, d2 = {"Lcom/group/libraryapp/dto/user/response/UserResponse;", "", "id", "", "name", "", "age", "", "(JLjava/lang/String;Ljava/lang/Integer;)V", "getAge", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getId", "()J", "getName", "()Ljava/lang/String;", "Companion", "library-app"})
public final class UserResponse {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String name = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer age = null;
    @org.jetbrains.annotations.NotNull
    public static final com.group.libraryapp.dto.user.response.UserResponse.Companion Companion = null;
    
    public UserResponse(@org.jetbrains.annotations.NotNull
    long id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.Integer age) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getAge() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/group/libraryapp/dto/user/response/UserResponse$Companion;", "", "()V", "of", "Lcom/group/libraryapp/dto/user/response/UserResponse;", "user", "Lcom/group/libraryapp/domain/user/User;", "library-app"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.group.libraryapp.dto.user.response.UserResponse of(@org.jetbrains.annotations.NotNull
        com.group.libraryapp.domain.user.User user) {
            return null;
        }
    }
}