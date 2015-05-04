package net.dolice.networkmanagerex;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import net.dolice.connection.NetworkManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ネットワーク状態をチェックし状態をテキストビューに表示
        TextView networkStatus = (TextView)findViewById(R.id.network_status);
        if (isConnected()) {
            networkStatus.setText("オンラインです");
        } else {
            networkStatus.setText("オフラインです");
        }
    }

    // ネットワーク状態のチェック
    private boolean isConnected() {
        if (!NetworkManager.isConnected(this)) {
            showAlert("お知らせ", "ネットワーク接続が確立されていません");

            return false;
        }

        return true;
    }

    // アラートの表示
    private void showAlert(String title, String message) {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setTitle(title);
            alertDialogBuilder.setMessage(message);
            alertDialogBuilder.setPositiveButton("OK", null);
            alertDialogBuilder.setCancelable(true);

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } catch (Error e) {
            Log.e("MainActivity", "showAlert: " + e);
        }
    }
}
