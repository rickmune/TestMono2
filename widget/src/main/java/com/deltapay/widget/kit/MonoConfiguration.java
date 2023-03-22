package com.deltapay.widget.kit;

import android.content.Context;

public class MonoConfiguration {

    // required
    public String publicKey;
    public Context context;
    public ConnectSuccessCallback onSuccess;

    // optionals
    public String reference;
    public String reauthCode;
    public ConnectCloseCallback onClose;
    public ConnectEventCallback onEvent;

    private MonoConfiguration(Builder builder) {
        this.context = builder.context;
        this.publicKey = builder.publicKey;
        this.reference = builder.reference;
        this.reauthCode = builder.reauthCode;
        this.onSuccess = builder.onSuccess;
        this.onClose = builder.onClose;
        this.onEvent = builder.onEvent;
    }

    public static class Builder {
        // required
        private final Context context;
        private final String publicKey;
        private final ConnectSuccessCallback onSuccess;

        // optionals
        private String reference = null;
        private String reauthCode = null;
        private ConnectCloseCallback onClose = null;
        private ConnectEventCallback onEvent = null;

        public Builder (Context context, String publicKey, ConnectSuccessCallback onSuccess){
            this.context = context;
            this.publicKey = publicKey;
            this.onSuccess = onSuccess;
        }

        public Builder addReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Builder addReauthCode(String reauthCode) {
            this.reauthCode = reauthCode;
            return this;
        }

        public Builder addOnClose(ConnectCloseCallback onClose){
            this.onClose = onClose;
            return this;
        }

        public Builder addOnEvent(ConnectEventCallback onEvent){
            this.onEvent = onEvent;
            return this;
        }

        public MonoConfiguration build() {
            return new MonoConfiguration(this);
        }
    }
}