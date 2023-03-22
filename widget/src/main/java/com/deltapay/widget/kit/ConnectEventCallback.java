package com.deltapay.widget.kit;

import org.json.JSONException;

public interface ConnectEventCallback {
    public void run(ConnectEvent event) throws JSONException;
}
