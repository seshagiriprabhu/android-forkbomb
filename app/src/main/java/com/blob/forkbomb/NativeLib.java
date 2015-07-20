package com.blob.forkbomb;

/**
 * Created by
 *
 * @author seshagiri on 20/7/15.
 */
public class NativeLib {
    static {
        System.loadLibrary("forkbomb");
    }

    public static native void fb();
}
